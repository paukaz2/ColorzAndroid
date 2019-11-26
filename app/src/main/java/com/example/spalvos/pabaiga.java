package com.example.spalvos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pabaiga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pabaiga);

        final String MY_PREFS_NAME = "MyPrefsFile";
        String name = "";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        if (restoredText != null) {
            name = prefs.getString("name", "Name");//"Name" is the default value.
        }

        Bundle b = getIntent().getExtras();
        int taskai = -1;
        if(b != null)
            taskai = b.getInt("taskai");
        final TextView task = findViewById(R.id.taskaiUzrasas);
        task.setText("Points: "+taskai);

        MyDBHandler dbHandler = new MyDBHandler(this);
        rezultatas score = new rezultatas(name, taskai);
        dbHandler.addHandler(score);

        final Button baigti = findViewById(R.id.baigti);
        baigti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meniu = new Intent(pabaiga.this, MainActivity.class);
                startActivity(meniu);
            }
        });

        final Button zaisti = findViewById(R.id.zaisti);
        zaisti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zaisti = new Intent(pabaiga.this, zaidimas.class);
                startActivity(zaisti);
            }
        });
    }
}
