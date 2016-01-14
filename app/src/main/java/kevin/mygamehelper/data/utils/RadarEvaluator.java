package kevin.myjjj;

import android.animation.TypeEvaluator;

/**
 * Created by Kevin on 2016/1/14.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RadarEvaluator implements TypeEvaluator<Hexagon> {

    @Override
    public Hexagon evaluate(float fraction, Hexagon startValue, Hexagon endValue) {

        int x1 = (int) (startValue.getX1() + fraction * (endValue.getX1() - startValue.getX1()));
        int y1 = (int) (startValue.getY1() + fraction * (endValue.getY1() - startValue.getY1()));
        int x2 = (int) (startValue.getX2() + fraction * (endValue.getX2() - startValue.getX2()));
        int y2 = (int) (startValue.getY2() + fraction * (endValue.getY2() - startValue.getY2()));
        int x3 = (int) (startValue.getX3() + fraction * (endValue.getX3() - startValue.getX3()));
        int y3 = (int) (startValue.getY3() + fraction * (endValue.getY3() - startValue.getY3()));
        int x4 = (int) (startValue.getX4() + fraction * (endValue.getX4() - startValue.getX4()));
        int y4 = (int) (startValue.getY4() + fraction * (endValue.getY4() - startValue.getY4()));
        int x5 = (int) (startValue.getX5() + fraction * (endValue.getX5() - startValue.getX5()));
        int y5 = (int) (startValue.getY5() + fraction * (endValue.getY5() - startValue.getY5()));
        int x6 = (int) (startValue.getX6() + fraction * (endValue.getX6() - startValue.getX6()));
        int y6 = (int) (startValue.getY6() + fraction * (endValue.getY6() - startValue.getY6()));

        return new Hexagon(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6);
    }
}
