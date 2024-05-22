package com.example.akash_task9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.AkashTask9P.R;


public class MainActivity extends Activity {

    private Button createButton;
    private Button showButton;
    private Button showOnMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButton = findViewById(R.id.createButton);
        showButton = findViewById(R.id.showButton);
        showOnMapButton = findViewById(R.id.showOnMapButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateAdvert = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intentCreateAdvert);
            }
        });

        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowOnMap = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intentShowOnMap);
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowItems = new Intent(MainActivity.this, ListAllActivity.class);
                startActivity(intentShowItems);
            }
        });

    }
}
