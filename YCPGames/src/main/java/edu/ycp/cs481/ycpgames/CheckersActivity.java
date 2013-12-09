package edu.ycp.cs481.ycpgames;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;

import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;




public class CheckersActivity extends Activity {
    private int screenHeight,screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        Log.d("CheckersActivity", "Screen size: (" + size.x + "," + size.y + ")");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Settings.getInstance().setSinglePlayer(sharedPref.getBoolean("pref_key_single_player",true));
        Settings.getInstance().setDifficulty(Integer.parseInt(sharedPref.getString("pref_key_difficulty", "0")));

        int screen[] = {screenWidth,screenHeight};
        CheckersDraw draw = new CheckersDraw(this,screen);
        setContentView(draw);
    }
    @Override
    protected void onPause(){
        super.onPause();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onStop(){
        super.onStop();

    }
}
