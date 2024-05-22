package com.example.akash_task9;

import com.example.AkashTask9P.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetailsActivity extends Activity {

    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewDescription;
    private TextView textViewDate;
    private TextView textViewLocation;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        textViewName = findViewById(R.id.textViewName);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDate = findViewById(R.id.textViewDate);
        textViewLocation = findViewById(R.id.textViewLocation);
        removeButton = findViewById(R.id.removeButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("ITEM_TITLE");
        String description = intent.getStringExtra("ITEM_DESCRIPTION");
        String phone = intent.getStringExtra("ITEM_PHONE");
        String date = intent.getStringExtra("ITEM_DATE");
        String location = intent.getStringExtra("ITEM_LOCATION");

        textViewName.setText(title);
        textViewPhone.setText("Contactor: " + phone);
        textViewDescription.setText("Description: " + description);
        textViewDate.setText("Date: " + date);
        textViewLocation.setText("At " +location);

        int position = intent.getIntExtra("ITEM_POSITION", -1);
        removeButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("ITEM_REMOVED", true);
            returnIntent.putExtra("ITEM_POSITION", position);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

    }
}
