package com.example.maximize.pocketmoney;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import Format.FloatFormatter;
import Utility.LoadDatabase;
import db.DatabaseHelper;
import model.Payment;

/**
 * Created by maximize on 12/19/2016 AD.
 */

public class ChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        LoadDatabase loadDB = new LoadDatabase(this, DatabaseHelper.TABLE_TYPE_NAME);

        PieChart pie = (PieChart) findViewById(R.id.pie_chart);
        final ArrayList<Payment> listStudent = loadDB.getSum();
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Payment payment : listStudent) {
            entries.add(new PieEntry(Float.parseFloat(payment.getPrice()), payment.getType()));
        }


        PieDataSet dataset = new PieDataSet(entries, "ประเภทของค่าใช้จ่าย");
        dataset.setSelectionShift(20); //ขนาดของ Pie เมื่อถูกเลือก
        dataset.setValueTextSize(15);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS); // set the color

        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        Description description = new Description();
        description.setText("แผนภูมิวงกลมแสดงค่าใช้จ่ายแต่ละประเภท");

        PieData data = new PieData(dataset);
        data.setValueFormatter(new FloatFormatter());
        pie.setData(data);
        pie.setHoleRadius(30); //ช่องว่างตรงกลาง
        pie.setTransparentCircleRadius(40);
        pie.setDescription(description);
        pie.animateY(3000);






    }
}
