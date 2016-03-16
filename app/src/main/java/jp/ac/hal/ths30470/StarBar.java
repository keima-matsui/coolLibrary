package jp.ac.hal.ths30470;

import junit.framework.Assert;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class StarBar extends RatingBar {
    private final Paint paint = new Paint();
    private final Path path = new Path();
    private static final double StarRadUnit = 2*Math.PI / 5;
    private static final double StarRadStart = - Math.PI / 2;
    private static final double[] StarRadValues = {
            StarRadStart,
            StarRadStart+StarRadUnit*2,
            StarRadStart+StarRadUnit*4,
            StarRadStart+StarRadUnit*1,
            StarRadStart+StarRadUnit*3,
    };
    private int offStarColor = Color.argb(50, 0, 0, 0);
    private int onStarColor = Color.argb(0xff, 0, 0, 0);

    private void pathStar(double cx, double cy, double r) {
        float sx = (float)(cx + r * Math.cos(StarRadValues[4]));
        float sy = (float)(cy + r * Math.sin(StarRadValues[4]));
        path.moveTo(sx, sy);
        for (double rad : StarRadValues) {
            float x = (float)(cx + r * Math.cos(rad));
            float y = (float)(cy + r * Math.sin(rad));
            path.lineTo(x, y);
        }
        path.close();
    }

    public StarBar(Context context) {
        super(context);
    }

    public StarBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StarBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        final int rating = Math.round(getRating());
        final int stars = getNumStars();
        final float w = getWidth();
        final float h = getHeight();
        final float sw = w / stars;
        Assert.assertEquals(h, sw);
        for (int i=0;i<stars;++i) {
            final float cx = (float)(i+0.5)*sw;
            final float cy = (float)sw/2;
            final float r = sw/2;

            path.reset();
            pathStar(cx, cy, r);

            final int fcolor = i < rating ? onStarColor : offStarColor;
            paint.setStrokeWidth((float) -1.0);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(fcolor);
            canvas.drawPath(path, paint);
        }
    }

    public void setOffStarColor(int offStarColor) {
        this.offStarColor = offStarColor;
    }

    public int getOffStarColor() {
        return offStarColor;
    }

    public void setOnStarColor(int onStarColor) {
        this.onStarColor = onStarColor;
    }

    public int getOnStarColor() {
        return onStarColor;
    }
}