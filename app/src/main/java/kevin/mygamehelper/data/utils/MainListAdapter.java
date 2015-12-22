package kevin.mygamehelper.data.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import kevin.utils.Utils;
import kevin.api.dota2.jsonResponse.Dota2Players;
import com.kevin.gamehelper.mygamehelper.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by Kevin on 2015/8/16.
 */
public class MainListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Dota2Players> dataList;

    BitmapUtils bitmapUtils;
    public MainListAdapter(Context context, ArrayList<Dota2Players> list) {
        this.context = context;
        this.dataList = list;
        bitmapUtils = new BitmapUtils(context);
    }

    public int getCount() {
        return 4;
    }

    public Object getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Dota2Players player = dataList.get(position);
        Log.e("datalist", position + dataList.toString() );
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.dota2_main_listitem, null);

            holder = new ViewHolder();
            holder.imgHero = (ImageButton) view.findViewById(R.id.img_hero);
            holder.imgPortrait = (ImageButton) view.findViewById(R.id.img_user_portrait);
            holder.item0 = (ImageButton) view.findViewById(R.id.img_item0);
            holder.item1 = (ImageButton) view.findViewById(R.id.img_item1);
            holder.item2 = (ImageButton) view.findViewById(R.id.img_item2);
            holder.item3 = (ImageButton) view.findViewById(R.id.img_item3);
            holder.item4 = (ImageButton) view.findViewById(R.id.img_item4);
            holder.item5 = (ImageButton) view.findViewById(R.id.img_item5);
            holder.tvIsMvp = (TextView) view.findViewById(R.id.tv_is_mvp);
            holder.tvHeroLevel = (TextView) view.findViewById(R.id.tv_hero_level);
            holder.tvUserId = (TextView) view.findViewById(R.id.tv_user_id);
            holder.tvKdaPercent = (TextView) view.findViewById(R.id.tv_kda_percent);
            holder.tvKda = (TextView) view.findViewById(R.id.tv_kda_main);
            holder.tvDamage = (TextView) view.findViewById(R.id.tv_damage);
            holder.tvDamagePercent = (TextView) view.findViewById(R.id.tv_damage_precent);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.e("player", player.toString());
        holder.tvDamagePercent.setText("伤害");
        holder.tvDamage.setText(player.getHero_damage());
        holder.tvKda.setText(player.getKills() + "/" + player.getDeaths() + "/" + player.getAssists());
        holder.tvHeroLevel.setText(player.getLevel());

        bitmapUtils.display(holder.imgHero,Utils.getHeroPicHphover(player.getHero_id(),true));
        bitmapUtils.display( holder.item0,Utils.getItemUrl(player.getItem_0(),true));
        bitmapUtils.display( holder.item1,Utils.getItemUrl(player.getItem_1(),true));
        bitmapUtils.display( holder.item2,Utils.getItemUrl(player.getItem_2(),true));
        bitmapUtils.display( holder.item3,Utils.getItemUrl(player.getItem_3(),true));
        bitmapUtils.display( holder.item4,Utils.getItemUrl(player.getItem_4(),true));
        bitmapUtils.display( holder.item5,Utils.getItemUrl(player.getItem_5(),true));

        return view;
    }

    class ViewHolder {
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
    }

}