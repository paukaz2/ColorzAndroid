package com.example.spalvos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Rezultatai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultatai);

        final ListView sarasas = findViewById(R.id.listView);
        MyDBHandler dbHandler = new MyDBHandler(this);

        ArrayList<rezultatas> scores = dbHandler.loadHandler();

        RezListAdapter adapter = new RezListAdapter(scores, this);
        sarasas.setAdapter(adapter);
    }
}
