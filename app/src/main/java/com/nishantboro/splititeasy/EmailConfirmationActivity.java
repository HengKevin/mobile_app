package com.nishantboro.splititeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.predictions.models.Feature;

public class EmailConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);
    }

    public void onConfirmButtonPressed(View view) {
        // confirm the code
        // re-login
        // save the user details in data-store

        EditText txtConfirmationCode = findViewById(R.id.txtConfirmationCode);

        Amplify.Auth.confirmSignUp(
                getEmail(),
                txtConfirmationCode.getText().toString(),
                this::onSuccess,
                this::onError
        );
    }

    private void onError(AuthException e) {
        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private void onSuccess(AuthSignUpResult authSignUpResult) {
        reLogin();
    }

    private void reLogin() {
        String username = getEmail();
        String password = getPassword();

        Amplify.Auth.signIn(
                username,
                password,
                this::onLoginSuccess,
                this::onError
        );
    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        // save registration data in datastore
        String userId = Amplify.Auth.getCurrentUser().getUserId();
        String firstname = getFirstName();
        String lastname = getLastName();
        String phonenumber = getPhoneNumber();
        String email = getEmail();
        String password = getPassword();

        Amplify.DataStore.save(
                User.builder()
                        .firstname(firstname)
                        .lastname(lastname)
                        .email(email)
                        .phonenumber(phonenumber)
                        .password(password)
                        .id(userId)
                        .build(),
                this::onSavedSuccess,
                this::onError
        );
    }



    private <T extends Model> void onSavedSuccess(DataStoreItemChange<T> tDataStoreItemChange) {
        Intent intent = new Intent(this, FeatureScreen.class);
        Amplify.DataStore.query(
                User.class,
                items -> {
                    while (items.hasNext()) {
                        User user = items.next();
                        Log.i("Amplify", "Id " + user.getId());
                        Log.i("Amplify", "Firstname" + user.getFirstname());
                        Log.i("Amplify", "Lastname" + user.getLastname());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
        startActivity(intent);
    }

    private void onError(DataStoreException e) {
        runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
    private String getPhoneNumber() { return getIntent().getStringExtra("phonenumber"); }

    private String getLastName() { return getIntent().getStringExtra("lastname"); }

    private String getFirstName() {
        return getIntent().getStringExtra("firstname");
    }

    private String getPassword() {
        return getIntent().getStringExtra("password");
    }

    private String getEmail() {
        return getIntent().getStringExtra("email");
    }

}