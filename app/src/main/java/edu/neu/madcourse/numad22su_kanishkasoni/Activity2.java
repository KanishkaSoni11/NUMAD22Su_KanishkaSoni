package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void onClick_Act2(View view) {
        TextView textView = (TextView) findViewById(R.id.pressed_text);

        if (view.getId() == R.id.Button_A) {
            textView.setText("Pressed : A");
        } else if (view.getId() == R.id.Button_B) {
            textView.setText("Pressed : B");
        }
        else if (view.getId() == R.id.Button_C) {
            textView.setText("Pressed : C");
        }
        else if (view.getId() == R.id.Button_D) {
            textView.setText("Pressed : D");
        }
        else if (view.getId() == R.id.Button_E) {
            textView.setText("Pressed : E");
        }
        else if (view.getId() == R.id.Button_F) {
            textView.setText("Pressed : F");
        }



    }
}