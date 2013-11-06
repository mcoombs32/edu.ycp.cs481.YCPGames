package edu.ycp.cs481.ycpgames;

import edu.ycp.cs481.ycpgames.util.SystemUiHider;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceView;
import android.util.Log;

public class DotsActivity extends Activity {

    private int screenHeight,screenWidth;
    private DotsGame game;
    DotsDraw draw;
    private DotsGrid grid;
    final static String TAG = "DotsActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        draw = new DotsDraw(this);
        setContentView(draw);


        game = new DotsGame();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        int screen[] = {screenWidth,screenHeight};
        grid = new DotsGrid(screen);


    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onStop(){
        super.onStop();

    }


    private void mDotsUpdate(){

    }
}
