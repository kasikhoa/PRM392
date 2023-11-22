package com.example.customlistview_2;

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

        // Create data
        ArrayList<Fruit> arrayList = new ArrayList<>();

        arrayList.add(new Fruit(R.drawable.avocado,
                "Avocado", "Avocado... Some Description Goes Here"));
        arrayList.add(new Fruit(R.drawable.cam,
                "Orange", "Orange... Some Description Goes Here"));
        arrayList.add(new Fruit(R.drawable.kiwi,
                "Kiwi", "Kiwi... Some Description Goes Here"));
        arrayList.add(new Fruit(R.drawable.pineapple,
                "Pineapple", "Pineapple... Some Description Goes Here"));
        arrayList.add(new Fruit(R.drawable.strawberry,
                "Strawberry", "Strawberry... Some Description Goes Here"));

        // custom adapter
        FruitAdapter personAdapter = new FruitAdapter(this, R.layout.list_row, arrayList);

        listView.setAdapter(personAdapter);
    }
}