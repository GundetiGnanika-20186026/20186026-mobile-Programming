package com.example.khanacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class graphActivity extends AppCompatActivity {

    Student[] data;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        data = (Student[]) intent.getSerializableExtra("Student");

        this.buildGraph();

    }


    public void buildGraph() {
        barChart = (BarChart) findViewById(R.id.barchart);
        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
        for (int i = 0; i < data.length;i++) {
            float t = (float) Double.parseDouble(data[i].time);
            bargroup1.add(new BarEntry(t,i));
        }

        ArrayList<BarEntry> bargroup2 = new ArrayList<>();
        for (int i = 0; i < data.length;i++) {
            float t = (float) Double.parseDouble(data[i].points);
            bargroup2.add(new BarEntry(t,i));
        }

        BarDataSet barDataSet1 = new BarDataSet(bargroup1, "Time");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet2 = new BarDataSet(bargroup2, "Points");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < data.length;i++) {
            labels.add(data[i].name.substring(0,6));
        }

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        BarData data = new BarData(labels, dataSets);
        barChart.setData(data);


    }








}
