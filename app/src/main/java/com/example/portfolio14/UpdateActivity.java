package com.example.portfolio14;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = new DatabaseManager(this);

        //Create the view
        updateView();

    } //end onCreate

    public void updateView() {
        ArrayList<Candy> candies = manager.selectAll();

        //Check if there is info in database
        if (candies.size() > 0) {
            ScrollView scrollView = new ScrollView(this);

            GridLayout gridLayout =  new GridLayout(this);

            gridLayout.setRowCount(candies.size());
            gridLayout.setColumnCount(4); //id, name, price, button


            //Arrays to hold info to be placed on screen
            TextView [] idArray = new TextView[candies.size()];

            EditText [][] namePriceArray = new EditText[candies.size()][2];

            Button [] buttons = new Button[candies.size()];

            ButtonHandler buttonHandler = new ButtonHandler();

            //Get the dimensions of the screen to make all 4 items fit on one line
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);

            int width = size.x;

            //Proces arraylist and build the layout

            int sub = 0;

            for (Candy candy : candies) {
                //Create Textviews for id
                idArray[sub] = new TextView(this);
                idArray[sub].setGravity(Gravity.CENTER);
                idArray[sub].setText("" + candy.getId());

                //Edit texts for name and price: [0]-name [1]-price
                namePriceArray[sub][0] = new EditText(this);
                namePriceArray[sub][1] = new EditText(this);

                namePriceArray[sub][0].setText(candy.getName());
                namePriceArray[sub][1].setText("" + candy.getPrice());
                namePriceArray[sub][1].setInputType(InputType.TYPE_CLASS_NUMBER);

                namePriceArray[sub][0].setId(10 * candy.getId());
                namePriceArray[sub][1].setId(10 * candy.getId() + 1);

                //Buttons
                buttons[sub] = new Button(this);
                buttons[sub].setText("Update");
                buttons[sub].setId(candy.getId());

                buttons[sub].setOnClickListener(buttonHandler);


                //Add the items to gridLayout
                gridLayout.addView(idArray[sub], (int) (width * .1), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(namePriceArray[sub][0], (int) (width * .3), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(namePriceArray[sub][1], (int) (width * .3), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(buttons[sub], (int) (width * .3), ViewGroup.LayoutParams.WRAP_CONTENT);

                sub++;

            } //end for

            //Add gridLayout to scrollView
            scrollView.addView(gridLayout);

            //Display scrollview
            setContentView(scrollView);

        } //end if

    } //end updateView

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Get the name and price from the edit text fields
            int candyId = v.getId();

            EditText nameET = findViewById(10 * candyId);
            EditText priceET = findViewById(10 * candyId + 1);

            String nameString = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            //Convert price to a double
            try {
                double price = Double.parseDouble(priceString);

                manager.updateByID(candyId, nameString, price);

            }
            catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this, "Price Error", Toast.LENGTH_SHORT).show();
            }





        }
    } //end buttonHanfler

}
