package com.dibujar.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Board extends View {
    private Bitmap bitmap=null;
    private Canvas canvas;
    private Path path=null;
    private float valX, valY;
    private static final float TOLERANCIA=4;
    private Paint paint=null;

    public Board (Context context){
        super(context);
        init (context);
    }

    public Board (Context context, AttributeSet atributeset){
        super(context, atributeset);
        init (context);
    }

    private void init (Context context){
        //obtener pantalla
        Display display=((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        bitmap = Bitmap.createBitmap(point.x,point.y,Bitmap.Config.ARGB_8888);
        canvas =new Canvas( bitmap);
        path=new Path();
        // preparamos el pincel
        paint =new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(0xFF00E1FF);
        paint.setStrokeWidth(10);
    }

    public boolean onTouchEvent (MotionEvent event){
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchStart(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }

    private void touchStart (float x, float y){
        path.reset();
        path.moveTo(x,y);
        valX=x;
        valY=y;
    }

    private void touchMove (float x, float y){
        if(Math.abs(x-valX)>= TOLERANCIA || Math.abs(y-valY)>=TOLERANCIA){
            path.quadTo(valX,valY,(x+valX)/2,(y+valY)/2);
            valX=x;
            valY=y;
        }
    }

    private void touchUp (){
        path.lineTo(valX,valY);
        canvas.drawPath(path,paint);
        path.reset();
    }

    protected void onDraw (Canvas canvas){
        //fondo
        canvas.drawColor(0xFFBBBBBB);
        //lo pintado
        canvas.drawBitmap(bitmap,0,0,null);
        //trazo actual
        canvas.drawPath(path,paint);
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void clear(Context context){
        Display display =((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point =new Point();
        display.getSize(point);
        bitmap=Bitmap.createBitmap(point.x, point.y,Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitmap);
        invalidate();
    }

    public void setEraser(){

        paint.setStrokeWidth(50);
        paint.setXfermode(new PorterDuffXfermode(
                PorterDuff.Mode.CLEAR));
    }

    public void setPaintColor(int color){
        paint.setColor(color);
        paint.setXfermode(null);
        paint.setStrokeWidth(10);
    }

}
