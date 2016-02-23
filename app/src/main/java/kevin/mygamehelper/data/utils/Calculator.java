package kevin.mygamehelper.data.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kevin on 2016/2/19.
 * DESCRIPTION:计算方差等
 * email:493243390@qq.com
 */
public class Calculator {

    public static float SIGMA = 0.68268949f / 2;
    public static float SIGMA2 = (0.95449974f - SIGMA * 2) / 2;
    public static float SIGMA3 = (0.99730020f - SIGMA * 2 - SIGMA2 * 2) / 2;
    public static float SIGMA_EXTRA = (1 - SIGMA * 2 - SIGMA2 * 2 - SIGMA3 * 2) / 2;

    public static float PRECENT = 0.6f;

    public static float average(ArrayList<Float> array) {

        int count = array.size();
        float ave = 0;
        for (int i = 0; i < count; i++) {
            ave += array.get(i);
        }
        ave /= count;
        float realSum = 0;
        for (int i = 0; i < count; i++) {
            if (ave > 200 && array.get(i) > (ave * 1.8)) {
                count--;
            } else {
                realSum += array.get(i);
            }
        }
        return realSum / count;
    }

    public static float variance(ArrayList<Float> array) {
        int count = array.size();
        float ave = average(array);
        float var = 0;
        for (int i = 0; i < count; i++) {
            var += (array.get(i) - ave) * (array.get(i) - ave);
        }
        return var / count;
    }

    public static double standard_deviation(ArrayList<Float> array) {
        return Math.sqrt(variance(array));
    }

    ;

    public static float Ex(ArrayList<Float> array, int EX) {

        int count = array.size();
        float ave = average(array);
        float ex = ave * PRECENT;

        float sigma = (float) standard_deviation(array);

        for (int i = 0; i < count; i++) {
            float value = array.get(i);
            //Log.e("ex", "Ex: "+ex + " precent :"+ precent(ave, sigma, value)+" value: "+value + " worth :"+ value * precent(ave, sigma, value) * (1-PRECENT)/count);
            ex += value * precent(ave, sigma, value) * (1 - PRECENT) / count;
        }
        return ex;
    }

    ;

    public static float Ex100(ArrayList<Float> array, float EX) {
        int count = array.size();
        float ave = average(array);
        float ex;
        if (ave > EX) {
            ex = 100 * PRECENT;
        } else {
            ex = ave / EX * (100 - PRECENT * 100);
        }

        float sigma = (float) standard_deviation(array);

        for (int i = 0; i < count; i++) {
            float value = array.get(i);
            ex += 100 * (1 - PRECENT) / count * precent(ave, sigma, value);
        }

        return ex;
    }

    public static float precent(float ave, float sigma, float value) {

        float sigma1 = sigma;
        float sigma2 = sigma * 1.96f;
        float sigma3 = sigma * 2.58f;

        float ex = 0;
        if (value < ave - sigma3) {
            ex = ex + SIGMA_EXTRA;
        } else if (value < ave - sigma2 && value > ave - sigma3) {
            ex = ex + SIGMA_EXTRA;
            ex += (value - (ave - sigma3)) / (sigma3 - sigma2) * SIGMA3;
        } else if (value < ave - sigma1 && value > ave - sigma2) {
            ex = ex + SIGMA_EXTRA + SIGMA3;
            ex += (value - (ave - sigma2)) / (sigma2 - sigma1) * SIGMA2;
        } else if (value < ave && value > ave - sigma1) {
            ex = ex + SIGMA_EXTRA + SIGMA3 + SIGMA2;
            ex += (value - (ave - sigma1)) / (sigma1) * SIGMA;
        } else if (value > ave && value < ave + sigma1) {
            ex = 0.5f;
            ex += (value - ave) / (sigma1) * SIGMA;
        } else if (value > ave + sigma1 && value < ave + sigma2) {
            ex = 0.5f + SIGMA;
            ex += (value - (ave + sigma)) / (sigma2 - sigma1) * SIGMA2;
        } else if (value > ave + sigma2 && value < ave + sigma3) {
            ex = 0.5f + SIGMA + SIGMA2;
            ex += (value - (ave + sigma1)) / (sigma3 - sigma2) * SIGMA3;
        } else {
            ex = 1f;
        }
        return ex;
    }
};