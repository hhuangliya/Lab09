package com.example.lab09;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends RectF {
    private static final int BMP_COLUMNS = 5;
    private static final int BMP_ROWS = 2;
    private int dX, dY, color;
    Bitmap bmp;
    private int currentFrame=0, iconWidth, iconHeight;
    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }

    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom, 1, 2, Color.MAGENTA);
    }

    public Sprite(int dX, int dY, int color) {
        this(1, 1, 110, 110, dX, dY, color);
    }

    public Sprite() {
        this(2, 3, Color.GREEN);
    }

    public void update(Canvas canvas){
        if(right+dX>canvas.getWidth()){
            offsetTo(canvas.getWidth()-width(),top);
            dX*=-1;
        }
        offset(dX, dY);
        currentFrame = ++currentFrame % (BMP_COLUMNS-1);//cycles current image with boundary proteciton
    }

    public void draw(Canvas canvas){
        update(canvas);
        if(bmp==null) {//if no bitmap exists draw a red circle
            Paint paint = new Paint();
            paint.setColor(color);//sets its color
            canvas.drawCircle(centerX(), centerY(), width() / 2, paint);//draws circle
        }else {
            iconWidth = bmp.getWidth() / BMP_COLUMNS;//calculate width of 1 image
            iconHeight = bmp.getHeight() / BMP_ROWS; //calculate height of 1 image
            int srcX = (currentFrame+1) * iconWidth;       //set x of source rectangle inside of bitmap based on current frame
            int srcY = 0; //set y to row of bitmap based on direction
            Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);  //defines the rectangle inside of heroBmp to displayed
            canvas.drawBitmap(bmp,src, this,null); //draw an image
        }
    }
}
