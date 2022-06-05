package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Activity_LinkCollector extends AppCompatActivity {

    RecyclerView urlRecyclerView;

    List<LinkCollector> linkCollectorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkcollector);

        //Instantiate the arraylist
        linkCollectorList = new ArrayList<>();
        String url = "https://reddit.com";

        List<String> sampleNames = new ArrayList<>(List.of("Aarav", "Beth", "Chun", "Dasya", "Ed", "Faith", "Gran", "Hem", "Isaac", "Jing", "Karl", "Liang", "Marvin", "Nimit"));
        for (String name : sampleNames) {
            linkCollectorList.add(new LinkCollector(name, url));
        }


        urlRecyclerView = findViewById(R.id.linkCollector_recycler_view);

        urlRecyclerView.setHasFixedSize(true);

        //This defines the way in which the RecyclerView is oriented
        urlRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        urlRecyclerView.setAdapter(new LinkCollectorAdapter(linkCollectorList, this));


    }

    public void onClick_Url(View view) {
        TextView textView = findViewById(R.id.url);
        CharSequence text = textView.getText();
        Uri webpage = Uri.parse(String.valueOf(text));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    public void onClick_fab(View view){
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}