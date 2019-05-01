package com.example.portfolio14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        databaseManager = new DatabaseManager(this);



    } //end onCreate


    //Add Button
    public void insertCandy(View view) {
        EditText candyNameET = findViewById(R.id.nameEditText);
        EditText priceET = findViewById(R.id.priceEditText);

        String nameStr = candyNameET.getText().toString();
        String priceStr = candyNameET.getText().toString();

        try {
            double price = Double.parseDouble(priceStr);

            //Create candy object
            Candy candy = new Candy(0, nameStr, price);

            DatabaseManager.insert(candy);

        }
        catch (NumberFormatException nfe) {
            Toast.makeText(this, "Price Error", Toast.LENGTH_SHORT).show();
        }

        //Clear the et fields
        candyNameET.setText("");
        priceET.setText("");

    }

    //back button
    public void goBack(View view) {
        finish();
    }
}
