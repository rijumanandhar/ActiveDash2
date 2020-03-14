package com.example.android_recyclerview_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;


import com.example.android_recyclerview_sample.adapter.SandwichAdapter;
import com.example.android_recyclerview_sample.model.Sandwich;
import com.example.android_recyclerview_sample.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Sandwich> sandwichList ;
    private RecyclerView recyclerView;
    private SandwichAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);

        Log.d("debug-array", "" + sandwiches.length);

        sandwichList = new ArrayList<Sandwich>();

        for (String item: sandwiches){
            try {
                sandwichList.add(JsonUtils.parseSandwichJson(item));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d("debug-list", "" + sandwichList.size());

        recyclerView = findViewById(R.id.sandwich_list);

        // TODO (07) Create a new SandwichAdapter variable,
        // and bind it to the RecyclerView’s Adapter.

        adapter = new SandwichAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setData(sandwichList);



    }



}
