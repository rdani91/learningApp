package hu.rozsa.daniel.learningapplication.ninth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class MyDrawView extends View {
    Point a = new Point(0, 0);
    Point b = new Point(0, 100);
    Point c = new Point(87, 50);
    Paint paint = new Paint();
    Path path = new Path();


    public MyDrawView(Context context) {
        super(context);
    }

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(android.graphics.Color.BLACK);
        canvas.drawPaint(paint);

        paint.setStrokeWidth(4);
        paint.setColor(android.graphics.Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);


        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, paint);
    }


}
