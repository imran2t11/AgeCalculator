package com.deitel.doodlz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;

import static com.deitel.doodlz.MotionEventExt.getPoint;

public class LineDrawHandler extends TouchDrawHandler
{
    // Fields
    protected final Map<Integer, Point> previousPointMap =  new HashMap<>();

    // Methods
    public LineDrawHandler(DoodleView doodleView)
    {
        super(doodleView);
    }
    public LineDrawHandler(DoodleView doodleView, Paint paintbrush)
    {
        super(doodleView, paintbrush);
    }

    public void onTouchStarted(float x, float y, int lineId)
    {
        Path path; // used to store the path for the given touch id
        Point point; // used to store the last point in path

        // if there is already a path for lineID
        if (pathMap.containsKey(lineId))
        {
            path =          pathMap.get(lineId); // get the Path
            path.reset();   // resets the Path because a new touch has started
            point =         previousPointMap.get(lineId); // get Path's last point
        }
        else {
            path =          new Path();
            pathMap.put(lineId, path); // add the Path to Map
            point =         new Point(); // create a new Point
            previousPointMap.put(lineId, point); // add the Point to the Map
        }

        // Move to the coordinates of the touch
        path.moveTo(x, y);
        point.x =           (int) x;
        point.y =           (int) y;
    }

    public void onTouchMoved(MotionEvent event)
    {
        // for each of the pointers in the given MotionEvent
        for (int i = 0; i < event.getPointerCount(); i++)
        {
            // get the pointer ID and pointer index
            int pointerID =             event.getPointerId(i);
            int pointerIndex =          event.findPointerIndex(pointerID);

            // if there is a path associated with the pointer
            if (pathMap.containsKey(pointerID))
            {
                // get the new coordinates for the pointer
                Point coords =          getPoint(event);

                // get the path and previous point associated with
                // this pointer
                Path path =             pathMap.get(pointerID);
                Point point =           previousPointMap.get(pointerID);

                // calculate how far the user moved from the last update
                float deltaX =          Math.abs(coords.x - point.x);
                float deltaY =          Math.abs(coords.y - point.y);

                // if the distance is significant enough to matter
                if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE)
                {
                    // move the path to the new location
                    path.quadTo(point.x, point.y,
                            (coords.x + point.x) / 2,
                            (coords.y + point.y) / 2);

                    // store the new coordinates
                    point.set(coords.x, coords.y);
                }
            }
        }
    }

    public void onTouchEnded(int lineId)
    {
        // Get the path, be sure it's all drawn on the canvas, then reset it.
        Path path =                 pathMap.get(lineId);
        bitmapCanvas.drawPath(path, paintbrush);
        path.reset();
    }

    // Getters
    public int getLineWidth()
    {
        return (int) paintbrush.getStrokeWidth();
    }

    // Setters
    public void setLineWidth(int width)
    {
        paintbrush.setStrokeWidth(width);
    }
}
