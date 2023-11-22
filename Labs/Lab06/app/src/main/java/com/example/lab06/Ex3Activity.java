package com.example.lab06;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Ex3Activity extends AppCompatActivity {
    Button btnChangeBackgroundColor;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);
        layout = findViewById(R.id.constraintLayoutMain);
        btnChangeBackgroundColor = findViewById(R.id.btnChangeBackgroundColor);
        registerForContextMenu(btnChangeBackgroundColor);
        Toolbar toolbar = findViewById(R.id.toolbarEx3);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        changeBackgroundColor(item.getItemId());
        return super.onContextItemSelected(item);
    }

    private void changeBackgroundColor(int menuItemId) {
        if (menuItemId == R.id.menuItemRed) {
            layout.setBackgroundColor(getResources().getColor(R.color.red));
        }
        if (menuItemId == R.id.menuItemGreen) {
            layout.setBackgroundColor(getResources().getColor(R.color.green));
        }
        if (menuItemId == R.id.menuItemYellow) {
            layout.setBackgroundColor(getResources().getColor(R.color.yellow));
        }

    }
}