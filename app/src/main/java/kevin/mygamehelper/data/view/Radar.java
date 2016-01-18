package kevin.myjjj;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Kevin on 2016/1/12.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class Radar extends View {

    private Paint mPaint;

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setForwardColor(Color forwardColor) {
        this.forwardColor = forwardColor;
    }

    private Color backgroundColor;
    private Color forwardColor;

    private int diameter;
    private int centerX;
    private int centerY;

    float scaleX = 1;
    float scaleY = 1;

    //根号3
    private int sqrt3diameter;
    private int halfDiameter;

    //临时用于画雷达边(按比例l缩放)  l : 1/4 2/4 3/4 4/4
    int dia;

    Path path;

    private Hexagon startHexagon;

    private Hexagon endHexagon;

    private Hexagon currentHexagon;

    private float kda;
    private float damage;
    private float push;
    private float comprehensive;
    private float grow;
    private float live;

    public static final boolean DRAW_BACKGROUND = true;
    public static final boolean DRAW_FORWARD = false;

    private boolean drawing = DRAW_BACKGROUND;

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

        sqrt3diameter = (int) (Math.sqrt(3) * diameter / 2);
        halfDiameter = diameter / 2;
        dia = diameter;

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize + getPaddingLeft() + getPaddingRight();
            centerX = diameter + getPaddingLeft();
        } else {
            float mWidth = diameter;
            int desired = (int) (getPaddingLeft() + mWidth + getPaddingRight());
            width = desired * 2;
            centerX = width/2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize + getPaddingTop() + getPaddingBottom();
            centerY = (int) (diameter / 2 * Math.sqrt(3)) + getPaddingTop();
        } else {
            float mHeight = (float) (diameter / 2 * Math.sqrt(3));
            int desired = (int) (getPaddingTop() + mHeight + getPaddingBottom());
            height = desired * 2;
            centerY = height/2;
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

        setMeasuredDimension(width, height);
        setEndPoint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawing == DRAW_BACKGROUND) {
            drawing = DRAW_FORWARD;
            startAnimation();
        }else{
            mPaint.setColor(Color.GRAY);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(1.3f);
            mPaint.setAlpha(0x60);
            initBackground(canvas);
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeWidth(1f);
            mPaint.setAlpha(0x30);
            draw6point(canvas,currentHexagon);
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

    private void startAnimation() {

        startHexagon = new Hexagon(centerX, centerY, centerX, centerY, centerX, centerY, centerX, centerY, centerX, centerY, centerX, centerY);
        Hexagon end = new Hexagon(centerX + halfDiameter, centerY + sqrt3diameter, centerX + dia, centerY, centerX + halfDiameter, centerY - sqrt3diameter,
                centerX - halfDiameter, centerY - sqrt3diameter, centerX - dia, centerY, centerX - halfDiameter, centerY + sqrt3diameter);

        if(endHexagon ==null){
            endHexagon = end;
        }

        ValueAnimator anim = new ValueAnimator().ofObject(new RadarEvaluator(), startHexagon, endHexagon);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentHexagon = (Hexagon) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(1000);
        anim.start();
    }

    private void initBackground(Canvas canvas){

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
        drawing = DRAW_FORWARD;
    }

    public void prepareEndHex(float kda, float damage, float grow, float push , float live, float comprehensive){
        this.kda = kda;
        this.damage = damage;
        this.grow=grow;
        this.push = push;
        this.live = live;
        this.comprehensive = comprehensive;
    }

    private void setEndPoint(){
        endHexagon = new Hexagon((int)(centerX + halfDiameter * kda/100), (int)(centerY + sqrt3diameter *kda/100), //右下 kda
                (int)(centerX + dia*damage/100), centerY, //右 damage
                (int)(centerX + halfDiameter*grow/100), (int)(centerY - sqrt3diameter*grow/100),//右上 grow
                (int)(centerX - halfDiameter*push/100), (int)(centerY - sqrt3diameter*push/100), //左上 push
                (int)(centerX - diameter*live/100), centerY, //左 live
                (int)(centerX - halfDiameter*comprehensive/100), (int)(centerY + sqrt3diameter*comprehensive/100));//左下 comprehensive
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
