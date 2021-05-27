package com.nishantboro.splititeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.generated.model.Account;
import com.amplifyframework.datastore.generated.model.User;



public class AccountPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Amplify.DataStore.query(
                User.class, Where.id(Amplify.Auth.getCurrentUser().getUserId()),
                items -> {
                    while (items.hasNext()) {
                        User item = items.next();
                        Log.i("Amplify", "Id " + item.getId());
                        Log.i("Amplify", "Name " + item.getFirstname() + ""+item.getLastname());
                        Log.i("Amplify", "email " + item.getEmail());
                        Log.i("Amplify", "phone " + item.getPhonenumber());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);
        TextView tv_name = findViewById(R.id.tv_name);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phone);
        TextView type = findViewById(R.id.type);
        TextView balance = findViewById(R.id.balance);
        Amplify.DataStore.query(
                User.class, Where.id(Amplify.Auth.getCurrentUser().getUserId()),
                items -> {
                    while (items.hasNext()) {
                        User item = items.next();
                        tv_name.setText(item.getFirstname()+" "+item.getLastname());
                        email.setText(item.getEmail());
                        phone.setText(item.getPhonenumber());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
        Amplify.DataStore.query(
                Account.class,
                items -> {
                    while (items.hasNext()) {
                        Account item = items.next();
                        type.setText(item.getAccountType());
                        balance.setText(item.getBalance().toString());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

    }
    String accountID = "cbe37da8-7fae-4d7b-b84f-8f9a35a8ff53";
    String userID = "4088d4e8-f5e9-4d74-8feb-69ae74bebc06";
    public void onPress(View view) {
        Account item = Account.builder()
                .accountType("Saving Account")
                .balance((double) 1000.45F)
                .userId(Amplify.Auth.getCurrentUser().getUserId())
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId() + success.item().getUserId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }

    public void onPressSync(View view) {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin(DataStoreConfiguration.builder()
                    .syncExpression(User.class, () -> User.ID.eq("4088d4e8-f5e9-4d74-8feb-69ae74bebc06"))
                    .syncExpression(Account.class, () -> Account.ID.eq("cbe37da8-7fae-4d7b-b84f-8f9a35a8ff53"))
                    .build()));
        } catch (AmplifyException e) {
            e.printStackTrace();
        }
    }
}