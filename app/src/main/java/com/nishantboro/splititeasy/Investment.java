package com.nishantboro.splititeasy;

import android.content.Intent;
import android.os.Bundle;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.generated.model.Account;
import com.amplifyframework.datastore.generated.model.InvestmentPla;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Investment extends AppCompatActivity {

    public EditText planName,planDesc,planGoal,planDeposit;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);
        planName = findViewById(R.id.planName);
        planDesc = findViewById(R.id.planDes);
        planGoal = findViewById(R.id.planGoal);
        planDeposit = findViewById(R.id.planDeposit);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plan =   planName.getText().toString();
                String desc =  planDesc.getText().toString();
                String goal = planGoal.getText().toString();
                double goalvalue = 0;
                if (!"".equals(goal)){ goalvalue = Double.parseDouble(goal);}
                String deposit = planDeposit.getText().toString();
                double depositamount = 0;
                if(!"".equals(deposit)){depositamount = Double.parseDouble(deposit);}
                // to save data locally

                InvestmentPla item = InvestmentPla.builder()
                        .planName(plan)
                        .description(desc)
                        .deposit(depositamount)
                        .goal(goalvalue)
                        .accountId(Amplify.Auth.getCurrentUser().getUserId())
                        .build();
                Amplify.DataStore.save(
                        item,
                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                );
                double finalDepositamount = depositamount;
                Amplify.DataStore.query(Account.class, Where.id("cbe37da8-7fae-4d7b-b84f-8f9a35a8ff53"),
                        matches -> {
                            if (matches.hasNext()) {
                                Account original = matches.next();
                                Double amount = original.getBalance();
                                Account edited = original.copyOfBuilder()
                                        .balance(amount - finalDepositamount)
                                        .build();
                                Amplify.DataStore.save(edited,
                                        updated -> Log.i("MyAmplifyApp", "Updated a post."),
                                        failure -> Log.e("MyAmplifyApp", "Update failed.", failure)
                                );
                            }
                        },
                        failure -> Log.e("MyAmplifyApp", "Query failed.", failure)
                );
                // to sync data to the cloud
                try{
                    Amplify.addPlugin(new AWSDataStorePlugin(DataStoreConfiguration.builder()
                            .syncExpression(InvestmentPla.class, () -> InvestmentPla.ACCOUNT_ID.eq(Amplify.Auth.getCurrentUser().getUserId()))
                            .build()));
                }catch (AmplifyException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity (new Intent(Investment.this,InvestmentPlan.class));
    }
}