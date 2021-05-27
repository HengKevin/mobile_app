package com.nishantboro.splititeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onPressLogin(View view) {
        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtPassword = findViewById(R.id.txtPassword);

        Amplify.Auth.signIn(
                txtEmail.getText().toString(),
                txtPassword.getText().toString(),
                this::onLoginSuccess,
                this::onLoginError
        );
    }

    private void onLoginError(AuthException e) {
        this.runOnUiThread(() -> {
        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    });
    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        //Go to feature screen
        Intent intent = new Intent(this, FeatureScreen.class);
        startActivity(intent);
    }

    public void onPressRegister(View view) {
        //Go to Register screen
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}