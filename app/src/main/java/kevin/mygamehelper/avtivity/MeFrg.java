package kevin.mygamehelper.avtivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.gamehelper.mygamehelper.R;

/**
 * Created by Administrator on 2015/8/4.
 */
public class MeFrg  extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_me,container,false);
    }
}
