package edu.neu.madcourse.numad22su_kanishkasoni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssetHistory_Adapter extends RecyclerView.Adapter<AssetHistory_ViewHolder> {
    private List<AssetHistory_Collector> assetHistory_collector;
    private final Context context;

    public AssetHistory_Adapter(List<AssetHistory_Collector> assetHistory_collector, Context context) {
        this.assetHistory_collector = assetHistory_collector;
        this.context = context;
    }

    @NonNull
    @Override
    public AssetHistory_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssetHistory_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_asset_history, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AssetHistory_ViewHolder holder, int position) {
        holder.bindThisData(assetHistory_collector.get(position));
    }

    @Override
    public int getItemCount() {
        return assetHistory_collector.size();
    }

    public void setNewList(List<AssetHistory_Collector> assetHistory_collector) {
        this.assetHistory_collector = assetHistory_collector;
        notifyDataSetChanged();

    }
}
