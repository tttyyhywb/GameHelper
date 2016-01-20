package kevin.mygamehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.mygamehelper.data.Dota2SearchActivity;

/**
 * Created by Kevin on 2015/8/4.
 */
public class DataFrg extends Fragment {

    @Bind(R.id.tv_data_frgment_serach)
    TextView tvDataFragmentSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_data, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvDataFragmentSearch = (TextView) view.findViewById(R.id.tv_data_frgment_serach);
        tvDataFragmentSearch.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_data_frgment_serach: {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Dota2SearchActivity.class);
                    startActivity(intent);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
