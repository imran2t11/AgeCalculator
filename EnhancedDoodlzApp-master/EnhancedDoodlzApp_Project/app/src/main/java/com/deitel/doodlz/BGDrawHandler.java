package com.deitel.doodlz;

import android.graphics.Canvas;

public class BGDrawHandler extends DrawHandler
{
    public BGDrawHandler(DoodleView doodleView)
    {
        super(doodleView);
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Set the whole bitmap as a solid color
        canvas.drawBitmap(bitmap, 0, 0, paintbrush);
    }
}
