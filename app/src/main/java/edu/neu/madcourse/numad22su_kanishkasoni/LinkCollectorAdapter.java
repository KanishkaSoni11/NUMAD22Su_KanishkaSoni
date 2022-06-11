package edu.neu.madcourse.numad22su_kanishkasoni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkCollectorAdapter extends RecyclerView.Adapter<LinkCollectorViewHolder> {

    private final List<LinkCollector> linkCollectorList;
    private final Context context;

    public LinkCollectorAdapter(List<LinkCollector> linkCollectorList, Context context) {
        this.linkCollectorList = linkCollectorList;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkCollectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create an instance of the viewholder by passing it the layout inflated as view and no root.
        return new LinkCollectorViewHolder(LayoutInflater.from(context).inflate(R.layout.item_link_collector, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkCollectorViewHolder holder, int position) {
        holder.bindThisData(linkCollectorList.get(position));
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return linkCollectorList.size();
    }

}
