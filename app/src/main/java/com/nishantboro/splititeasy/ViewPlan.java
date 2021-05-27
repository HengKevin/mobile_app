package com.nishantboro.splititeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.InvestmentPla;

public class ViewPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        String name = getName();
        TextView pName = findViewById(R.id.pName);
        TextView pDesc = findViewById(R.id.pDesc);
        TextView pgoal = findViewById(R.id.pgoal);
        TextView pcurrent = findViewById(R.id.pcurrent);
        Amplify.DataStore.query(
                InvestmentPla.class, Where.matches(InvestmentPla.PLAN_NAME.eq(name)),
                items -> {
                    while (items.hasNext()) {
                        InvestmentPla item = items.next();
                        pName.setText(item.getPlanName());
                        pDesc.setText(item.getDescription());
                        pgoal.setText(item.getGoal().toString());
                        pcurrent.setText(item.getDeposit().toString());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private String getName() { return getIntent().getStringExtra("name");}

    public void onDeposit(View view) {
        String name = getName();
        TextView pcurrent = findViewById(R.id.pcurrent);
        String temp = pcurrent.getText().toString();
        double currentvalue = Double.parseDouble(temp);
        currentvalue += 10.00;

        double finalCurrentvalue = currentvalue;
        Amplify.DataStore.query(InvestmentPla.class, Where.matches(InvestmentPla.PLAN_NAME.eq(name)),
                matches -> {
                    if (matches.hasNext()) {
                        InvestmentPla original = matches.next();
                        InvestmentPla edited = original.copyOfBuilder()
                                .deposit(finalCurrentvalue)
                                .build();
                        Amplify.DataStore.save(edited,
                                updated -> Log.i("MyAmplifyApp", "Updated a post."),
                                failure -> Log.e("MyAmplifyApp", "Update failed.", failure)
                        );
                    }
                },
                failure -> Log.e("MyAmplifyApp", "Query failed.", failure)
        );
    }
}