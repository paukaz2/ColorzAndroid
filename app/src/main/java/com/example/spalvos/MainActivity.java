package com.example.spalvos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);

        if (restoredText == null) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("name", "No name");
            editor.apply();
        }

        final Button pradedu = findViewById(R.id.pradeti);
        pradedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zaisti = new Intent(MainActivity.this, zaidimas.class);
                startActivity(zaisti);
            }
        });

        final Button results = findViewById(R.id.rezultatai);
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent res = new Intent(MainActivity.this, Rezultatai.class);
                startActivity(res);
            }
        });

        final Button settings = findViewById(R.id.nustatymai);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nust = new Intent(MainActivity.this, nustatymai.class);
                startActivity(nust);
            }
        });


    }
    int backButtonCount = 0;
    @Override
    public void onBackPressed()
    {
        if(backButtonCount >=1)
        {
            backButtonCount = 0;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the app", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

}
