package cuteuser.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    Thread drawThread;
    float x,y,r;
    boolean started = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        r = 0;
        started = true;
        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        drawThread = new Thread() {
            Paint paint = new Paint();

            @Override
            public void run() {
                paint.setColor(Color.YELLOW);
                while (true) {
                    Canvas canvas = getHolder().lockCanvas();
                    canvas.drawColor(Color.BLUE);
                    if (started) canvas.drawCircle(x, y, r, paint);
                    getHolder().unlockCanvasAndPost(canvas);
                    r+=5;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
