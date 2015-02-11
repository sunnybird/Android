package com.example.jinlong.dailyartical;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jinlong.dailyartical.activities.WelcomeActivity;
import com.example.jinlong.dailyartical.config.Config;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isFristStart()) {
            startNextActivity();
        }
    }
    public void onClick(View view) {

        SharedPreferences sp = getSharedPreferences(Config.SharePreferName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Config.ShareKey, true);
        editor.commit();
        startNextActivity();
    }

     private boolean isFristStart() {
         SharedPreferences sp = getSharedPreferences(Config.SharePreferName, Context.MODE_PRIVATE);
         return sp.getBoolean(Config.ShareKey,false);
     }

    private void startNextActivity(){

        Intent intent = new Intent();
        intent.setClass(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
        Log.i("MainActivity", "finish()");

    }





}
