package com.deitel.doodlz;

import android.graphics.Point;
import android.view.MotionEvent;

public class MotionEventExt
{
    public static Point getPoint(MotionEvent event)
    {
        return new Point((int) event.getX(), (int) event.getY());
    }
}
