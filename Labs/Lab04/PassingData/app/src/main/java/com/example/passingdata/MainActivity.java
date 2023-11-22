package com.example.passingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            String [] cityArr = {"Hanoi", "HCM", "Da Nang", "Phan Thiet"};
            Student student = new Student("Nguyen Anh Khoa", "SE150494");
            Bundle bundle = new Bundle();
            bundle.putString("string", "Send data with Bundle");
            bundle.putInt("number", 2401);
            bundle.putStringArray("array", cityArr);
            bundle.putSerializable("student", student);

            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        });
    }
}