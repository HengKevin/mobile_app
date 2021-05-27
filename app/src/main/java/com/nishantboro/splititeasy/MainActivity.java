package com.nishantboro.splititeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Account;
import com.amplifyframework.datastore.generated.model.User;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
        } catch (AmplifyException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        AuthUser currentUser = Amplify.Auth.getCurrentUser();


        Intent intent;
        if(currentUser == null) {
            //Go to login screen
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        } else {
            //Go to the feature screen
            intent = new Intent(getApplicationContext(), FeatureScreen.class);
        }
        //Start activity
        startActivity(intent);
        finish();
    }
}
