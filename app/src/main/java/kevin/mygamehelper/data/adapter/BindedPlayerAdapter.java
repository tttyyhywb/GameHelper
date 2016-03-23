package kevin.mygamehelper.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kevin.api.dota2.bean.Dota2User;
import kevin.database.DataBase.DBHelperDota2;
import kevin.mygamehelper.data.Dota2PreviewActivity;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;
import kevin.utils.SPUtils;

/**
 * Created by Kevin on 2016/3/23.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class BindedPlayerAdapter extends RecyclerView.Adapter<BindedPlayerAdapter.MyHolder> {

    List<Dota2User> dataList = new ArrayList<Dota2User>();

    private Context context;

    SPUtils spUtils = SPUtils.getInstance();

    public BindedPlayerAdapter(List<Dota2User> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(
                context).inflate(R.layout.binded_player_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Dota2User data = dataList.get(position);
        holder.tvUserId.setText("ID:" + D2Utils.getAccountId(data.getSteamid()));
        holder.tvUserName.setText(data.getPersonaname());
        ImgUtils.getInstance().loadImage(data.getAvatarfull(), holder.imgUserPortrait);
        holder.linearLayout.setOnClickListener(new Listener(data));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView imgUserPortrait;
        TextView tvUserId;
        TextView tvUserName;
        LinearLayout linearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            imgUserPortrait = (ImageView) itemView.findViewById(R.id.img_search_user_portrait);
            tvUserId = (TextView) itemView.findViewById(R.id.tv_search_userid);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_search_username);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.dota2_search_user_list_linearlayout);

        }
    }

    class Listener implements View.OnClickListener {

        private Dota2User data;

        public Listener(Dota2User data) {
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Dota2User.TAG, data);
            Intent intent = new Intent();
            intent.setClass(context, Dota2PreviewActivity.class);
            intent.putExtras(bundle);
            Set<String> steamIds = spUtils.getStringSet(Dota2User.TAG, null);
            try {
                if (steamIds == null) {
                    steamIds = new HashSet<>();
                } else {
                    steamIds = new HashSet<>(steamIds);
                }
                steamIds.add(data.getSteamid());
                spUtils.putStringSet(Dota2User.TAG, steamIds);
                DBHelperDota2.getInstance().getDao(Dota2User.class).create(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            context.startActivity(intent);
        }
    }
}
