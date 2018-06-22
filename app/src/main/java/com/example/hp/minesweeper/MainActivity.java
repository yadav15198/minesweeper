package com.example.hp.minesweeper;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,View.OnLongClickListener  {
    LinearLayout rootlayout;
    ArrayList<LinearLayout> rows;
    int size1 = 5;
    int size2 = 5;
  static   MS board[][];
    int No_Of_Mines =5;
    int arr1[] = {-1, -1, -1, 0, 1, 1, 1, 0};
    int arr2[] = {-1, 0, 1, 1, 1, 0, -1, -1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootlayout = findViewById(R.id.rootlayout);
        setupboard();
        setNeighbour();
     //   showAll();
    }
    public void setmines(){
        int l=No_Of_Mines ;
        while(l!=0){
            Random random = new Random();
            int i = random.nextInt(size1);
            int j = random.nextInt(size2);
            MS button = board[i][j];
          //  Log.d("lalal"," Mines set at = "+ i + " " + j);
           // button.setText("-1");
            if(board[i][j].getValue() != -1){
            board[i][j].setValue(-1);
            l--;}
        }
    }
    public void showMines(){
        MS button = null;
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                button = board[i][j];
                if(button.getValue() == -1){
                    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.bomb));
                }
            }
        }
    }


public void setNeighbour() {
    for (int i = 0; i < size1; i++) {
        for (int j = 0; j < size2; j++) {
            MS button = null;
            button = board[i][j];
            int X = 0,Y = 0;
            if (button.value == -1) {
                for (int l = 0; l < 8; l++) {
                    X = i + arr1[l];
                    Y = j + arr2[l];
                    if( (X >= 0) && (X < size1) && (Y >= 0) &&  (Y < size2) ){
                        if (board[X][Y].value != -1) {

                            board[X][Y].value++;
                            Log.d("lalal",String.valueOf(X) + "," + String.valueOf(Y) + " Vslue = " + board[X][Y].value);

                        }
                    }
                }
            }
        }
    }

}
    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
    }

    public void setupboard() {
        rows = new ArrayList<>();
        board = new MS[size1][size2];
        rootlayout.removeAllViews();
        for (int i = 0; i < size1; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
            linearLayout.setLayoutParams(layoutParams);
            rootlayout.addView(linearLayout);
            rows.add(linearLayout);
        }
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                MS button = new MS(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                button.setLayoutParams(layoutParams);
                LinearLayout row = rows.get(i);
                row.addView(button);
                button.i = i;
                button.j = j;
                button.setOnLongClickListener(this);
                button.setOnClickListener(this);
                board[i][j] = button;
                button.value = 0;

            }
        }
        setmines();
    }
public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater Inflater =getMenuInflater();
    Inflater.inflate(R.menu.main_menu,menu);
    return true;

}
public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item1){
            setupboard();
            setNeighbour();
           }
        else if(id == R.id.size5){
            size2 =5;
            size1 = 5;
            No_Of_Mines = (size1*size2)/5;
            setupboard();
            setNeighbour();

        }
        else if(id == R.id.size7){
            size1=7;
            size2 =7;
            No_Of_Mines = (size1*size2)/6;
            setupboard();
           setNeighbour();
        }

        else if(id == R.id.size10){
            size2 = 10;
            size1 = 10;
            No_Of_Mines = (size1*size2)/6;
            setupboard();
            setNeighbour();
        }
     return super.onOptionsItemSelected(item);
}

    private void showAll() {
        MS button = null;
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                button = board[i][i];
                Log.d("Valuesss" , i + " " + j + " = " + board[i][j].value);
                board[i][j].setText(String.valueOf(board[i][j].value));

            }
        }
    }
public void revealneighbour(MS button) {
    button.setText(button.value + "");
    int X, Y;
    MS newbutton = null;
    button.isRevealed = true;

    for (int k = 0; k < 8; k++) {
        X = button.i + arr1[k];
        Y = button.j + arr2[k];

        if ((X >= 0) && (X < size1) && (Y >= 0) && (Y < size2)) {
            newbutton = board[X][Y];
            if (newbutton.value == 0 && !newbutton.isRevealed) {
                newbutton.setText(newbutton.value + "");
                revealneighbour(newbutton);
            } else {
                if(newbutton.getValue() > 0) {
                    newbutton.setText(newbutton.value + "");
                    newbutton.isRevealed = true;
                }
            }
        }
    }
}

    @Override
    public void onClick(View view) {
     MS button = (MS)view;
     if(button.getValue() > 0){
      button.setText(button.value + "");
      button.isRevealed = true;
     }
     else if(button.getValue() == -1){

            MS mbutton = null;
            for(int i = 0; i<size1; i++){
                for(int j = 0; j<size2; j++) {
                    mbutton = board[i][j];
                    if (mbutton.getValue() != -1) {
                        mbutton.setEnabled(false);
                    }
                }
            }
            showMines();
         Toast.makeText(this,"GameOver",Toast.LENGTH_LONG).show();
        }
        else if(button.getValue() == 0){
         revealneighbour(button);
     }
    }

    @Override
    public boolean onLongClick(View view) {
        MS button = (MS) view;
        if (button.isFlagged == false) {
           // button.setBackgroundDrawable(getResources().getDrawable(R.drawable.flag));
            button.setText("F");
            button.isFlagged = true;
        } else {
                button.setText("");
                button.isFlagged = false;

        }

        return true;
    }

}

