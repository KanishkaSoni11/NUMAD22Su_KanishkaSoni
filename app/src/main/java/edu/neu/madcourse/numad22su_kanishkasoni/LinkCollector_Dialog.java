package edu.neu.madcourse.numad22su_kanishkasoni;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LinkCollector_Dialog extends AppCompatDialogFragment {
    private EditText editText_name;
    private EditText editText_url;
    private LinkCollector_Interface dialogInterfaceListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_linkcollector, null);

        builder.setView(view).setTitle("Add").setPositiveButton("Add", (dialog, which) -> {
            String name = editText_name.getText().toString();
            String url = editText_url.getText().toString();
            dialogInterfaceListener.applText(name,url);
            System.out.println("33 here");
        });
        editText_name = view.findViewById(R.id.item_name);
        editText_url = view.findViewById(R.id.item_url);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogInterfaceListener = (LinkCollector_Interface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement Listener");
        }
    }

    public interface LinkCollector_Interface{
        void applText(String name, String url);
    }

}
