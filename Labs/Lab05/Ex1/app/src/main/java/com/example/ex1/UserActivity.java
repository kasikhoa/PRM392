package com.example.ex1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView rvUserList = findViewById(R.id.rvUserList);

        users = new ArrayList<>();
        users.add(new User("khoa", "Nguyen Anh Khoa", "khoandase@gmail.com"));
        users.add(new User("khoa", "Nguyen Anh Khoa", "khoandase@gmail.com"));
        users.add(new User("khoa", "Nguyen Anh Khoa", "khoandase@gmail.com"));
        users.add(new User("khoa", "Nguyen Anh Khoa", "khoandase@gmail.com"));
        users.add(new User("khoa", "Nguyen Anh Khoa", "khoandase@gmail.com"));

        UserAdapter userAdapter = new UserAdapter(users);
        rvUserList.setAdapter(userAdapter);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
    }
}