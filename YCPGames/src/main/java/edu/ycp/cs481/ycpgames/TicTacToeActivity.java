package edu.ycp.cs481.ycpgames;

import edu.ycp.cs481.ycpgames.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TicTacToeActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private static final String TAG = "TicTacToeActivity";

    private static final int X = 1;
    private static final int O = 2;
    private SystemUiHider mSystemUiHider;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.unregisterReceiver()
        setContentView(R.layout.activity_tictactoe);
        game =  new TicTacToeGame();
        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);
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

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });



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
                if (game.whosTurn() == 1){
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
                if (game.whosTurn() == 1){
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
                if (game.whosTurn() == 1){
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
                if (game.whosTurn() == 1){
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
                if (game.whosTurn() == 1){
                    gameOver = game.move(1,2);
                    mUpdateView();
                }

            }
        });

        bottomLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Received touch event on button (0,0).");
                if (game.whosTurn() == 1){
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
                if (game.whosTurn() == 1){
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
                if (game.whosTurn() == 1){
                    gameOver = game.move(0,2);
                    mUpdateView();
                }
                gameOverCheck();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    protected void onStop(){
        super.onStop();
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */


    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

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
        if(gameOver > 0){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.game_over);
            switch(gameOver){
                case -1:
                    alertDialogBuilder.setMessage(R.string.draw);
                    break;
                case 1:
                    alertDialogBuilder.setMessage(R.string.player_win);
                    break;
                case 2:
                    alertDialogBuilder.setMessage(R.string.comp_win);
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

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
