
package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    TextView textView;
    boolean draw=false;
    boolean gameActive=true;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int[][] gameWin={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_TicTacToe);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    public void dropIn(View view){
        //0 for yellow and 1 for red
        ImageView counter=(ImageView)view;

        int index=Integer.parseInt(counter.getTag().toString());

        if(activePlayer==0&&gameState[index]==2&&gameActive){
            counter.setTranslationY(-1500);
            counter.setImageResource(R.drawable.yellow);
            activePlayer=1;
            gameState[index]=0;
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);
        } else if(activePlayer==1&&gameState[index]==2&&gameActive){
            counter.setTranslationY(-1500);
            counter.setImageResource(R.drawable.red);
            activePlayer=0;
            gameState[index]=1;
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);
        }

        for(int [] win:gameWin){
            if(gameState[win[0]]==gameState[win[1]]&&gameState[win[1]]==gameState[win[2]]&&gameState[win[0]]!=2&&gameActive){
                String winner="";
                if(activePlayer==0){
                    winner="Red is winner";
                }else if(activePlayer==1){
                    winner="Yellow is winner";
                }
                gameActive=false;
                Button button=findViewById(R.id.button);
                textView.setText(winner);
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        }
        draw=false;
        for (int j : gameState) {
            if (j != 2) {
                draw = true;
            } else {
                draw = false;
                break;
            }
        }
        if(draw &&textView.getVisibility()==View.INVISIBLE){
            String winner="Match draw";
            gameActive=false;
            Button button=findViewById(R.id.button);
            textView.setText(winner);
            textView.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }

    }
    public void playAgainButton(View view){
        Button playAgainButton=findViewById(R.id.button);
        TextView textView=findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=findViewById(R.id.gridLayoutId);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            activePlayer=0;
            draw=false;
            gameActive=true;
            for(int j=0;j<gameState.length;j++){
                gameState[i]=2;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}