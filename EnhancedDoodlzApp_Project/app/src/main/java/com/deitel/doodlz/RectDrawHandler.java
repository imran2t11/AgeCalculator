package com.deitel.doodlz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.HashMap;
import java.lang.Math;
import java.util.Map;

// Handles drawing a rectangle on screen.
public class RectDrawHandler extends TouchDrawHandler
{
    // Fields
    private boolean filled =                            false;
    protected final Map<Integer, Point> previousPointMap =  new HashMap<>();

    public HashMap<Integer, Rect> rectMap =             new HashMap<>();
    public HashMap<Integer, Point> startingPoints =     new HashMap<>();

    // Methods

    // Constructors
    public RectDrawHandler(DoodleView doodleView) { super(doodleView); }

    public RectDrawHandler(DoodleView doodleView, Paint paintbrush)
    {
        super(doodleView, paintbrush);
    }

    // Main functionality

    @Override
    public void onTouchStarted(float x, float y, int touchId)
    {
        onTouchStartedInitPoints(x, y, touchId);
        onTouchStartedSetRect(x, y, touchId);
    }

    @Override
    public void onTouchMoved(MotionEvent event)
    {
        // For each pointer in the event...
        for (int i = 0; i < event.getPointerCount(); i++)
        {
            int touchId =           event.getPointerId(i);

            Point pointerLoc =      new Point((int) event.getX(), (int) event.getY());
            Point previousPoint =   previousPointMap.get(touchId);

            // See how far the pointer moved from the last registered point.
            float deltaX =          Math.abs(pointerLoc.x - previousPoint.x);
            float deltaY =          Math.abs(pointerLoc.y - previousPoint.y);

            // If it did, update the rect and said point.
            if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE)
            {
                updateRect(pointerLoc, touchId);
                previousPoint.set(pointerLoc.x, pointerLoc.y);
            }
        }
    }

    @Override
    public void onTouchEnded(int touchId)
    {
        // Just have the canvas bitmap draw the appropriate rect, so
        // that it stays on the screen.
        bitmapCanvas.drawRect(rectMap.get(touchId), paintbrush);
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Draw the rects based on the touch Ids registered in the rect map.
        for (Integer touchId : rectMap.keySet())
            canvas.drawRect(rectMap.get(touchId), paintbrush);
    }

    // Getters
    public boolean drawsFilled() { return this.filled; }

    // Setters
    public void setDrawsFilled(boolean value)
    {
        this.filled =                   value;

        // Make sure that the paintbrush's style matches the new
        // filled state.
        if (this.filled)
            this.paintbrush.setStyle(Paint.Style.FILL);
        else
            this.paintbrush.setStyle(Paint.Style.STROKE);

    }

    // Helpers
    protected void onTouchStartedInitPoints(float x, float y, int touchId)
    {
        // Set the starting point and previous points.
        Point startPoint, prevPoint;

        if (startingPoints.containsKey(touchId))
        {
            startPoint = startingPoints.get(touchId);
            prevPoint = previousPointMap.get(touchId);
        }
        else {
            startPoint = new Point();
            prevPoint = new Point();
        }

        startPoint.set((int) x, (int) y);
        prevPoint.set((int) x, (int) y);
        startingPoints.put(touchId, startPoint);
        previousPointMap.put(touchId, prevPoint);
    }

    protected void onTouchStartedSetRect(float x, float y, int touchId)
    {
        Rect rect;

        if (rectMap.containsKey(touchId))
            rect =              rectMap.get(touchId);
        else
            rect =              new Rect();

        // Have the rect start with side lengths of 1, to avoid a certain glitch
        rect.setEmpty();
        rectMap.put(touchId, rect);
    }

    protected void updateRect(Point pointerLoc, int touchId)
    {
        // Set the rect's four corners based on the pointer's current
        // location, and the starting point for the rect.
        Rect rect =                         rectMap.get(touchId);
        Point startPoint =                  startingPoints.get(touchId);

        int left =                          Math.min(pointerLoc.x, startPoint.x);
        int right =                         Math.max(pointerLoc.x, startPoint.x);
        int top =                           Math.max(pointerLoc.y, startPoint.y);
        int bottom =                        Math.min(pointerLoc.y, startPoint.y);

        rect.right =                        right;
        rect.left =                         left;
        rect.top =                          top;
        rect.bottom =                       bottom;
    }

}
