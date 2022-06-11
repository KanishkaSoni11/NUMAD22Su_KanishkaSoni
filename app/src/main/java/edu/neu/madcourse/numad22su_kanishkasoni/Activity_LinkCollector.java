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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_LinkCollector extends AppCompatActivity implements LinkCollector_Dialog.LinkCollector_Interface {

    private RecyclerView urlRecyclerView;

    List<LinkCollector> linkCollectorList;

    private View view;

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
        linkCollectorAdapter = new LinkCollectorAdapter(linkCollectorList, this);
        urlRecyclerView.setAdapter(linkCollectorAdapter);


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
        this.view = view;
    }

    public void openDialog(View view) {
        LinkCollector_Dialog dialog = new LinkCollector_Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }


    @Override
    public void applText(String name, String url) {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(url);

        if (m.matches()) {
            Snackbar.make(view, "Link added successfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            linkCollectorList.add(new LinkCollector(name, url));
            linkCollectorAdapter.notifyDataSetChanged();
        } else {
            Snackbar.make(view, "Link invalid", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }



    }
}