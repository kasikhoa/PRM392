package com.example.doraemonracing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RuleActivity extends AppCompatActivity {
    Button buttonRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_rules);
        buttonRule = findViewById(R.id.buttonRules);
        buttonRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RuleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
