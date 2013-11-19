package edu.ycp.cs481.ycpgames;


import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.util.Log;

public class DotsActivity extends Activity {

    private int screenHeight,screenWidth;
    private DotsDraw draw;
    private DotsGrid grid;
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

        grid = new DotsGrid(screen);

        super.onCreate(savedInstanceState);
        draw = new DotsDraw(this, grid);
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
