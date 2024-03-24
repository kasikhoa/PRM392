package com.example.doraemonracing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String REQUIRE = "Require";
    private EditText etUsername;
    private EditText etPassword;
    private TextView txtNotAccountYet;
    private Button btnSignIn;

    private boolean checkLogin() {
        if (TextUtils.equals(etUsername.getText().toString(), ("user")) && TextUtils.equals(etPassword.getText().toString(), "1234")) {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUsername = (EditText) findViewById(R.id.etSignInName);
        etPassword = (EditText) findViewById(R.id.etSignInPass);
        txtNotAccountYet = (TextView) findViewById(R.id.txtCreate);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        txtNotAccountYet.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    private Boolean checkInput() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void signIn() {
        if (!checkInput()) {
            return;
        }
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpForm() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignIn) {
            if (checkLogin()) {
                signIn();
            } else {
                Toast.makeText(SignInActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            }

        }
        if (view.getId() == R.id.txtCreate) {
            signUpForm();
        }
    }
}