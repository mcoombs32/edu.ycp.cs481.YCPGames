package edu.ycp.cs481.ycpgames;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by michaelcoombs on 11/5/13.
 */
public class DotsDraw extends SurfaceView implements SurfaceHolder.Callback {


    private Bitmap mBackground;
    private GameThread thread;
    private DotsGrid grid;
    private DotsGame game;
    private Paint paintOne, paintTwo;
    private DotsGridCell start,end;
    private Toast invalidToast;

    public DotsDraw(Context context, int[] screen){
        super(context);
        getHolder().addCallback(this);

        game = new DotsGame();
        grid = new DotsGrid(screen);
        invalidToast = Toast.makeText(context,"Invalid move, please select another!",Toast.LENGTH_SHORT);

        InputStream is = context.getResources().openRawResource(R.drawable.dot);
        try {
            mBackground =  BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        paintOne = new Paint();
        paintOne.setColor(Color.parseColor("#108642"));
        paintOne.setStyle(Paint.Style.FILL);
        paintOne.setAntiAlias(true);
        paintOne.setTextSize(48);
        paintOne.setTypeface(Typeface.DEFAULT_BOLD);
        paintOne.setShadowLayer(1.2f,1,1,Color.GRAY);
        paintOne.setStrokeWidth(12);

        start = null;
        end = null;

        paintTwo = new Paint();
        paintTwo.setColor(Color.parseColor("#c0c0c0"));
        paintTwo.setAntiAlias(true);
        paintTwo.setTextSize(48);
        paintTwo.setTypeface(Typeface.DEFAULT_BOLD);
        paintTwo.setShadowLayer(1.2f,1,1,Color.GRAY);
        paintTwo.setStyle(Paint.Style.FILL);
        paintTwo.setStrokeWidth(12);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawRGB(255, 255, 255);
        Paint tempPaint = null;
        game.board.isGameOver();
        paintTwo.setTextSize(48);
        paintOne.setTextSize(48);
        float textWidth = paintTwo.measureText("Player 2: "+game.getPlayerTwoScore());
        canvas.drawText("Player 1: " + game.getPlayerOneScore(), 20, canvas.getHeight()-(grid.getCell(0,0).getLength()/2), paintOne);
        canvas.drawText("Player 2: "+game.getPlayerTwoScore(),canvas.getWidth()-(textWidth+20),canvas.getHeight()-(grid.getCell(0,0).getLength()/2),paintTwo);

        for(int i = 0; i < grid.getGridWidth()-1;i++){
            for (int j = 0; j < grid.getGridLength()-1; j++) {
               int[] center = grid.getCell(i,j).getCenter();
               if ((game.board.getLineAt(i, j, Direction.UP) != GameVal.EMPTY)){
                   if ((game.board.getLineAt(i, j, Direction.UP) == GameVal.PLAYER_ONE)){
                       tempPaint = paintOne;
                   }else if ((game.board.getLineAt(i, j, Direction.UP) == GameVal.PLAYER_TWO)){
                       tempPaint = paintTwo;
                   }
                   canvas.drawLine(center[0],center[1], grid.getCell(i+1,j).getCenter()[0],grid.getCell(i+1,j).getCenter()[1],tempPaint);
               }
               if ((game.board.getLineAt(i,j,Direction.LEFT) != GameVal.EMPTY)){
                   if ((game.board.getLineAt(i, j, Direction.LEFT) == GameVal.PLAYER_ONE)){
                       tempPaint = paintOne;
                   }else if ((game.board.getLineAt(i, j, Direction.LEFT) == GameVal.PLAYER_TWO)){
                       tempPaint = paintTwo;
                   }
                   canvas.drawLine(center[0],center[1], grid.getCell(i,j+1).getCenter()[0],grid.getCell(i,j+1).getCenter()[1],tempPaint);
               }
               if ((game.board.getLineAt(i,j,Direction.RIGHT) != GameVal.EMPTY) && (i != grid.getGridWidth()-1)){
                   if ((game.board.getLineAt(i, j, Direction.RIGHT) == GameVal.PLAYER_ONE)){
                       tempPaint = paintOne;
                   }else if ((game.board.getLineAt(i, j, Direction.RIGHT) == GameVal.PLAYER_TWO)){
                       tempPaint = paintTwo;
                   }
                   canvas.drawLine(grid.getCell(i+1,j).getCenter()[0],grid.getCell(i+1,j).getCenter()[1], grid.getCell(i+1,j+1).getCenter()[0],grid.getCell(i+1,j+1).getCenter()[1],tempPaint);
               }
               if ((game.board.getLineAt(i,j,Direction.DOWN) != GameVal.EMPTY) && (j != grid.getGridLength()-1)){
                   if ((game.board.getLineAt(i, j, Direction.DOWN) == GameVal.PLAYER_ONE)){
                       tempPaint = paintOne;
                   }else if ((game.board.getLineAt(i, j, Direction.DOWN) == GameVal.PLAYER_TWO)){
                       tempPaint = paintTwo;
                   }
                   canvas.drawLine(grid.getCell(i,j+1).getCenter()[0],grid.getCell(i,j+1).getCenter()[1], grid.getCell(i+1,j+1).getCenter()[0],grid.getCell(i+1,j+1).getCenter()[1],tempPaint);
               }
               if(game.board.grid[i][j].isNodeFilled() == GameVal.PLAYER_ONE){
                   tempPaint = paintOne;
                   tempPaint.setTextSize(100);
                   canvas.drawText("1",center[0]+(grid.getCell(i,j).getWidth()/2),center[1]+(grid.getCell(i,j).getLength()/2),tempPaint);

               }else if(game.board.grid[i][j].isNodeFilled() == GameVal.PLAYER_TWO){
                   tempPaint = paintTwo;
                   tempPaint.setTextSize(100);
                   canvas.drawText("2",center[0]+(grid.getCell(i,j).getWidth()/2),center[1]+(grid.getCell(i,j).getLength()/2),tempPaint);

               }
            }
        }

        for(int i = 0; i < grid.getGridWidth();i++){
            for (int j = 0; j < grid.getGridLength(); j++) {
                int[] center = grid.getCell(i,j).getCenter();
                //Log.d(TAG,"w:"+(mBackground.getScaledWidth(canvas)/2)+", h:"+(mBackground.getScaledHeight(canvas)/2));
                canvas.drawBitmap(mBackground,center[0] - (mBackground.getWidth()/2),center[1] - (mBackground.getHeight()/2),null);
            }
        }
    }

    public DotsGridCell mFindNearestDot(int x, int y){
       DotsGridCell temp = null;

        for(int i = 0; i < grid.getGridWidth();i++){
            for (int j = 0; j < grid.getGridLength(); j++) {
                if (x > grid.getCell(i,j).getX() && x <= grid.getCell(i,j).getX1()){
                    if (y > grid.getCell(i,j).getY() && y <= grid.getCell(i,j).getY1()){
                        temp = grid.getCell(i,j);
                    }
                }
            }
        }
        return temp;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    public void setRunning(boolean run){
        thread.setRunning(run);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
       // if (game.settings.isSinglePlayer() && game.playerOne.isPlayersTurn()){
        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                final float x = event.getX();
                final float y = event.getY();
                start = mFindNearestDot((int)x,(int)y);
                if(start == null)
                    break;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                final float endX = event.getX();
                final float endY = event.getY();
                end = mFindNearestDot((int)endX, (int)endY);
                if (end == null || end.equals(start))
                    break;
                else{
                    mUpdateGrid(start,end);
                    break;
                }
            default:
        }
        //}
        return true;

    }

    private void mUpdateGrid(DotsGridCell start, DotsGridCell end) {
        int isMoveValid;

        float dx = end.getX()-start.getX();
        float dy = end.getY()-start.getY();
        if (Math.abs(dx) > Math.abs(dy)){
           if (dx > 0){
               if (start.getArrY() == game.board.getGridHeight()){
                    isMoveValid =  game.makeMove(start.getArrX(),start.getArrY()-1,Direction.DOWN);
               }else
                    isMoveValid =  game.makeMove(start.getArrX(),start.getArrY(),Direction.UP);
           }else

               if (start.getArrY() == game.board.getGridWidth()){
                    isMoveValid =  game.makeMove(end.getArrX(),end.getArrY()-1,Direction.DOWN);
               }else
                    isMoveValid =  game.makeMove(end.getArrX(),end.getArrY(),Direction.UP);
        }else{
            if (dy > 0){
                if (start.getArrX() == game.board.getGridWidth()){
                    isMoveValid = game.makeMove(start.getArrX()-1,start.getArrY(),Direction.RIGHT);
                }else
                    isMoveValid = game.makeMove(start.getArrX(),start.getArrY(),Direction.LEFT);
            }else
            if (start.getArrX() == game.board.getGridWidth()){
                isMoveValid = game.makeMove(end.getArrX()-1,end.getArrY(),Direction.RIGHT);
            }else
                isMoveValid = game.makeMove(end.getArrX(),end.getArrY(),Direction.LEFT);
        }
        if (isMoveValid == -2){
            invalidToast.show();
        }else
            invalidate();
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


        @Override
        public void run() {
            while (run) {
                Canvas c = null;
                try{
                    c = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        if(c!= null){
                            gameView.onDraw(c);
                        }

                    }
                }finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                if(game.board.isGameOver() != GameVal.IN_PROGRESS)
                   setRunning(false);

                }
            }
    }


}




