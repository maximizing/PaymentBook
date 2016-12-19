package com.example.maximize.pocketmoney;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Utility.LoadDatabase;
import db.DatabaseHelper;

/**
 * Created by maximize on 12/14/2016 AD.
 */

public class PaymentActivity extends AppCompatActivity {

    private EditText edtDescrib, edtPrice;
    private Button btn_save;
    private Spinner mSpinerType;
    DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        helper = new DatabaseHelper(PaymentActivity.this);
        db = helper.getWritableDatabase();
        new LoadDatabase(this, DatabaseHelper.TABLE_TYPE_NAME).getData();

        edtDescrib = (EditText) findViewById(R.id.describtion);
        edtPrice = (EditText) findViewById(R.id.price);
        btn_save = (Button) findViewById(R.id.btn_save);
        mSpinerType = (Spinner) findViewById(R.id.spin_type);

        ArrayList<String> arrList = new ArrayList<String>();
        for (int i = 0; i < LoadDatabase.typeList.size(); i++) {
            arrList.add(LoadDatabase.typeList.get(i).getType());
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrList);
        mSpinerType.setAdapter(adapterSpinner);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String payment = edtDescrib.getText().toString().trim();
                Float price = Float.valueOf(edtPrice.getText().toString().trim());
                String type = mSpinerType.getSelectedItem().toString();
                String datetime = getDateTime();

                if (!"".equals(payment) && !"".equals(price) && !"".equals(type)) {


                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COL_DESCRIB, payment);
                    cv.put(DatabaseHelper.COL_PRICE, price);
                    cv.put(DatabaseHelper.COL_TYPE, type);
                    cv.put(DatabaseHelper.COL_DATETIME, datetime);

                    db.insert(DatabaseHelper.TABLE_PAYMENT_NAME, null, cv);
                    edtDescrib.setText("");
                    edtPrice.setText("");
                    mSpinerType.setSelection(0);
                }
            }
        });

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
