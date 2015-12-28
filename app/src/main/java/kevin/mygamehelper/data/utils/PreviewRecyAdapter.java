package kevin.mygamehelper.data.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2Players;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.activity.Dota2MainMatchActivity;
import kevin.utils.D2Utils;

/**
 * Created by Kevin on 2015/12/28.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class PreviewRecyAdapter extends RecyclerView.Adapter<PreviewRecyAdapter.MyHolder> {

    ArrayList<Dota2GameOutline> matches;
    Dota2User  account;
    Context context;
    AssetManager assetManager;
    BitmapUtils bitmapUtils;
    public final int COUNT = 6;
    Dota2GameOutline match;

    public PreviewRecyAdapter(Context context, ArrayList<Dota2GameOutline> matches, Dota2User account){
        this.account = account;
        this.context = context;
        this.matches = matches;
        assetManager = context.getResources().getAssets();
        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.dota2_preview_listitem,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        match = matches.get(position);

        Log.e("~~~~~~~~~",position + match.toString());

        Dota2Players players = null;
        for(Dota2Players p : match.getDetails().getPlayers()){
            if(p.getAccount_id().equals( D2Utils.getAccountId(account.getSteamid()))){
                players = p;
                break;
            }
        }

        if((players.getPlayer_slot()>100 && match.getDetails().getRadiant_win()=="true")||(players.getPlayer_slot()<100 && match.getDetails().getRadiant_win()=="false")){
            holder.tvResult.setTextColor(Color.rgb(154,11,35));
            holder.tvResult.setText("失败");
        }else{
            holder.tvResult.setTextColor(Color.rgb(56,174,47));
            holder.tvResult.setText("胜利");
        }
        bitmapUtils.display(holder.picHero,D2Utils.getHeroPicHphover(players.getHero_id(), true));
        holder.tvKda.setText(players.getKills()+"/"+players.getDeaths()+"/"+players.getAssists());
        holder.tvEndTime.setText(match.getDetails().getDuration()/60+"分钟");

        holder.llPreview.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageButton picHero;
        TextView tvResult;
        TextView tvLobbyType;
        TextView tvKda;
        TextView tvEndTime;
        LinearLayout llPreview;

        public MyHolder(View itemView) {
            super(itemView);
            picHero = (ImageButton)itemView.findViewById(R.id.img_hero_preview);
            tvEndTime = (TextView)itemView.findViewById(R.id.tv_end_time);
            tvResult = (TextView)itemView.findViewById(R.id.tv_result);
            tvLobbyType = (TextView)itemView.findViewById(R.id.tv_lobby_type);
            tvKda = (TextView)itemView.findViewById(R.id.tv_kda_preview);
            llPreview=(LinearLayout)itemView.findViewById(R.id.ll_preview);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(match.TAG, match);
            Intent intent = new Intent();
            intent.setClass(context , Dota2MainMatchActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    };
}
