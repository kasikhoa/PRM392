package com.example.customlistview_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ArrayList<Legend> arrayList = new ArrayList<>();

        arrayList.add(new Legend(R.drawable.pele, R.drawable.brazil,
                "Pele", "October 23, 1940 (age 72)"));
        arrayList.add(new Legend(R.drawable.maradona, R.drawable.arg,
                "Diego Maradona", "October 30, 1960 (age 52)"));
        arrayList.add(new Legend(R.drawable.johan, R.drawable.holland,
                "Johan Cruyff", "April 25, 1947 (age 65)"));
        arrayList.add(new Legend(R.drawable.ronaldo, R.drawable.brazil,
                "Ronaldo De Lima", "September 22, 1976 (age 36)"));
        arrayList.add(new Legend(R.drawable.m10, R.drawable.arg,
                "Lionel Messi", "June 24, 1987 (age 36)"));
        arrayList.add(new Legend(R.drawable.cr7, R.drawable.portugal,
                "Cristiano Ronaldo", "February 5, 1985 (age 38)"));

        LegendAdapter legendAdapter = new LegendAdapter(this, R.layout.list_row, arrayList);

        listView.setAdapter(legendAdapter);

    }
}