package com.example.spalvos;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RezListAdapter extends BaseAdapter {

    ArrayList<rezultatas> scorai;
    Activity activity;
    int i = 1;

    RezListAdapter(ArrayList<rezultatas> sc, Activity a)
    {
        scorai = sc;
        activity = a;
    }

    public int getCount()
    {
        if(scorai != null)
            return scorai.size();
        return 0;
    }

    public long getItemId(int position) {return position;}

    public Object getItem(int position)
    {
        if(scorai != null)
            return scorai.get(position);
        return null;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if(vi == null)
        {
            LayoutInflater li = LayoutInflater.from(activity);
            vi = li.inflate(R.layout.adapter_view_layout, null);
        }
        TextView id = (TextView) vi.findViewById(R.id.ID);
        TextView vardas = (TextView) vi.findViewById(R.id.vardas);
        TextView rez = (TextView) vi.findViewById(R.id.rezultatas);

        rezultatas score = scorai.get(position);

        id.setText(Integer.toString(score.getId()));
        vardas.setText(score.getVardas());
        rez.setText(Integer.toString(score.getRezultatas()));

        return vi;
    }
}
