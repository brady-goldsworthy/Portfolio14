package com.example.portfolio14;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager manager;
    private double total;

    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = new DatabaseManager(this);

        //Initialize the total to 0
        total = 0.0;

        scrollView = findViewById(R.id.contentLayout);

        //Calculate width of the button
        Point size =  new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        buttonWidth = size.x / 2;

        updateView();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id = R.id.action_delete) {
            Intent deleteIntent = new Intent(this, DeleteActivity.class);
            return true;
        }
        else if (id == R.id.action_update) {
            Intent updateIntent = new Intent(this, UpdateActivity.class);
            startActivity(updateIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
