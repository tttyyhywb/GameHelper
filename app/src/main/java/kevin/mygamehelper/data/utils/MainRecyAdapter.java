package kevin.mygamehelper.data.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Players;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;

/**
 * Created by Kevin on 2015/12/29.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class MainRecyAdapter extends RecyclerView.Adapter<MainRecyAdapter.MyHolder> {

    private final static int DATA = 0;
    private final static int DIRE = 1;
    private final static int RADIANT = 2;

    Context context;
    Dota2GameOutline match;
    Dota2MatchDetails detail;
    ArrayList<Dota2Players> players;

    public MainRecyAdapter(Context context, Dota2GameOutline dota2GameOutline, Dota2MatchDetails detail) {
        this.context = context;
        this.match = dota2GameOutline;
        this.detail = detail;
        players = detail.getPlayers();

        players.add(0,new Dota2Players());
        players.add(6,new Dota2Players());
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder;
        switch (viewType) {
            case RADIANT: {
                holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.main_detail_radiant, parent, false));
                break;
            }
            case DIRE: {
                holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.main_detail_dire, parent, false));
                break;
            }
            default: {
                holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.dota2_main_listitem, parent, false));
                break;
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        switch (getItemViewType(position)) {
            case RADIANT: {
                if(detail.getRadiant_win()){
                    holder.tvWin.setText("胜利");
                }else {
                    holder.tvWin.setText("失败");
                }
                break;
            }
            case DIRE: {
                if(detail.getRadiant_win()){
                    holder.tvWin.setText("失败");
                }else {
                    holder.tvWin.setText("胜利");
                }
                break;
            }
            default: {
                Dota2Players player = players.get(position);
                holder.tvDamagePercent.setText("伤害");
                holder.tvDamage.setText(player.getHero_damage());
                holder.tvKda.setText(player.getKills() + "/" + player.getDeaths() + "/" + player.getAssists());
                holder.tvHeroLevel.setText(player.getLevel());

                ImgUtils.getInstance().loadImage(D2Utils.getHeroPicHphover(player.getHero_id(),true),holder.imgHero);
                ImgUtils.getInstance().loadImage(D2Utils.getItemUrl(player.getItem_0(),true),holder.item0);
                ImgUtils.getInstance().loadImage(D2Utils.getItemUrl(player.getItem_1(),true),holder.item1);
                ImgUtils.getInstance().loadImage(D2Utils.getItemUrl(player.getItem_2(),true),holder.item2);
                ImgUtils.getInstance().loadImage(D2Utils.getItemUrl(player.getItem_3(),true),holder.item3);
                ImgUtils.getInstance().loadImage(D2Utils.getItemUrl(player.getItem_4(),true),holder.item4);
                ImgUtils.getInstance().loadImage(D2Utils.getItemUrl(player.getItem_5(),true),holder.item5);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return detail.getPlayers().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgHero;
        ImageView item0;
        ImageView item1;
        ImageView item2;
        ImageView item3;
        ImageView item4;
        ImageView item5;
        ImageView imgPortrait;
        TextView tvIsMvp;
        TextView tvHeroLevel;
        TextView tvUserId;
        TextView tvKdaPercent;
        TextView tvKda;
        TextView tvDamagePercent;
        TextView tvDamage;

        ImageView imgWin;
        TextView tvWin;

        public MyHolder(View itemView) {
            super(itemView);
            imgHero = (ImageView) itemView.findViewById(R.id.img_hero);
            imgPortrait = (ImageView) itemView.findViewById(R.id.img_user_portrait);
            item0 = (ImageView) itemView.findViewById(R.id.img_item0);
            item1 = (ImageView) itemView.findViewById(R.id.img_item1);
            item2 = (ImageView) itemView.findViewById(R.id.img_item2);
            item3 = (ImageView) itemView.findViewById(R.id.img_item3);
            item4 = (ImageView) itemView.findViewById(R.id.img_item4);
            item5 = (ImageView) itemView.findViewById(R.id.img_item5);
            tvIsMvp = (TextView) itemView.findViewById(R.id.tv_is_mvp);
            tvHeroLevel = (TextView) itemView.findViewById(R.id.tv_hero_level);
            tvUserId = (TextView) itemView.findViewById(R.id.tv_user_id);
            tvKdaPercent = (TextView) itemView.findViewById(R.id.tv_kda_percent);
            tvKda = (TextView) itemView.findViewById(R.id.tv_kda_main);
            tvDamage = (TextView) itemView.findViewById(R.id.tv_damage);
            tvDamagePercent = (TextView) itemView.findViewById(R.id.tv_damage_precent);
            //title view
            imgWin = (ImageView) itemView.findViewById(R.id.img_title_win);
            tvWin = (TextView) itemView.findViewById(R.id.tv_title_win);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return RADIANT;
        } else if (position == 6) {
            return DIRE;
        } else {
            return DATA;
        }
    }

    ;
}
