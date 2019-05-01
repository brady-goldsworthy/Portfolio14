package com.example.portfolio14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager = new DatabaseManager(this);

        updateView();

    } // end onCreate

    public void updateView() {
        ArrayList<Candy> candies = databaseManager.selectAll();

        //Creating the layout
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        for (Candy candy : candies) {
            //Create radiobutton to be placed into radio group
            RadioButton radioButton = new RadioButton(this);

            radioButton.setId(candy.getId());
            radioButton.setText(candy.getName());

            //Add to radio group
            radioGroup.addView(radioButton);


        }

        //Setup handler for radiobuttons
        RadioButtonHandler handler = new RadioButtonHandler();
        radioGroup.setOnCheckedChangeListener(handler);

        //Create a back button
        Button backBtn = new Button(this);
        backBtn.setText("Go back");
        //Set onclick property
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Add radiogroup to the scrollview
        scrollView.addView(radioGroup);

        //Add scrollview to relative layout
        layout.addView(scrollView);

        //Params for back button location
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 30);

        //Add back button to layout using params
        layout.addView(backBtn, params);

        //display layout
        setContentView(layout);

    } //end updateView

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            databaseManager.deleteByID(checkedId);
            Toast.makeText(DeleteActivity.this, "candy deleted", Toast.LENGTH_SHORT).show();

            updateView();
        }
    } //end RadioButtonHandler

} //end DeleteActivity

