package com.example.khanacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Student_list extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;
    Button button;
    Student[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        button = (Button) findViewById(R.id.graph_id);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                go_next();
            }

        });






        Intent intent = getIntent();
        data = (Student[])intent.getSerializableExtra("Student");;


        for(int i = 0; i < data.length; i++) {
            ListItem listItem = new ListItem(
                    "Name: " +data[i].name,
                    "Time: " + data[i].time + " sec",
                    "Points: " + data[i].points);
            listItems.add(listItem);
        }

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

    }


    public void go_next() {
        Intent intent = new Intent(this,graphActivity.class);
        intent.putExtra("Student",this.data);
        startActivity(intent);
    }









}
