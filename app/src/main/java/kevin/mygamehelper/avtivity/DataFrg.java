package kevin.mygamehelper.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kevin.gamehelper.mygamehelper.R;
import kevin.mygamehelper.dota2.dota2activity.Dota2SearchActivity;

/**
 * Created by Kevin on 2015/8/4.
 */
public class DataFrg  extends Fragment {

    RelativeLayout layoutNoUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_data,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutNoUser =(RelativeLayout) view.findViewById(R.id.layout_nouser);
        layoutNoUser.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.layout_nouser: {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),Dota2SearchActivity.class);
                    startActivity(intent);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };

}
