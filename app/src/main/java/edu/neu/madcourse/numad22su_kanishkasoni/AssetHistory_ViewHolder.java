package edu.neu.madcourse.numad22su_kanishkasoni;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class AssetHistory_ViewHolder extends  RecyclerView.ViewHolder{
    public TextView priceUsd;
    public TextView date;
    public TextView time;

    public AssetHistory_ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.priceUsd = itemView.findViewById(R.id.price);
        this.date = itemView.findViewById(R.id.date);
        this.time = itemView.findViewById(R.id.time);


    }

    public void bindThisData(AssetHistory_Collector theLinkToBind) {
        priceUsd.setText("Price: " + theLinkToBind.getPrice());
        date.setText(("Date: " + theLinkToBind.getDate()));
        time.setText(theLinkToBind.getTime());


    }
}
