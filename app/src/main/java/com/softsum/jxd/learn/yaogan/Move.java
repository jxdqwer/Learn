package com.softsum.jxd.learn.yaogan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.softsum.jxd.learn.R;

public class Move {
    private float x1,y1;//按下时的坐标 大圆
    private float x2,y2;//移动后的坐标 小圆
    private final float r1,r2;//r1大圆的半径 r2小圆的半径
    public float angle;//x1y1指向x2y2的角度 弧度制
    public boolean down=false;//判断是否被按下
    public boolean in=false;//判断小圆是否在大圆里面，简单的说就是防止小圆被脱太远
    public boolean move=false;//判断手指按下后是否移动（MY实际开发中用到,该教程用不到此变量）
    public Bitmap img;//大圆小圆的图片

    public Move(){
        r1 = 480 * 0.5f * ViewAttributes.bili;//乘上一个比例 适应不同大小的屏幕
        r2 = 300 * 0.5f * ViewAttributes.bili;
        img= BitmapFactory.decodeResource(ViewAttributes.main.getResources(), R.drawable.apple);//初始化摇杆图片
    }

    public void down(float xx,float yy){ //摇杆按下后的操作
        if(xx<r1) x1=r1;
        else x1 = xx;

        if(ViewAttributes.h-yy<r1) y1=ViewAttributes.h-r1;
        else y1 = yy;
        //上面的代码是防止按下的位置太靠近屏幕边缘
        //跟x1=xx;y1=yy;区别不大，待会可以改成x1=xx;y1=yy;看看效果有什么不同
        down=true;
    }
    public void move(float xx,float yy){ //按下摇杆后移动的操作
        angle=getAngle(xx,yy);
        in=in(xx, yy);
        move=isMove(xx,yy);
        if (!in) {
            //下面会做解释
            xx= (float) (x1+ Math.sin(angle)*r1*0.7f);
            yy= (float) (y1+ Math.cos(angle)*r1*0.7f);
        }
        x2=xx;
        y2=yy;
    }
    public void up(){ //松开后的操作
        down=false;
    }

    public float getAngle(float xx,float yy){ //获取x1y1指向x2y2的角度
        double angle,k;
        if (y1==yy)//斜率不存在时
            if (x1 > xx)//判断x1指向x2的方向
                angle=-Math.PI/2;
            else
                angle=Math.PI/2;
        else{
            k=(x1-xx)/(y1-yy); //两点的坐标求斜率,至于为什么是(x1-x2)/(y1-y2)不是(y1-y2)/(x1-x2)待会我们再做解释
            if (y1 > yy) {//判断x1y1指向x2y2的方向
                // 用反tan求角度 这个高中好像没学过 既然Math类已经帮我们封装好了就直接拿来用吧
                angle=Math.atan(k) + Math.PI;
            } else {
                angle=Math.atan(k);
            }
            //这段可写可不写 让计算出来的角度属于-PI/2到PI/2
            if(angle>Math.PI)
                angle-=Math.PI*2;
            else if(angle<-Math.PI)
                angle+=Math.PI*2;
        }
        return (float) angle;
    }

    public boolean in(float xx, float yy) { //防止小圆被脱太远 拖动范围不超出r1的70%
        double r = Math.sqrt((x1 - xx) * (x1 - xx) + (y1 - yy) * (y1 - yy));//两点间距离公式
        if (r < r1*0.7f)
            return true;
        else return false;
    }
    public boolean isMove(float xx, float yy) { //判断按下摇杆后 是否移动,如果x1y1 x2y2的距离大于r1*0.15视为移动
        // MY实际开发中用到,该教程用不到此变量
        double r = Math.sqrt((x1 - xx) * (x1 - xx) + (y1 - yy) * (y1 - yy));//两点间距离公式
        if (r > r1*0.15f)
            return true;
        else return false;
    }
    public void onDraw(Canvas g, Paint p){ //画摇杆
        if(down) { //当摇杆被按下时 才显示
            //怎么用Canvas画图这里就不说了
            ViewAttributes.re.left = x1 - r1;
            ViewAttributes.re.top = y1 - r1;
            ViewAttributes.re.right = x1 + r1;
            ViewAttributes.re.bottom = y1 + r1;
            g.drawBitmap(img, null, ViewAttributes.re, p); //画大圆
            ViewAttributes.re.left = x2 - r2;
            ViewAttributes.re.top = y2 - r2;
            ViewAttributes.re.right = x2 + r2;
            ViewAttributes.re.bottom = y2 + r2;
            g.drawBitmap(img, null, ViewAttributes.re, p); //画小圆
        }
    }

}
