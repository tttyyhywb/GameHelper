package kevin.mygamehelper.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kevin.utils.Utils;
import kevin.api.dota2.jsonResponse.Dota2GameOutline;
import kevin.api.dota2.jsonResponse.Dota2Players;
import kevin.api.dota2.jsonResponse.Dota2User;
import com.kevin.gamehelper.mygamehelper.R;
import kevin.mygamehelper.data.activity.Dota2MainMatchActivity;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by Kevin on 2015/8/27.
 */
public class PreviewListAdapter extends BaseAdapter {

    ArrayList<Dota2GameOutline> matches;
    Dota2User.Players account;
    Context context;
    AssetManager assetManager;
    BitmapUtils bitmapUtils;
    public final int COUNT = 6;
    Dota2GameOutline match;
    public PreviewListAdapter(Context context, ArrayList<Dota2GameOutline> matches, Dota2User.Players account) {
        this.account = account;
        this.context = context;
        this.matches = matches;
        assetManager = context.getResources().getAssets();
        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;
        match = matches.get(position);
//      Log.e("account",account.toString() );
//      Log.e("matchccccccccccccc", match.toString());
        Dota2Players players = null;
        for(Dota2Players p : match.getDetails().getPlayers()){
//           Log.e("1", p.getAccount_id());
//           Log.e("2", Utils.getAccountId(account.getSteamid()));
            if(p.getAccount_id().equals( Utils.getAccountId(account.getSteamid()))){
                players = p;
                break;
            }
        }

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dota2_preview_listitem, null);

            holder = new ViewHolder();
            holder.picHero = (ImageButton)view.findViewById(R.id.img_hero_preview);
            holder.tvEndTime = (TextView)view.findViewById(R.id.tv_end_time);
            holder.tvResult = (TextView)view.findViewById(R.id.tv_result);
            holder.tvLobbyType = (TextView)view.findViewById(R.id.tv_lobby_type);
            holder.tvKda = (TextView)view.findViewById(R.id.tv_kda_preview);
            holder.llPreview=(LinearLayout)view.findViewById(R.id.ll_preview);
            holder.llPreview.setOnClickListener(listener);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        //Log.e("player", players.toString());
        //设置数据
        if((players.getPlayer_slot()>100 && match.getDetails().getRadiant_win()=="true")||(players.getPlayer_slot()<100 && match.getDetails().getRadiant_win()=="false")){
            holder.tvResult.setTextColor(Color.rgb(154,11,35));
            holder.tvResult.setText("失败");
        }else{
            holder.tvResult.setTextColor(Color.rgb(56,174,47));
            holder.tvResult.setText("胜利");
        }
        bitmapUtils.display(holder.picHero,Utils.getHeroPicHphover(players.getHero_id(), true));
        holder.tvKda.setText(players.getKills()+"/"+players.getDeaths()+"/"+players.getAssists());
        holder.tvEndTime.setText(match.getDetails().getDuration()/60+"分钟");
        return view;
    }

    static class ViewHolder{
        ImageButton picHero;
        TextView tvResult;
        TextView tvLobbyType;
        TextView tvKda;
        TextView tvEndTime;
        LinearLayout llPreview;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(match.DOTA2GAMEDETIALS , match);
            Intent intent = new Intent();
            intent.setClass(context , Dota2MainMatchActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    };
}
