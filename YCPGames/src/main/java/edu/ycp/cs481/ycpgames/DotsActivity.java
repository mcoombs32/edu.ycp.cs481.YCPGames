package edu.ycp.cs481.ycpgames;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.util.Log;

public class DotsActivity extends Activity {
    private int screenHeight,screenWidth;

    private DotsDraw draw;
    final static String TAG = "DotsActivity";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        Log.d("DotsActivity","Screen size: ("+size.x+","+size.y+")");

        int screen[] = {screenWidth,screenHeight};
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Settings.getInstance().setSinglePlayer(sharedPref.getBoolean("pref_key_single_player",true));
        Settings.getInstance().setGridHeight(Integer.parseInt(sharedPref.getString("pref_key_grid","5")));
        Settings.getInstance().setGridWidth(Integer.parseInt(sharedPref.getString("pref_key_grid","5")));
        Settings.getInstance().setDifficulty(Integer.parseInt(sharedPref.getString("pref_key_difficulty", "0")));
        draw = new DotsDraw(this, screen);
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
