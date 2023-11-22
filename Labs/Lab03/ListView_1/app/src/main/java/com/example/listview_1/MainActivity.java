package com.example.listview_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewSubject;
    EditText editText;
    Button btnAdd, btnUpdate, btnDelete;
    Integer indexValue;
    String message;
    ArrayList<String> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSubject = (ListView) findViewById(R.id.ListViewSubject);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editText = (EditText) findViewById(R.id.txtEditText);

        subjects = new ArrayList<>();
        subjects.add("Android");
        subjects.add("PHP");
        subjects.add("IOS");
        subjects.add("Unity");
        subjects.add("ASP.Net");

        ArrayAdapter adapter = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1, subjects);
        listViewSubject.setAdapter(adapter);

        //Add items
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputValue = editText.getText().toString();
                if(TextUtils.isEmpty(inputValue)){
                    Toast.makeText(
                            MainActivity.this, "Empty! Add failed", Toast.LENGTH_SHORT).show();
                }
                else if (subjects.contains(inputValue)){
                    Toast.makeText(
                            MainActivity.this, "Subject already exists!", Toast.LENGTH_SHORT).show();
                }else {
                    subjects.add(inputValue);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });

        //select items
        listViewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexValue = i;
                editText.setText(adapterView.getItemAtPosition(i).toString());
              message = adapterView.getItemAtPosition(i).toString() + " has been selected";
              Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        //Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputValue = editText.getText().toString();
                if(TextUtils.isEmpty(inputValue)){
                    Toast.makeText(
                            MainActivity.this, "Empty! Update failed", Toast.LENGTH_SHORT).show();
                }else{
                    subjects.set(indexValue, inputValue);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(
                            MainActivity.this, "Updated success!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexValue != null) {
                    subjects.remove(indexValue.intValue());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(
                            MainActivity.this, "Deleted success", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    indexValue = null;
                } else {
                    Toast.makeText(
                            MainActivity.this, "No item selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Delete
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (indexValue >= 0 && indexValue < subjects.size()) {
//                    subjects.remove(indexValue);
//                    adapter.notifyDataSetChanged();
//                    Toast.makeText(
//                            MainActivity.this, "Deleted success!", Toast.LENGTH_SHORT).show();
//                    editText.setText("");
//                    indexValue = -1;
//                } else {
//                    Toast.makeText(
//                            MainActivity.this, "No item selected!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}