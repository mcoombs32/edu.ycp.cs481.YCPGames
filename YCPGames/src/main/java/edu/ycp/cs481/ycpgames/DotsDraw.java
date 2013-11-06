package edu.ycp.cs481.ycpgames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.util.Log;
/**
 * Created by michaelcoombs on 11/5/13.
 */
public class DotsDraw extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap mBackground;
    SurfaceHolder surfaceHolder;
    private GameThread thread;

    public DotsDraw(Context context){
        super(context);
        surfaceHolder = getHolder();
        getHolder().addCallback(this);

        mBackground = BitmapFactory.decodeResource(getResources(),R.drawable.dot);
    }



    @Override
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(mBackground,30,30,null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }

        }


}
class GameThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private DotsDraw gameView;
    private boolean run = false;

    public GameThread(SurfaceHolder surfaceHolder, DotsDraw gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    @Override
    public void run() {
        while (run) {
            Canvas c = null;
            try{
                c = surfaceHolder.lockCanvas();
                //Log.d("DotsDraw",c.toString());

                synchronized (surfaceHolder) {
                    gameView.onDraw(c);
                }
            }finally {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
        }
    }
}
