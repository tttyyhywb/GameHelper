package kevin.mygamehelper.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2User;

/**
 * Created by Kevin on 2016/1/19.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RecordFrg extends Fragment {
    Dota2MatchDetails[] detials;
    ArrayList<Dota2GameOutline> matches;
    Dota2User account;
    public RecordFrg() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dota2_preview_record, container, false);
    }

    public RecordFrg(Dota2MatchDetails[] detials, ArrayList<Dota2GameOutline> matches,Dota2User account) {
        this.detials = detials;
        this.matches = matches;
        this.account = account;
    }
}
