package kevin.mygamehelper.data.utils;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2MatchDetails;

/**
 * Created by Administrator on 2016/2/21.
 */
public interface FieldGettor {

    public <T,V> ArrayList<T> getFieldAsList(V[] detials, String fieldName, int count);

}
