package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.media.MediaSync;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<state> dataSet = new ArrayList<>();


    TextView textView11,textView12,textView13,textView14,textView15,textView16,textView17,textView18;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        button = findViewById(R.id.button);

        Toast.makeText(this, "Your Intenet Seems Slow", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Wait 2-3s More Fetching Data", Toast.LENGTH_SHORT).show();
        fetchData();

        Log.d("STAGE 2", "DATA FETCHED ");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
                Toast.makeText(MainActivity.this, "REFRESHED", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void fetchData(){
        String url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray raw = response.getJSONArray("regionData");
                    for(int i = 0; i<raw.length();i++){
                        dataSet.add(new state(raw.getJSONObject(i).getString("region"),raw.getJSONObject(i).getString("activeCases"),raw.getJSONObject(i).getString("newInfected"),raw.getJSONObject(i).getString("recovered"),raw.getJSONObject(i).getString("newRecovered"),raw.getJSONObject(i).getString("deceased"),raw.getJSONObject(i).getString("newDeceased"),raw.getJSONObject(i).getString("totalInfected") ));
                        Log.d("STAGE 1", "GOT STATE : " + i);
                    }
                    textView11.setText(response.getString("activeCasesNew"));
                    textView12.setText(response.getString("activeCases"));
                    textView13.setText(response.getString("recoveredNew"));
                    textView14.setText(response.getString("recovered"));
                    textView15.setText(response.getString("deathsNew"));
                    textView16.setText(response.getString("deaths"));
                    textView17.setText(response.getString("totalCases"));
                    textView18.setText(response.getString("lastUpdatedAtApify"));
                    Log.d("STAGE 1", "INDIAN DATA SET");
                    Log.d("STAGE 1", "DATASET PREPARED");
                    CustomAdapter cd = new CustomAdapter(dataSet,MainActivity.this);
                    Log.d("STAGE 3", "DATA SENT TO CUSTOM ADAPTER");
                    recyclerView.setAdapter(cd);
                    Log.d("STAGE 4", "ADAPTER SET ON RECYCLE VIEW");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("STAGE 1", "DATASET PREPARETION FAILED IN CATCH");
                    Log.d("STAGE 3", "DATA NOT SENT TO CUSTOM ADAPTER");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("STAGE 1", "ERROR IN RESPONSE");
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}