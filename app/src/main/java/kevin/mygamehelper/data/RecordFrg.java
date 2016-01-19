package kevin.mygamehelper.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.gamehelper.mygamehelper.R;

/**
 * Created by Kevin on 2016/1/19.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RecordFrg extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dota2_preview_record,container,false);
    }
}
