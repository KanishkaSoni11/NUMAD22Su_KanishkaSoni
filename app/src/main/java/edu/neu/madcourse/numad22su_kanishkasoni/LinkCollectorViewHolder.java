package edu.neu.madcourse.numad22su_kanishkasoni;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkCollectorViewHolder extends RecyclerView.ViewHolder{
    public TextView nameTV;
    public TextView urlTV;

    public LinkCollectorViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nameTV = itemView.findViewById(R.id.name);
        this.urlTV = itemView.findViewById(R.id.url);
    }

    public void bindThisData(LinkCollector theLinkToBind) {
        // sets the name of the person to the name textview of the viewholder.
        nameTV.setText(theLinkToBind.getName());
        // sets the url of the person to the age textview of the viewholder.
        urlTV.setText(String.valueOf(theLinkToBind.getUrl()));
    }
}
