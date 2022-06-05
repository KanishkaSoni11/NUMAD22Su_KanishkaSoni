package edu.neu.madcourse.numad22su_kanishkasoni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LinkCollector_Dialog extends AppCompatDialogFragment {
    private EditText editText_name;
    private EditText editText_url;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_linkcollector, null);

        builder.setView(view).setTitle("Add").setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        editText_name = view.findViewById(R.id.item_name);
        editText_url = view.findViewById(R.id.item_url);
        return builder.create();
    }

}
