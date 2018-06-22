package com.example.hp.minesweeper;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MS extends AppCompatButton  {
 public   int i,j;
 int value;
private boolean checkMInes = false;
   public boolean isFlagged = false;
   public boolean isRevealed;

    public MS(Context context) {
        super(context);
        isRevealed = false;

    }
    public void setXY(int x ,int y){
        i = x;
        j =y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}
