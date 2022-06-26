package edu.neu.madcourse.numad22su_kanishkasoni;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AssetHistory_ViewHolder extends  RecyclerView.ViewHolder{
    public TextView priceUsd;
    public TextView date;

    public AssetHistory_ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.priceUsd = itemView.findViewById(R.id.price);
        this.date = itemView.findViewById(R.id.date);


    }

    public void bindThisData(AssetHistory_Collector theLinkToBind) {
        // sets the name of the person to the name textview of the viewholder.
        priceUsd.setText("Price: " + theLinkToBind.getPrice());
        // sets the url of the person to the age textview of the viewholder.
        date.setText(("Date: " + theLinkToBind.getDate()));

    }
}
