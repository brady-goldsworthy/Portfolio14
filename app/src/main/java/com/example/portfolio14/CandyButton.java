package com.example.portfolio14;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class CandyButton extends AppCompatButton {

    private Candy candy;

    public CandyButton(Context context, Candy newCandy) {
        super(context);
        candy = newCandy;
    }

    public double getPrice() {
        return candy.getPrice();
    }

}
