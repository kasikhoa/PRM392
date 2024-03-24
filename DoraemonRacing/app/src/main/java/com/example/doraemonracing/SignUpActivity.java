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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String REQUIRE = "Require";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView txtAlreadyAccount;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = (EditText) findViewById(R.id.etSignUpName);
        etPassword = (EditText) findViewById(R.id.etSignUpPass);
        etConfirmPassword = (EditText) findViewById(R.id.etSignUpConfirmPass);
        txtAlreadyAccount = (TextView) findViewById(R.id.txtLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        txtAlreadyAccount.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
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
        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(etPassword.getText().toString(), etConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Password is not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void signUp() {
        if (!checkInput()) {
            return;
        }
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void signInForm() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) {
            signUp();
        }
        if (view.getId() == R.id.txtLogin) {
            signInForm();
        }
    }
}