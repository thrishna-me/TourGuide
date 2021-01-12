package com.example.tourguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private  BlogPreferences preferences;
    private TextInputLayout usernameInputLayout;
    private TextInputLayout passwordInputLayout;
    private Button loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new BlogPreferences(this);
        if (preferences.isLoggedIn()) {
            startMainActivity();
            finish();
        }
        setContentView(R.layout.activity_login);
        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);
        
        loginButton.setOnClickListener(view -> onLoginClicked()); //used Lambda Expression here

        usernameInputLayout
                .getEditText()
                .addTextChangedListener(createTextWatcher(usernameInputLayout));

        passwordInputLayout
                .getEditText()
                .addTextChangedListener(createTextWatcher(passwordInputLayout));
    }

    private TextWatcher createTextWatcher(TextInputLayout usernameInputLayout) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordInputLayout.setError(null);
                usernameInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    private void onLoginClicked() {
        String username = usernameInputLayout.getEditText().getText().toString();
        String password = passwordInputLayout.getEditText().getText().toString();
        if (username.isEmpty()){
            usernameInputLayout.setError("Username can't be empty");
        } else if (password.isEmpty()){
            passwordInputLayout.setError("Password can't be empty");
        } else if (!username.equals("admin") & !password.equals("admin")) {
            showErrorDialog();
        } else{
            performLogin();
        }
    }

    private void performLogin() {
        preferences.setLoggedIn(true);

        usernameInputLayout.setEnabled(false);
        passwordInputLayout.setEnabled(false);
        loginButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startMainActivity();
            finish();
        }, 2000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage("Username or password is incorrect. Please try again")
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }
}