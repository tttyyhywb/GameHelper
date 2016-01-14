package kevin.myjjj;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Kevin on 2016/1/12.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class Radar extends View {

    Paint mPaint;
    int diameter;
    int centerX;
    int centerY;

    float scaleX = 1;
    float scaleY = 1;

    //根号3
    int sqrt3diameter;
    int halfDiameter;

    //临时用于画雷达边时候的比例  如1/4 2/4 3/4 4/4
    int dia;

    Path path;

    public Radar(Context context) {
        this(context, null);
    }

    public Radar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Radar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.radar_view, defStyleAttr, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.radar_view_diameter:
                    diameter = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            float mWidth = diameter;
            int desired = (int) (getPaddingLeft() + mWidth + getPaddingRight());
            width = desired * 2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float mHeight = (float) (diameter / 2 * Math.sqrt(3));
            int desired = (int) (getPaddingTop() + mHeight + getPaddingBottom());
            height = desired * 2;
        }

        if (width / 2 < diameter) {
            scaleX = width / 2 * 1.0f / diameter;
        }

        if (height / 2 * Math.sqrt(3) < diameter) {
            scaleY = height * 1.0f / 2 * (int) (Math.sqrt(3)) / diameter;
        }

        if (scaleX < scaleY) {
            scaleY = scaleX;
        } else {
            scaleX = scaleY;
        }

//        centerX = width / 2 ;
//        centerY = height / 2;
        centerX = diameter + getPaddingLeft();
        centerY = (int) (diameter / 2 * Math.sqrt(3)) + getPaddingTop();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAlpha(0x60);
        sqrt3diameter = (int) (Math.sqrt(3) * diameter / 2);
        halfDiameter = diameter / 2;
        Path path;


        canvas.scale(scaleX, scaleY);
        for (int i = -1; i <= 1; i++) {
            path = new Path();
            path.moveTo(centerX, centerY);
            path.lineTo(centerX + i * diameter, centerY);
            path.moveTo(centerX, centerY);
            path.lineTo(centerX + i * halfDiameter, centerY + i * -1 * sqrt3diameter);
            path.moveTo(centerX, centerY);
            path.lineTo(centerX + i * halfDiameter, centerY + i * sqrt3diameter);
            canvas.drawPath(path, mPaint);
        }

        for (int i = 1; i <= 4; i++) {
            dia = diameter / 4 * i;
            sqrt3diameter = (int) (Math.sqrt(3) * dia / 2);
            halfDiameter = dia / 2;
            Hexagon hexagon = new Hexagon(centerX + halfDiameter, centerY + sqrt3diameter, centerX + dia, centerY, centerX + halfDiameter, centerY - sqrt3diameter,
                    centerX - halfDiameter, centerY - sqrt3diameter, centerX - dia, centerY, centerX - halfDiameter, centerY + sqrt3diameter);
            draw6point(canvas, hexagon);
        }
    }

    private void draw6point(Canvas canvas, Hexagon hexagon) {

        path = new Path();
        path.moveTo(hexagon.getX1(), hexagon.getY1());
        path.lineTo(hexagon.getX2(), hexagon.getY2());
        path.lineTo(hexagon.getX3(), hexagon.getY3());
        path.lineTo(hexagon.getX4(), hexagon.getY4());
        path.lineTo(hexagon.getX5(), hexagon.getY5());
        path.lineTo(hexagon.getX6(), hexagon.getY6());
        path.close();
        canvas.drawPath(path, mPaint);
    }


}
