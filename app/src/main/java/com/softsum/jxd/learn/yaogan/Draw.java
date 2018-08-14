package com.softsum.jxd.learn.yaogan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.RelativeLayout;

public class Draw extends RelativeLayout implements Runnable{

    private Paint p;
    private Move m = new Move();

    public  Draw(Context context){
        super(context);
        p=new Paint();
        setBackgroundColor(Color.BLACK);

        OnTouch onTouch = new OnTouch(context,m);

        addView(onTouch,ViewAttributes.w/2,ViewAttributes.h);

        onTouch.setX(0);
        onTouch.setY(0);

        new Thread(this).start();
    }

    @Override
    protected void onDraw(Canvas g)
    {
        super.onDraw(g);
        m.onDraw(g,p);
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            postInvalidate();
        }

    }
}
