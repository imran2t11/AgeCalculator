package com.deitel.doodlz;

import android.graphics.Paint;
import android.text.method.Touch;
import android.view.MotionEvent;

public abstract class TouchDrawHandler extends DrawHandler
    implements ITouchEventHandler, ITouchStartedHandler,
        ITouchMovedHandler, ITouchEndedHandler
{
    // used to determine whether user moved a finger enough to draw again
    protected static float TOUCH_TOLERANCE =    10;

    public TouchDrawHandler(DoodleView doodleView)
    {
        super(doodleView);
    }

    public TouchDrawHandler(DoodleView doodleView, Paint paintbrush)
    {
        super(doodleView, paintbrush);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        // Based on the event type, trigger a touch-handling method.
        int action =            event.getActionMasked(); // event type
        int actionIndex =       event.getActionIndex(); // pointer (i.e., finger)

        boolean touchStarted =  action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_POINTER_DOWN;
        boolean touchEnded =    action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_POINTER_UP;

        // determine whether touch started, ended or is moving
        if (touchStarted)
            onTouchStarted(event.getX(actionIndex),
                    event.getY(actionIndex),
                    event.getPointerId(actionIndex));

        else if (touchEnded)
            onTouchEnded(event.getPointerId(actionIndex));

        else // touch ongoing
            onTouchMoved(event);

        doodleView.invalidate();
        return true;
    }

    public abstract void onTouchStarted(float x, float y, int lineId);

    public abstract void onTouchMoved(MotionEvent event);

    public abstract void onTouchEnded(int lineId);
}
