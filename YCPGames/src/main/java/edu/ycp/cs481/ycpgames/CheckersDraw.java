package edu.ycp.cs481.ycpgames;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import java.util.List;

/**
 * Created by michaelcoombs on 12/2/13.
 */
public class CheckersDraw extends SurfaceView implements SurfaceHolder.Callback {

    private Paint paintOne, paintTwo, circlePaint, rectPaint;
    private Toast invalidToast;
    private CheckersGame game;
    private CheckersGrid grid;
    private GameThread thread;
    private CheckersGridCell selectedPiece;
    private boolean selected = false;

    public CheckersDraw(Context context, int[] screen){
        super(context);
        getHolder().addCallback(this);

        game = new CheckersGame();
        grid = new CheckersGrid(screen);
        invalidToast = Toast.makeText(context,"Invalid move, please select another!",Toast.LENGTH_SHORT);

        paintOne = new Paint();
        paintOne.setColor(Color.parseColor("#108642"));
        paintOne.setStyle(Paint.Style.FILL);
        paintOne.setAntiAlias(true);
        paintOne.setTextSize(48);
        paintOne.setTypeface(Typeface.DEFAULT_BOLD);
        paintOne.setShadowLayer(2.2f, 0, 0, Color.BLACK);
        paintOne.setStrokeWidth(12);

        paintTwo = new Paint();
        paintTwo.setColor(Color.parseColor("#c0c0c0"));
        paintTwo.setAntiAlias(true);
        paintTwo.setTextSize(48);
        paintTwo.setTypeface(Typeface.DEFAULT_BOLD);
        paintTwo.setShadowLayer(2.2f,0,0,Color.GRAY);
        paintTwo.setStyle(Paint.Style.FILL);
        paintTwo.setStrokeWidth(12);

        circlePaint = new Paint();
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(3);

        rectPaint = new Paint();
        rectPaint.setARGB(128,218,165,32);
        rectPaint.setStyle(Paint.Style.FILL);




    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawRGB(255, 255, 255);
        Paint tempPaint;
        CheckersBoard tempBoard = game.getBoard();
        List<int[]> valid;
        if(selected){
           valid =  mSelectPiece(selectedPiece);
           if(valid != null){
               while(valid.iterator().hasNext()){
                   int[] temp = valid.iterator().next();
                   canvas.drawRect(grid.getCell(temp[0],temp[1]).getX()+(grid.getCell(temp[0],temp[1]).getWidth()/4),
                           grid.getCell(temp[0],temp[1]).getY()+(grid.getCell(temp[0],temp[1]).getLength()/4),
                           grid.getCell(temp[0],temp[1]).getX1()-(grid.getCell(temp[0],temp[1]).getWidth()/4),
                           grid.getCell(temp[0],temp[1]).getY1()-(grid.getCell(temp[0],temp[1]).getLength()/4),
                           rectPaint);
               }
           }
        }


        for(int i = 0; i <grid.getGridWidth();i++){
            for (int j = 1; j < grid.getGridLength()-1;j++){
                if((i%2 == 0)&&(j%2 !=0)||(j%2 == 0)&&(i%2 != 0)){
                    paintOne.setColor(Color.parseColor("#108642"));
                    tempPaint = paintOne;
                }else{
                    paintTwo.setColor(Color.parseColor("#c0c0c0"));
                    tempPaint = paintTwo;
                }
                canvas.drawRect(grid.getCell(i,j).getX(),grid.getCell(i,j).getY(),grid.getCell(i,j).getX1(),grid.getCell(i,j).getY1(),tempPaint);

                if(tempBoard.getPieceAt(i,j-1).getPlayer() == CheckersVal.PLAYER_ONE){
                    paintOne.setColor(Color.BLACK);
                    canvas.drawCircle(grid.getCell(i,j).getCenter()[0],grid.getCell(i,j).getCenter()[1],(int)(grid.getCell(i,j).getWidth()/2.1),paintOne);
                }else if (tempBoard.getPieceAt(i,j-1).getPlayer() == CheckersVal.PLAYER_TWO){
                    paintTwo.setColor(Color.WHITE);
                    canvas.drawCircle(grid.getCell(i,j).getCenter()[0],grid.getCell(i,j).getCenter()[1],(int)(grid.getCell(i,j).getWidth()/2.1),paintTwo);
                }

            }
        }
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
    private List<int[]> mSelectPiece(CheckersGridCell piece){
        List<int[]> valid = null;
        if(game.selectPiece(piece.getArrX(),piece.getArrY())){
            valid = game.getValidMoves();
        }
        return valid;
    }
    public CheckersGridCell mGetNearestPiece(int x, int y){
        CheckersGridCell temp = null;
        for(int i = 0; i < grid.getGridWidth();i++){
            for (int j = 1; j < grid.getGridLength()-1; j++) {
                if (x > grid.getCell(i,j).getX() && x <= grid.getCell(i,j).getX1()){
                    if (y > grid.getCell(i,j).getY() && y <= grid.getCell(i,j).getY1()){
                        temp = grid.getCell(i,j);
                    }
                }
            }
        }
        return temp;
    }

    public void mUpdateGrid(CheckersGridCell move){
        game.makeMove(move.getArrX(),move.getArrY());
        selected = false;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
       // if (game.settings.isSinglePlayer() && game.playerOne.isPlayersTurn()){

        boolean touchEvent = false;
        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                touchEvent = true;
                if(selected == false){
                    final float x = event.getX();
                    final float y = event.getY();
                    selectedPiece = mGetNearestPiece((int)x,(int)y);
                    if (selectedPiece == null){
                        invalidToast.show();
                        touchEvent = false;
                        break;
                    }
                }else{
                    final float x = event.getX();
                    final float y = event.getY();
                    selectedPiece = mGetNearestPiece((int)x,(int)y);
                    mUpdateGrid(selectedPiece);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(selected = false){
                    selected = true;
                }else
                    selected = false;
                break;
            default:
                touchEvent = false;
                selected = false;
        }
        return touchEvent;
    }

    class GameThread extends Thread{
        private SurfaceHolder surfaceHolder;
        private CheckersDraw gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, CheckersDraw gameView) {
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
            }
        }
    }
}