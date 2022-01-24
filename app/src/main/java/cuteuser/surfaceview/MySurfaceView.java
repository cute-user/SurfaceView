package cuteuser.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    Thread drawThread;

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        drawThread = new Thread() {
            float x = 0;
            Paint paint = new Paint();

            @Override
            public void run() {
                paint.setColor(Color.WHITE);
                while (true) {
                    Canvas canvas = getHolder().lockCanvas();
                    canvas.drawColor(Color.BLACK);
                    canvas.drawCircle(0, x, 50, paint);
                    getHolder().unlockCanvasAndPost(canvas);
                    x++;
                    try {
                        Thread.sleep(1500);
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
