package com.example.spalvos;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class zaidimas extends AppCompatActivity {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Random generatorius = new Random(timestamp.getTime());
    ArrayList<Spalva> spalvuList = new ArrayList<Spalva>();
    int laikoIntervalas = 650;
    String esamaSpalva = "";
    String reikiamaSpalva = "";
    private int taskai = 0;

    public int getTaskai() {
        return taskai;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaidimas);

        spalvuList.add(new Spalva(getResources().getColor(R.color.oranzine), "Orange"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.raudona), "Red"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.zalia), "Green"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.melyna), "Dark Blue"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.zydra), "Sky Blue"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.balta), "White"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.juoda), "Black"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.rozine), "Pink"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.violetine), "Violet"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.ruda), "Brown"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.geltona), "Yellow"));
        spalvuList.add(new Spalva(getResources().getColor(R.color.pilka), "Grey"));

        final TextView kokiaSpalva = findViewById(R.id.spalvosPavadinimas);
        final TextView kiekTasku = findViewById(R.id.taskai);
        kokiaSpalva.bringToFront();
        kiekTasku.bringToFront();
        reikiamaSpalva = spalvuList.get(generatorius.nextInt(spalvuList.size())).getPavadinimas();
        kokiaSpalva.setText(reikiamaSpalva);
        kiekTasku.setText("0");

        final String MY_PREFS_NAME = "MyPrefsFile";
        String speed = "Normal";

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        if (restoredText != null) {
            speed = prefs.getString("speed", "Normal");//"Normal" is the default value.
        }
        if(speed.equals("Slow"))
            laikoIntervalas = 800;
        else if(speed.equals("Normal"))
            laikoIntervalas = 650;
        else if(speed.equals("Fast"))
            laikoIntervalas = 500;


        final Button speju = findViewById(R.id.speti);
        speju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        if(ArTaSpalva(esamaSpalva, reikiamaSpalva)) {
                            reikiamaSpalva = spalvuList.get(generatorius.nextInt(spalvuList.size())).getPavadinimas();
                            kokiaSpalva.setText(reikiamaSpalva);
                            kiekTasku.setText(""+taskai);
                        }else {
                            Intent baigti = new Intent(zaidimas.this, pabaiga.class);
                            Bundle b = new Bundle();
                            b.putInt("taskai", taskai);
                            baigti.putExtras(b);
                            startActivity(baigti);
                            finish();
                        }
                        KeistiGreiti(taskai);
                    }
                }).start();
            }
        });

        new Thread(new Runnable() {
            public void run() {
                Timer timer = new Timer();
                //Set the schedule function
                timer.scheduleAtFixedRate(new TimerTask() {

                                              @Override
                                              public void run() {
                                                  KeistiSpalva();
                                              }
                                          },
                        0, laikoIntervalas);
            }
        }).start();
    }

    public boolean ArTaSpalva(String esama, String reikiama)
    {
        if(esama.equals(reikiama)) {
            taskai++;
            return true;
        }
        return false;
    }

    public int KeistiSpalva()
    {
        Collections.shuffle(spalvuList);
        int maziausiaiNaudotas = 0;
        int kiekMaziausiai = 999;
        for(int i = 0; i < spalvuList.size(); i++)
        {
            if(spalvuList.get(i).getNaudota() < kiekMaziausiai)
            {
                maziausiaiNaudotas = i;
                kiekMaziausiai = spalvuList.get(i).getNaudota();
            }
        }
        PakeistiSpalvosLaukus(maziausiaiNaudotas);

        return spalvuList.get(maziausiaiNaudotas).getSpalva();
    }

    public void PakeistiSpalvosLaukus(int maziausiaiNaudotas)
    {
        TextView spalvosLaukas = findViewById(R.id.rodomaSpalva);
        spalvosLaukas.setBackgroundColor(spalvuList.get(maziausiaiNaudotas).getSpalva());
        esamaSpalva = spalvuList.get(maziausiaiNaudotas).getPavadinimas();
        spalvuList.get(maziausiaiNaudotas).addNaudota();
    }

    public int KeistiGreiti(int number)
    {
        if(number %4 == 0)
            laikoIntervalas -= 50;

        return laikoIntervalas;
    }
}
