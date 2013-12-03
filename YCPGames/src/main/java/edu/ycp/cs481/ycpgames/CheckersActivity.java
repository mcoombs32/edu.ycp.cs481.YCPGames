package edu.ycp.cs481.ycpgames;


import android.app.Activity;
import android.graphics.Point;

import android.os.Bundle;

import android.util.Log;
import android.view.Display;




public class CheckersActivity extends Activity {
    private int screenHeight,screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        Log.d("CheckersActivity", "Screen size: (" + size.x + "," + size.y + ")");

        int screen[] = {screenWidth,screenHeight};
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
