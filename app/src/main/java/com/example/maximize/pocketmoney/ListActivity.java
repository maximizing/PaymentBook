package com.example.maximize.pocketmoney;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import Utility.LoadDatabase;
import adapter.ListViewPaymentAdapter;
import adapter.ListViewTypeAdapter;
import db.DatabaseHelper;

/**
 * Created by maximize on 12/18/2016 AD.
 */

public class ListActivity extends AppCompatActivity {

    private ListView mListView;
    private ListViewPaymentAdapter listViewPaymentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        new LoadDatabase(this, DatabaseHelper.TABLE_PAYMENT_NAME).getData();

        mListView = (ListView) findViewById(R.id.list_payment);

        listViewPaymentAdapter = new ListViewPaymentAdapter (this, R.layout.view_item_payment, LoadDatabase.paymentList);
        mListView.setAdapter(listViewPaymentAdapter);

    }
}
