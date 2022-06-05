package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Activity_LinkCollector extends AppCompatActivity implements LinkCollector_Dialog.LinkCollector_Interface {

    RecyclerView urlRecyclerView;

    List<LinkCollector> linkCollectorList;

    private LinkCollectorAdapter linkCollectorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkcollector);

        //Instantiate the arraylist
        linkCollectorList = new ArrayList<>();

        urlRecyclerView = findViewById(R.id.linkCollector_recycler_view);

        urlRecyclerView.setHasFixedSize(true);

        //This defines the way in which the RecyclerView is oriented
        urlRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        urlRecyclerView.setAdapter(new LinkCollectorAdapter(linkCollectorList, this));


    }

    public void onClick_Url(View view) {
        System.out.println("View 1 + " + view);

        TextView textView = view.findViewById(R.id.url);
        CharSequence text = textView.getText();
        System.out.println("View -- " + text);
        Uri webpage = Uri.parse(String.valueOf(text));

        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    public void onClick_fab(View view) {
        openDialog(view);
    }

    public void openDialog(View view) {
        LinkCollector_Dialog dialog = new LinkCollector_Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }


    @Override
    public void applText(String name, String url) {
        linkCollectorList.add(new LinkCollector(name, url));

    }
}