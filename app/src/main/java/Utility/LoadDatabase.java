package Utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import db.DatabaseHelper;
import model.Payment;
import model.Type;

/**
 * Created by maximize on 12/18/2016 AD.
 */

public class LoadDatabase {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private String DatabaseName;
    private Context mContext;
    public static ArrayList<Type> typeList = new ArrayList<>();
    public static ArrayList<Payment> paymentList = new ArrayList<>();
    public static ArrayList<Payment> pieType = new ArrayList<>();

    public LoadDatabase(Context context, String database_name) {
        this.DatabaseName = database_name;
        this.mContext = context;

        mHelper = new DatabaseHelper(mContext);
        mDb = mHelper.getWritableDatabase();

    }

    public void getData(){

        typeList.clear();
        paymentList.clear();

        Cursor cursor = mDb.query(DatabaseName, null, null, null, null, null, null);

        while (cursor.moveToNext()) {


            if (DatabaseHelper.TABLE_TYPE_NAME.equals(DatabaseName) && !"".equals(DatabaseName)) {
                String strType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TYPE));
                Type type = new Type();
                type.setType(strType);
                type.setTypeCheck(false);
                typeList.add(type);

            } else if (DatabaseHelper.TABLE_PAYMENT_NAME.equals(DatabaseName) && !"".equals(DatabaseName)) {
                String strPayment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DESCRIB));
                String strPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PRICE));
                String strType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TYPE));
                String strDateTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATETIME));

                Payment payment = new Payment();
                payment.setDescribtion(strPayment);
                payment.setPrice(strPrice);
                payment.setType(strType);
                payment.setDateTime(strDateTime);
                paymentList.add(payment);

            }

        }
        cursor.close();
        //mDb.close();
        //mHelper.close();
    }



    public Boolean getUseType(String type) {

        Cursor cursor = mDb.query(DatabaseHelper.TABLE_PAYMENT_NAME,
                null,
                DatabaseHelper.COL_TYPE + " = ? ",
                new String[]{type},
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
            return false;
        }


        return true;
    }

    public void deleteType(String type) {

        mDb.delete(DatabaseHelper.TABLE_TYPE_NAME,
                DatabaseHelper.COL_TYPE + " = " + type,
                null);
    }

    public ArrayList<Payment> getSum() {

        String table = DatabaseHelper.TABLE_PAYMENT_NAME;
        String[] columns = new String[]{DatabaseHelper.COL_TYPE, "sum(" + DatabaseHelper.COL_PRICE + ") as price "};
        String selection = null;
        String[] arguments = null;
        String groupBy = DatabaseHelper.COL_TYPE;
        String having = null;
        String orderBy = null;

        pieType.clear();

        Cursor cursor = mDb.query(table, columns, selection, arguments, groupBy, having, orderBy);

        while (cursor.moveToNext()) {
            String strType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TYPE));
            String strPrice = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PRICE));

            Payment payment = new Payment();
            payment.setPrice(strPrice);
            payment.setType(strType);
            pieType.add(payment);
        }

        cursor.close();

        return pieType;
    }

}
