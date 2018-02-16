package com.tungx.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int active_player = 0;
    int win_player = 0;
    boolean isActive = true;
    String winText = "";
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winning_position = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tapped_counter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tapped_counter] == 2 && isActive) {
            gameState[tapped_counter] = active_player;
            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.o);
                active_player = 1;
                win_player = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                active_player = 0;
                win_player = 2;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] win_count : winning_position) {
                if(gameState[win_count[0]] == gameState[win_count[1]] &&
                        gameState[win_count[1]] == gameState[win_count[2]] &&
                        gameState[win_count[0]] != 2) {
                    isActive = false;
                    winText = "Player " + win_player + " has won";
                    TextView winner_text = (TextView) findViewById(R.id.winner_text);
                    winner_text.setText(winText);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain_layout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean game_over = true;
                    for(int drawCounter : gameState) {
                        if(drawCounter == 2) {
                            game_over = false;
                        }
                    }
                    if(game_over) {
                        winText = "It's a draw";
                        TextView winner_text = (TextView) findViewById(R.id.winner_text);
                        winner_text.setText(winText);
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain_layout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void reset(View view) {
        isActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain_layout);
        layout.setVisibility(View.INVISIBLE);
        int active_player = 0;
        int win_player = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout grid_layout = (GridLayout) findViewById(R.id.grid_layout);
        for(int i = 0; i < grid_layout.getChildCount(); i++) {
            ((ImageView) grid_layout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
