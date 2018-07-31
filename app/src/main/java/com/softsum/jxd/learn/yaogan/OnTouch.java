package com.softsum.jxd.learn.yaogan;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class OnTouch extends View{

    private  Move m;

    public OnTouch(Context context,Move move){
        super(context);
        this.m=move;

        setBackgroundColor(Color.WHITE);
        getBackground().setAlpha(ViewAttributes.ontouchAlpha);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final float x = motionEvent.getX() + getX(),y = motionEvent.getY() + getY();

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    m.down(x,y);
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    m.up();
                }
                m.move(x,y);
                Log.v("OnTouch","x:" + String.valueOf(x) +
                        "y" + String.valueOf(y)  );
                return true;
            }
        });

    }

}
