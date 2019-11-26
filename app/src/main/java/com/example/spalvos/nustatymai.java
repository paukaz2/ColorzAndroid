package com.example.spalvos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class nustatymai extends AppCompatActivity {

    static int speedIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nustatymai);
        final EditText vardas = findViewById(R.id.vardas);
        final TextView greitis = findViewById(R.id.greitis);
        final String [] greiciai = {"Slow", "Normal", "Fast"};
        String text = "";
        //static int speedIndex = 1;
        String speed = greiciai[speedIndex];


        final String MY_PREFS_NAME = "MyPrefsFile";

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        if (restoredText != null) {
            text = prefs.getString("name", "Name");//"Name" is the default value.
            speed = prefs.getString("speed", "Normal");//"Name" is the default value.
        }
        vardas.setText(text);
        greitis.setText(speed);

        final Button nustatytiVarda = findViewById(R.id.irasytiVarda);
        nustatytiVarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = vardas.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.apply();

                ConstraintLayout mainLayout;

                // Get your layout set up, this is just an example
                mainLayout = (ConstraintLayout)findViewById(R.id.layout);

                // Then just use the following:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);

                Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();
            }
        });

        final Button nustatytiGreiti = findViewById(R.id.keistiGreiti);
        nustatytiGreiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speedIndex < 2)
                    speedIndex++;
                else
                    speedIndex = 0;
                greitis.setText(greiciai[speedIndex]);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("speed", greiciai[speedIndex]);
                editor.apply();
            }
        });
    }
}
