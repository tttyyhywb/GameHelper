package kevin.mygamehelper.data.utils;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/21.
 */
public class SimpleFieldGettor implements IFieldGettor {
    @Override
    public <T, V> ArrayList<T> getFieldAsList(V[] detials, String fieldName, int count) {
        if (detials[count - 1] == null) {
            Log.e("RadarView", "getItemAsList:out of array index");
            return null;
        }

        try {
            ArrayList<T> array = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                Class clazz = detials[i].getClass();
                String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Method method = clazz.getMethod(methodName);
                T t = (T) method.invoke(detials[i]);
                array.add(t);
            }
            return array;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
