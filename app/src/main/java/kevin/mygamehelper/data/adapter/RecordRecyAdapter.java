package kevin.mygamehelper.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.utils.RecordItem;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;

/**
 * Created by Kevin on 2016/2/23.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RecordRecyAdapter extends RecyclerView.Adapter<RecordRecyAdapter.MyHolder>{

    Context context;
    ArrayList<RecordItem> data;
    public RecordRecyAdapter(Context contexts, ArrayList<RecordItem> data) {
        this.context = contexts;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(
                context).inflate(R.layout.highest_record_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvRecordDetial.setText(data.get(position).getRecord()+"");
        if(data.get(position).isIfWin()){
            holder.tvIfWin.setTextColor(Color.rgb(154, 11, 35));
            holder.tvIfWin.setText("失败");
        }else{
            holder.tvIfWin.setTextColor(Color.rgb(56, 174, 47));
            holder.tvIfWin.setText("胜利");
        }
        holder.tvRecordItem.setText(data.get(position).getName());
        ImgUtils.getInstance().loadImage(D2Utils.getHeroPicHphover(data.get(position).getHeroId(), true), holder.imgHerePic);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imgHerePic;
        TextView tvRecordDetial;
        TextView tvIfWin;
        TextView tvRecordItem;

        public MyHolder(View itemView) {
            super(itemView);
            imgHerePic = (ImageView) itemView.findViewById(R.id.here_pic);
            tvIfWin = (TextView) itemView.findViewById(R.id.if_win);
            tvRecordDetial = (TextView) itemView.findViewById(R.id.record_detail);
            tvRecordItem = (TextView) itemView.findViewById(R.id.record_item);
        }
    }
}
