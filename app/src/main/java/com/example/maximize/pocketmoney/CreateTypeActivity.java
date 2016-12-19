package com.example.maximize.pocketmoney;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import Utility.LoadDatabase;
import adapter.ListViewTypeAdapter;
import db.DatabaseHelper;

/**
 * Created by maximize on 12/17/2016 AD.
 */

public class CreateTypeActivity extends AppCompatActivity {

    private Button btn_save, btn_del;
    private EditText edt_type;
    private ListView mListView;
    private ListViewTypeAdapter listViewTypeAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_type);
        final LoadDatabase loadDB = new LoadDatabase(this, DatabaseHelper.TABLE_TYPE_NAME);
        loadDB.getData();
        mListView = (ListView) findViewById(R.id.list_type);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_del = (Button) findViewById(R.id.btn_del);
        edt_type = (EditText) findViewById(R.id.edt_type);


        listViewTypeAdapter = new ListViewTypeAdapter(this, R.layout.view_item_type, LoadDatabase.typeList);
        mListView.setAdapter(listViewTypeAdapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!("").equals(edt_type.getText().toString().trim())) {
                    String getType = edt_type.getText().toString();
                    if (!LoadDatabase.typeList.contains(getType)) {

                        DatabaseHelper helper = new DatabaseHelper(CreateTypeActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();

                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.COL_TYPE, getType);
                        db.insert(DatabaseHelper.TABLE_TYPE_NAME, null, cv);
                        edt_type.setText("");
                        new LoadDatabase(getApplicationContext(), DatabaseHelper.TABLE_TYPE_NAME).getData();
                        listViewTypeAdapter.notifyDataSetChanged();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลประเภทค่าใช้จ่าย", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < LoadDatabase.typeList.size(); i++) {

                    if (true == LoadDatabase.typeList.get(i).getTypeCheck()) {
                        String type = LoadDatabase.typeList.get(i).getType();

                        if (!loadDB.getUseType(type)) {

                            AlertDialog.Builder dialog = new AlertDialog.Builder(CreateTypeActivity.this);
                            dialog.setTitle("Delete failed!");
                            dialog.setMessage("Type is uesd");
                            dialog.setCancelable(true);
                            dialog.show();
                            break;

                        } else {
                            loadDB.deleteType(type);
                            new LoadDatabase(getApplicationContext(), DatabaseHelper.TABLE_TYPE_NAME).getData();
                            listViewTypeAdapter.notifyDataSetChanged();
                            break;
                        }


                    }
                }
            }
        });


    }


}
