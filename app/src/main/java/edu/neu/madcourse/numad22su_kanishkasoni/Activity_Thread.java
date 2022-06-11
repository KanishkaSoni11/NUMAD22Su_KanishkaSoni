package edu.neu.madcourse.numad22su_kanishkasoni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class Activity_Thread extends AppCompatActivity {
    private Handler textHandler = new Handler();
    TextView textView_number;
    TextView textView_numberBeingChecked;
    private boolean terminateSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        textView_number = findViewById(R.id.textView_number);
        textView_numberBeingChecked = findViewById(R.id.textView_numberChecked);

    }

    public void onClick_findPrimes(View view) {
        terminateSearch = false;
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();

    }

    @Override
    public void onBackPressed() {
        if (!terminateSearch) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you want to Terminate the process?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            super.onBackPressed();
        }

    }


    public void onClick_TerminateSearch(View view) {
        terminateSearch = true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
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
                        textView_number.setText("Prime Number: " + finalI);
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