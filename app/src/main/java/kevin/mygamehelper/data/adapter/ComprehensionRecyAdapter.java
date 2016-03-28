package kevin.mygamehelper.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;
import java.util.Calendar;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Players;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.Dota2MainMatchActivity;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;

/**
 * Created by Kevin on 2015/12/28.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class ComprehensionRecyAdapter extends RecyclerView.Adapter<ComprehensionRecyAdapter.MyHolder> {

    ArrayList<Dota2GameOutline> matches;
    Dota2MatchDetails[] details;
    Dota2User account;
    Context context;
    AssetManager assetManager;
    Dota2GameOutline match;
    Dota2MatchDetails detail;
    private int showCount = 0;

    public ComprehensionRecyAdapter(Context context, ArrayList<Dota2GameOutline> matches, Dota2User account, Dota2MatchDetails[] details) {
        this.account = account;
        this.context = context;
        this.matches = matches;
        assetManager = context.getResources().getAssets();
        this.details = details;
    }

    //showCount决定综合页面展示多少item
    public ComprehensionRecyAdapter(Context context, ArrayList<Dota2GameOutline> matches, Dota2User account, Dota2MatchDetails[] details, int showCount) {
        this(context, matches, account, details);
        if (showCount != 0 && showCount <matches.size()) {
            this.showCount = showCount;
        }
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.dota2_preview_listitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        match = matches.get(position);
        detail = details[position];

        Dota2Players player = null;
        for (Dota2Players p : detail.getPlayers()) {
            if (p.getAccount_id().equals(D2Utils.getAccountId(account.getSteamid()))) {
                player = p;
                break;
            }
        }

        if ((player.getPlayer_slot() > 100 && detail.getRadiant_win()) || (player.getPlayer_slot() < 100 && !detail.getRadiant_win())) {
            holder.tvResult.setTextColor(Color.rgb(154, 11, 35));
            holder.tvResult.setText("失败");
        } else {
            holder.tvResult.setTextColor(Color.rgb(56, 174, 47));
            holder.tvResult.setText("胜利");
        }
        ImgUtils.getInstance().loadImage(D2Utils.getHeroPicHphover(player.getHero_id(), true), holder.picHero);
        holder.tvKda.setText((int)player.getKills() + "/" + (int)player.getDeaths() + "/" + (int)player.getAssists());
        holder.tvDuration.setText(detail.getDuration() / 60 + "分钟");

        Long time = new Long(detail.getStart_time()+"000");
        Calendar date = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        date.setTimeInMillis(time);

        if (date.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            holder.tvEndTime.setText((date.get(Calendar.MONTH)+1) + "." + date.get(Calendar.DATE));
        } else {
            holder.tvEndTime.setText(date.get(Calendar.YEAR) + "." + (date.get(Calendar.MONTH)+1) + "." + date.get(Calendar.DATE));
        }


        Bundle bundle = new Bundle();
        bundle.putSerializable(match.TAG, match);
        bundle.putSerializable(detail.TAG, detail);
        holder.llPreview.setTag(bundle);
        holder.llPreview.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if (showCount < matches.size() && showCount != 0) {
            return showCount;
        } else {
            return matches.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView picHero;
        TextView tvResult;
        TextView tvLobbyType;
        TextView tvKda;
        TextView tvDuration;
        LinearLayout llPreview;
        TextView tvEndTime;
        public MyHolder(View itemView) {
            super(itemView);
            picHero = (ImageView) itemView.findViewById(R.id.img_hero_preview);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
            tvResult = (TextView) itemView.findViewById(R.id.tv_result);
            tvLobbyType = (TextView) itemView.findViewById(R.id.tv_lobby_type);
            tvKda = (TextView) itemView.findViewById(R.id.tv_kda_preview);
            tvEndTime = (TextView) itemView.findViewById(R.id.tv_end_time);
            llPreview = (LinearLayout) itemView.findViewById(R.id.ll_preview);
        }
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = (Bundle) v.getTag();
            Intent intent = new Intent();
            intent.setClass(context, Dota2MainMatchActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    };

}
