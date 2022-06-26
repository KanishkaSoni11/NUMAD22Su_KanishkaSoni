package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    RecyclerView urlRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAbout_me(View view){
//        Context context = getApplicationContext();
//        CharSequence text = "Kanishka Soni : soni.kan@northeastern.edu";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

        Intent intent = new Intent(this, Activity_About.class);
        startActivity(intent);

    }

    public void openAcitvity_Clicky(View view){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public  void openActivity_LinkCollector(View view) {
        Intent intent = new Intent(this, Activity_LinkCollector.class);
        startActivity(intent);
    }

    public void openAcitvity_findPrimes(View view){
        Intent intent = new Intent(this, Activity_Thread.class);
        startActivity(intent);
    }

    public void openAcitvity_location(View view){
        Intent intent = new Intent(this, Activity_Location.class);
        startActivity(intent);
    }
}