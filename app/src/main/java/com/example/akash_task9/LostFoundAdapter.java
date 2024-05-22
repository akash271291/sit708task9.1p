package com.example.akash_task9;

import com.example.AkashTask9P.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import java.util.List;

public class LostFoundAdapter extends RecyclerView.Adapter<LostFoundAdapter.ViewHolder> {

    private List<ItemRecord> items;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public LostFoundAdapter(Context context, List<ItemRecord> items, OnItemClickListener onItemClickListener) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemRecord item = items.get(position);
        holder.titleTextView.setText(item.getTitle());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    private void saveItemsToSharedPreferences(Context context, List<ItemRecord> itemsList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LostFoundPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemsList);
        editor.putString("items", json);
        editor.apply();
    }
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        saveItemsToSharedPreferences(inflater.getContext(), items);
    }
}
