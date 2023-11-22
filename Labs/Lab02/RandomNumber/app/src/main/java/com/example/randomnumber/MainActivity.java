package com.example.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView minNumber;
    TextView maxNumber;
    TextView numResult;
    Button btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minNumber = (TextView) findViewById(R.id.minNumber);
        maxNumber = (TextView) findViewById(R.id.maxNumber);
        numResult = (TextView) findViewById(R.id.numResult);

        btnRandom = (Button) findViewById(R.id.btnClick);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min = Integer.parseInt(minNumber.getText().toString());
                int max = Integer.parseInt(maxNumber.getText().toString());

                if (min >= max) {
                    Toast.makeText(MainActivity.this, "Min must be less than Max", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Random random = new Random();
                    int randomNum = random.nextInt(max - min + 1) + min;
                    numResult.setText(String.valueOf(randomNum));
                }
            }
        });
    }
}