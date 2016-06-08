package kevin.mygamehelper.common.widget;

import android.animation.ValueAnimator;
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

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Players;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.utils.Calculator;
import kevin.mygamehelper.data.utils.IFieldGettor;
import kevin.mygamehelper.data.utils.Hexagon;
import kevin.mygamehelper.data.utils.RadarEvaluator;
import kevin.mygamehelper.data.utils.SimpleFieldGettor;

/**
 * Created by Kevin on 2016/1/12.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RadarView extends View implements IFieldGettor {

    Dota2MatchDetails[] detials;

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
    private int textsize;

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

    private float kda = -1;
    private float damage = -1;
    private float push = -1;
    private float comprehensive = -1;
    private float grow = -1;
    private float live = -1;

    public static final boolean DRAW_BACKGROUND = true;
    public static final boolean DRAW_FORWARD = false;

    private boolean drawing = DRAW_BACKGROUND;

    IFieldGettor fieldGettor;

    int showCount;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
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
                case R.styleable.radar_view_textsize:
                    textsize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            }
        }

        sqrt3diameter = (int) (Math.sqrt(3) * diameter / 2);
        halfDiameter = diameter / 2;
        dia = diameter;

        a.recycle();

        setFieldGettor(new SimpleFieldGettor());
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
            Log.e("width", getWidth() + "");
            if (getWidth() / 2 > centerX) {
                centerX = getWidth() / 2;
            } else {
                centerX = diameter + getPaddingLeft();
            }
        } else {
            float mWidth = diameter;
            int desired = (int) (getPaddingLeft() + mWidth + getPaddingRight());
            width = desired * 2;
            centerX = width / 2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize + getPaddingTop() + getPaddingBottom();
            centerY = (int) (diameter / 2 * Math.sqrt(3)) + getPaddingTop();
        } else {
            float mHeight = (float) (diameter / 2 * Math.sqrt(3));
            int desired = (int) (getPaddingTop() + mHeight + getPaddingBottom());
            height = desired * 2;
            centerY = height / 2;
        }

        width = width + 4 * textsize;
        height = height + 3 * textsize;

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
        //由于左边text导致中心右移
        centerX = centerX + 2 * textsize;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawing == DRAW_BACKGROUND) {
            drawing = DRAW_FORWARD;
            startAnimation();
        } else {
            mPaint.setColor(Color.GRAY);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(1.3f);
            mPaint.setAlpha(0xA0);
            initBackground(canvas);
            mPaint.setColor(Color.parseColor("#c5cae9"));
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeWidth(1f);
            mPaint.setAlpha(0xA0);
            draw6point(canvas, currentHexagon);
        }
        setSixText(canvas);
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

        setEndPoint();

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

    private void initBackground(Canvas canvas) {

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

    public void prepareEndHex(ArrayList<Dota2GameOutline> matches, Dota2MatchDetails[] detials, int count, Dota2User account) {

        showCount = count > matches.size() ? matches.size():count;

        Dota2Players[] accountDetials = new Dota2Players[showCount];

        for (int i = 0; i < showCount; i++) {
            accountDetials[i] = (detials[i].getPlayer(account));
        }

        ArrayList<Float> array = getFieldAsList(accountDetials, "deaths", showCount);
        this.live = 100 - Calculator.Ex100(array, 3);
        array = getFieldAsList(accountDetials, "hero_damage", showCount);
        this.damage = Calculator.Ex100(array, 5000);
        array = getFieldAsList(accountDetials, "xp_per_min", showCount);
        this.grow = Calculator.Ex100(array, 500);
        array = getFieldAsList(accountDetials, "tower_damage", showCount);
        this.push = Calculator.Ex100(array, 2500);
        ArrayList<Float> arrayK = getFieldAsList(accountDetials, "kills", showCount);
        ArrayList<Float> arrayD = getFieldAsList(accountDetials, "deaths", showCount);
        ArrayList<Float> arrayA = getFieldAsList(accountDetials, "assists", showCount);
        for(int i=0;i<showCount;i++){
            array.add((arrayK.get(i)+arrayD.get(i))/arrayA.get(i));
        }
        this.kda = Calculator.Ex100(array, 5);
        this.comprehensive = (this.live + this.damage + this.grow + this.push + this.kda) / 5;

    }

    private void setEndPoint() {
        startHexagon = new Hexagon(centerX, centerY, centerX, centerY, centerX, centerY, centerX, centerY, centerX, centerY, centerX, centerY);
        if (this.kda != -1) {
            endHexagon = new Hexagon((int) (centerX + halfDiameter * kda / 100), (int) (centerY + sqrt3diameter * kda / 100), //右下 kda
                    (int) (centerX + diameter * damage / 100), centerY, //右 damage
                    (int) (centerX + halfDiameter * grow / 100), (int) (centerY - sqrt3diameter * grow / 100),//右上 grow
                    (int) (centerX - halfDiameter * push / 100), (int) (centerY - sqrt3diameter * push / 100), //左上 push
                    (int) (centerX - diameter * live / 100), centerY, //左 live
                    (int) (centerX - halfDiameter * comprehensive / 100), (int) (centerY + sqrt3diameter * comprehensive / 100));//左下 comprehensive
        } else if (endHexagon == null) {
            Hexagon end = new Hexagon(centerX + halfDiameter, centerY + sqrt3diameter, centerX + dia, centerY, centerX + halfDiameter, centerY - sqrt3diameter,
                    centerX - halfDiameter, centerY - sqrt3diameter, centerX - dia, centerY, centerX - halfDiameter, centerY + sqrt3diameter);
            endHexagon = end;
        }
    }

    private void setSixText(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1.3f);
        mPaint.setAlpha(0xFF);
        mPaint.setTextSize(textsize);
        canvas.drawText("kda", centerX + halfDiameter, centerY + sqrt3diameter + textsize, mPaint);
        canvas.drawText("伤害", centerX + diameter, centerY, mPaint);
        canvas.drawText("发育", centerX + halfDiameter, centerY - sqrt3diameter, mPaint);
        canvas.drawText("推进", centerX - halfDiameter - 2 * textsize, centerY - sqrt3diameter, mPaint);
        canvas.drawText("生存", centerX - diameter - 2 * textsize, centerY, mPaint);
        canvas.drawText("综合", centerX - halfDiameter - 2 * textsize, centerY + sqrt3diameter + textsize, mPaint);
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

    public void setMatchDetails(Dota2MatchDetails[] detials) {
        this.detials = detials;
    }

    public void setFieldGettor(IFieldGettor gettor) {
        this.fieldGettor = gettor;
    }

    @Override
    public <T, V> ArrayList<T> getFieldAsList(V[] detials, String name, int count) {
        if (fieldGettor != null) {
            return fieldGettor.getFieldAsList(detials, name, count);
        } else {
            return null;
        }
    }
}

