package edu.ycp.cs481.ycpgames;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageButton;


public class TicTacToeActivity extends Activity {
    private static final int X = 1;
    private static final int O = 2;
    private TicTacToeGame game;
    private ImageButton[] buttons = new ImageButton[23];
    private int[][] tempGrid;
    protected ImageButton topLeftButton;
    protected ImageButton topCenterButton;
    protected ImageButton topRightButton;
    protected ImageButton centerLeftButton;
    protected ImageButton centerButton;
    protected ImageButton centerRightButton;
    protected ImageButton bottomLeftButton;
    protected ImageButton bottomCenterButton;
    protected ImageButton bottomRightButton;
    private int gameOver = 0;
    private static final String TAG = "TicTacToeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        game =  new TicTacToeGame();
        tempGrid = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                tempGrid[i][j] = 0;
            }
        }

        topLeftButton = (ImageButton) findViewById(R.id.topLeftButton);
        topCenterButton = (ImageButton) findViewById(R.id.topCenterButton);
        topRightButton = (ImageButton) findViewById(R.id.topRightButton);
        centerLeftButton = (ImageButton) findViewById(R.id.centerLeftButton);
        centerButton = (ImageButton) findViewById(R.id.centerButton);
        centerRightButton = (ImageButton) findViewById(R.id.centerRightButton);
        bottomLeftButton = (ImageButton) findViewById(R.id.bottomLeftButton);
        bottomCenterButton = (ImageButton) findViewById(R.id.bottomCenterButton);
        bottomRightButton = (ImageButton) findViewById(R.id.bottomRightButton);



        buttons[0] = bottomLeftButton;
        buttons[1] = bottomCenterButton;
        buttons[2] = bottomRightButton;
        buttons[10] = centerLeftButton;
        buttons[11] = centerButton;
        buttons[12] = centerRightButton;
        buttons[20] = topLeftButton;
        buttons[21] = topCenterButton;
        buttons[22] = topRightButton;

        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (2,0).");
                if (game.whosTurn() == 1){
                    gameOver = game.move(2,0);
                    mUpdateView();
                    }
                gameOverCheck();
                }
        });

        topCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (2,1).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(2,1);
                    mUpdateView();
                }
                gameOverCheck();

            }
        });

        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (2,2).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(2,2);
                    mUpdateView();
                }
                gameOverCheck();

            }
        });

        centerLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (1,0).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(1,0);
                    mUpdateView();
                }
                gameOverCheck();

            }
        });

        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (1,1).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(1,1);
                    mUpdateView();
                }
                gameOverCheck();

            }
        });

        centerRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (1,2).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(1,2);
                    mUpdateView();
                }

            }
        });

        bottomLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (0,0).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(0,0);
                    mUpdateView();
                }
                gameOverCheck();

            }
        });

        bottomCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (0,1).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(0,1);
                    mUpdateView();
                }
                gameOverCheck();
            }
        });

        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (0,2).");
                if ((game.whosTurn() == 1) || !Settings.getInstance().isSinglePlayer()){
                    gameOver = game.move(0,2);
                    mUpdateView();
                }
                gameOverCheck();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onStop(){
        super.onStop();
    }



    private void mUpdateView(){
        for (int i = 0; i< game.board.getGridHeight();i++){
            for(int j = 0; j< game.board.getGridWidth(); j++){
                tempGrid[i][j] = game.board.getPieceAt(i,j);
                switch(tempGrid[i][j]){
                    case X:
                        Log.d(TAG,"Drawing X at Button: ("+i+","+j+")");
                        buttons[Integer.parseInt(Integer.toString(i) + Integer.toString(j))].setBackgroundResource(R.drawable.xbutton);
                        break;
                    case O:
                        Log.d(TAG,"Drawing 0 at Button: ("+i+","+j+")");
                        buttons[Integer.parseInt(Integer.toString(i) + Integer.toString(j))].setBackgroundResource(R.drawable.obutton);
                        break;
                    default:
                        Log.d(TAG,"Drawing transparent at Button: ("+i+","+j+")");
                        buttons[Integer.parseInt(Integer.toString(i) + Integer.toString(j))].setBackgroundResource(R.drawable.transbutton);
                        break;
                }
            }
        }
    }
    private void gameOverCheck(){
        if((gameOver > 0)||(gameOver == -1)){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.game_over);
            switch(gameOver){
                case -1:
                    alertDialogBuilder.setMessage(R.string.draw);
                    break;
                case 1:
                    if(Settings.getInstance().isSinglePlayer())
                        alertDialogBuilder.setMessage(R.string.player_win);
                    else
                        alertDialogBuilder.setMessage(R.string.player1_win);
                    break;
                case 2:
                    if(Settings.getInstance().isSinglePlayer())
                        alertDialogBuilder.setMessage(R.string.comp_win);
                    else
                        alertDialogBuilder.setMessage(R.string.player2_win);
                    break;
            }
            alertDialogBuilder
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            game.reset();
                            mUpdateView();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TicTacToeActivity.this.finish();
                        }
                    });
            AlertDialog gameOverAlert = alertDialogBuilder.create();
            gameOverAlert.show();
            gameOver = 0;
        }
    }

}
