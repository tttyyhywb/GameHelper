package kevin.mygamehelper.data.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.BitmapUtils;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Players;
import kevin.utils.D2Utils;

/**
 * Created by Kevin on 2015/12/29.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class MainRecyAdapter extends RecyclerView.Adapter<MainRecyAdapter.MyHolder> {

    Context context;
    Dota2GameOutline match;
    Dota2MatchDetails detail;
    BitmapUtils bitmapUtils;

    public MainRecyAdapter(Context context, Dota2GameOutline dota2GameOutline, Dota2MatchDetails detail){
        this.context = context;
        this.match = dota2GameOutline;
        this.detail = detail;
        bitmapUtils = new BitmapUtils(context);
        Log.e("main",match.toString());
        Log.e("maindetail",detail.toString());
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.dota2_main_listitem,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Dota2Players player = detail.getPlayers().get(position);
        holder.tvDamagePercent.setText("伤害");
        holder.tvDamage.setText(player.getHero_damage());
        holder.tvKda.setText(player.getKills() + "/" + player.getDeaths() + "/" + player.getAssists());
        holder.tvHeroLevel.setText(player.getLevel());

        bitmapUtils.display(holder.imgHero, D2Utils.getHeroPicHphover(player.getHero_id(),true));
        bitmapUtils.display( holder.item0,D2Utils.getItemUrl(player.getItem_0(),true));
        bitmapUtils.display( holder.item1,D2Utils.getItemUrl(player.getItem_1(),true));
        bitmapUtils.display( holder.item2,D2Utils.getItemUrl(player.getItem_2(),true));
        bitmapUtils.display( holder.item3,D2Utils.getItemUrl(player.getItem_3(),true));
        bitmapUtils.display( holder.item4,D2Utils.getItemUrl(player.getItem_4(),true));
        bitmapUtils.display( holder.item5,D2Utils.getItemUrl(player.getItem_5(),true));

    }

    @Override
    public int getItemCount() {
        return detail.getPlayers().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageButton imgHero;
        ImageButton item0;
        ImageButton item1;
        ImageButton item2;
        ImageButton item3;
        ImageButton item4;
        ImageButton item5;
        ImageButton imgPortrait;
        TextView tvIsMvp;
        TextView tvHeroLevel;
        TextView tvUserId;
        TextView tvKdaPercent;
        TextView tvKda;
        TextView tvDamagePercent;
        TextView tvDamage;

        public MyHolder(View itemView) {
            super(itemView);
            imgHero = (ImageButton) itemView.findViewById(R.id.img_hero);
            imgPortrait = (ImageButton) itemView.findViewById(R.id.img_user_portrait);
            item0 = (ImageButton) itemView.findViewById(R.id.img_item0);
            item1 = (ImageButton) itemView.findViewById(R.id.img_item1);
            item2 = (ImageButton) itemView.findViewById(R.id.img_item2);
            item3 = (ImageButton) itemView.findViewById(R.id.img_item3);
            item4 = (ImageButton) itemView.findViewById(R.id.img_item4);
            item5 = (ImageButton) itemView.findViewById(R.id.img_item5);
            tvIsMvp = (TextView) itemView.findViewById(R.id.tv_is_mvp);
            tvHeroLevel = (TextView) itemView.findViewById(R.id.tv_hero_level);
            tvUserId = (TextView) itemView.findViewById(R.id.tv_user_id);
            tvKdaPercent = (TextView) itemView.findViewById(R.id.tv_kda_percent);
            tvKda = (TextView) itemView.findViewById(R.id.tv_kda_main);
            tvDamage = (TextView) itemView.findViewById(R.id.tv_damage);
            tvDamagePercent = (TextView) itemView.findViewById(R.id.tv_damage_precent);
        }
    }
}
