package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Activity_WebService extends AppCompatActivity {
    private static final String TAG = "WebServiceActivity";
    private Handler textHandler = new Handler();
    private CheckBox checkBox_Time;

    RecyclerView assetHistoryRecyclerView;
    List<AssetHistory_Collector> assetHistories;
    AssetHistory_Adapter assetHistory_adapter;
    private RadioGroup radioGroup_coin;
    private String id;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        radioGroup_coin = findViewById(R.id.radioGroup_coin);
        checkBox_Time = findViewById(R.id.checkbox_time);
        progressBar = findViewById(R.id.pbLoading);


        radioGroup_coin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton_bitcoin) {
                    id = "bitcoin";
                } else if (checkedId == R.id.radioButton_rupee) {
                    id = "dogecoin";
                } else if (checkedId == R.id.radioButton_usd) {
                    id = "usd-coin";
                }
            }
        });


    }

    public void callWebserviceButtonHandler(View view) {
        if(id == null) {
            Toast.makeText(this, "Please select one Asset", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(ProgressBar.VISIBLE);
        Activity_WebService.AssetHistory childThread = new Activity_WebService.AssetHistory();
        new Thread(childThread).start();

        assetHistoryRecyclerView = findViewById(R.id.recyclerView_webService);

        assetHistories = new ArrayList<>();

        assetHistoryRecyclerView.setHasFixedSize(true);

        assetHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        assetHistory_adapter = new AssetHistory_Adapter(assetHistories, this);
        assetHistoryRecyclerView.setAdapter(assetHistory_adapter);
    }


    class AssetHistory implements Runnable {
        private static final String TAG = "WebServiceActivity";

        List<AssetHistory_Collector> results = new ArrayList<>();

        @Override
        public void run() {
            URL url = null;
            try {
                System.out.println("Callllinnggg");


                url = new URL("https://api.coincap.io/v2/assets/" + id + "/history?interval=d1");
                //  url = new URL(params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();

                // Read response.
                InputStream inputStream = conn.getInputStream();
                final String resp = convertStreamToString(inputStream);


                JSONObject jObject = new JSONObject(resp);
                //JSONObject data = jObject.getJSONObject("data");
                JSONArray dataArray = jObject.getJSONArray("data");


                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject obj = dataArray.getJSONObject(i);
                    String priceUsd = obj.getString("priceUsd");
                    String date = obj.getString("date");
                    String time = "";
                    if (checkBox_Time.isChecked()) {
                        time = obj.getString("time");
                        time = "Time: " + time;
                    }
                    AssetHistory_Collector assetHistory_collector = new AssetHistory_Collector(priceUsd, date, time);


                    assetHistories.add(assetHistory_collector);
                }

                textHandler.post(() -> {
                    assetHistory_adapter.setNewList(assetHistories);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);


                });


            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, "JSONException");
                e.printStackTrace();
            }


        }


        private String convertStreamToString(InputStream is) {
            Scanner s = new Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next().replace(",", ",\n") : "";
        }

    }


}