package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity_Thread extends AppCompatActivity {
    private static final String TAG = "Activity_Thread";
    private Handler textHandler = new Handler();
    TextView textView_number;
    TextView textView_numberBeingChecked;
    private boolean terminateSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        textView_number = findViewById(R.id.textView_number);
        textView_numberBeingChecked = findViewById(R.id.textView_numberChecked);
    }

    public void onClick_findPrimes(View view) {
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();

    }

    public void onClick_TerminateSearch(View view){
        terminateSearch = true;

    }

    class runnableThread implements Runnable {
        boolean isPrime(int n) {
            if (n == 2)
                return true;
            else if (n % 2 == 0)
                return false;

            for (int i = 3; i <= Math.sqrt(n); i += 2) {
                if (n % i == 0)
                    return false;
            }
            return true;
        }

        @Override
        public void run() {
            int i = 2;
            while (!terminateSearch) {
                final int finalI = i;

                //The handler changes the TextView running in the UI thread.
                textHandler.post(() -> {
                    textView_numberBeingChecked.setText("Number being Checked: " + finalI);
                    if (isPrime(finalI)) {
                        textView_number.setText("Prime Number: " + finalI );
                    }
                });
                try {
                    Thread.sleep(50); //Makes the thread sleep or be inactive for 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }
}