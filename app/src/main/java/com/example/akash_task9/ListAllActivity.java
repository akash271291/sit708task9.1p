package com.example.akash_task9;

import com.example.AkashTask9P.R;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LostFoundAdapter adapter;
    private List<ItemRecord> itemList;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        boolean itemRemoved = data.getBooleanExtra("ITEM_REMOVED", false);
                        int position = data.getIntExtra("ITEM_POSITION", -1);
                        if (itemRemoved && position != -1) {
                            adapter.removeItem(position);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = loadItems();

        adapter = new LostFoundAdapter(this, itemList, position -> {
            Intent intent = new Intent(ListAllActivity.this, ItemDetailsActivity.class);
            intent.putExtra("ITEM_TITLE", itemList.get(position).getTitle());
            intent.putExtra("ITEM_DESCRIPTION", itemList.get(position).getDescription());
            intent.putExtra("ITEM_PHONE", itemList.get(position).getPhone());
            intent.putExtra("ITEM_DATE", itemList.get(position).getDate());
            intent.putExtra("ITEM_LOCATION", itemList.get(position).getLocation());
            intent.putExtra("ITEM_POSITION", position);
            activityResultLauncher.launch(intent);
        });

        recyclerView.setAdapter(adapter);
    }

    private List<ItemRecord> loadItems() {
        SharedPreferences sharedPreferences = getSharedPreferences("LostFoundPrefs", MODE_PRIVATE);
        String itemsJson = sharedPreferences.getString("items", "[]");
        Type type = new TypeToken<ArrayList<ItemRecord>>(){}.getType();
        return new Gson().fromJson(itemsJson, type);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        itemList.addAll(loadItems());
        adapter.notifyDataSetChanged();
    }
}
