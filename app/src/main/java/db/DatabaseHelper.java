package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Promlert on 2016-11-20.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "charges_book.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PAYMENT_NAME = "payment";
    public static final String TABLE_TYPE_NAME = "type";
    public static final String COL_ID = "_id";
    public static final String COL_DESCRIB = "description";
    public static final String COL_PRICE = "price";
    public static final String COL_TYPE = "type";
    public static final String COL_DATETIME = "date_time";


    private static final String SQL_CREATE_PAYMENT_TABLE =
            "CREATE TABLE " + TABLE_PAYMENT_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_DESCRIB + " TEXT, "
                    + COL_PRICE + " FLOAT, "
                    + COL_TYPE + " TEXT, "
                    + COL_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ");";

    private static final String SQL_CREATE_TYPE_TABLE =
            "CREATE TABLE " + TABLE_TYPE_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_TYPE + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAYMENT_TABLE);
        db.execSQL(SQL_CREATE_TYPE_TABLE);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_TYPE, "อาหาร");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "เครื่องดื่ม");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "การเดินทาง");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "การจ่ายบิล");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "ค่าชอปปิ้ง");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "การท่องเที่ยว");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "ค่ารักษาพยาบาล");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "ความบันเทิง");
        db.insert(TABLE_TYPE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_TYPE, "ของขัวญ/ของฝาก");
        db.insert(TABLE_TYPE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
