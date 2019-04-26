package com.deitel.doodlz;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.provider.MediaStore;
import android.support.v4.print.PrintHelper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public abstract class DrawHandler
{
    // Fields
    protected Bitmap bitmap; // drawing area for displaying or saving
    protected Canvas bitmapCanvas; // used to to draw on the bitmap
    protected final Paint paintbrush =                      new Paint();
    // ^ Used to draw bitmap onto screen

    protected final HashMap<Integer, Path> pathMap =      new HashMap<>();


    protected DoodleView doodleView;
    // ^ To get certain important info from

    // Methods

    public DrawHandler(DoodleView doodleView)
    {
        this.doodleView =               doodleView;
        this.bitmap =                   doodleView.getBitmap();
        this.bitmapCanvas =             doodleView.getBitmapCanvas();
    }

    public DrawHandler(DoodleView doodleView, Paint paintBrush)
    {
        this(doodleView);
        this.paintbrush.set(paintBrush);
    }


    public void draw(Canvas canvas)
    {
        // Draw the paths registered
        for (Integer key : pathMap.keySet())
            canvas.drawPath(pathMap.get(key), paintbrush); // draw line

    }

    // Setters
    public void setDrawingColor(int color)
    {
        this.paintbrush.setColor(color);
    }

    public void setPaintBrush(Paint source)
    {
        this.paintbrush.set(source);
    }

    public void setStrokeWidth(int width) { this.paintbrush.setStrokeWidth(width); }

    public void setBitmapCanvas(Canvas canvas)
    {
        bitmapCanvas = canvas;
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    // Getters
    public float getStrokeWidth() { return this.paintbrush.getStrokeWidth(); }
    public int getDrawingColor()
    {
        return this.paintbrush.getColor();
    }


}
