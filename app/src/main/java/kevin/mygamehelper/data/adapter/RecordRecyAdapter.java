package kevin.mygamehelper.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2GameOutline;
import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.data.Dota2MainMatchActivity;
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
    Dota2GameOutline match;
    Dota2MatchDetails detail;

    public RecordRecyAdapter(Context contexts, ArrayList<RecordItem> data,Dota2GameOutline match , Dota2MatchDetails detial) {
        this.context = contexts;
        this.data = data;
        this.match = match;
        this.detail = detial;
        Log.e("recordrecyadater", "RecordRecyAdapter: " + this.data );
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
        holder.tvRecordDetial.setText((int)data.get(position).getRecord()+"");
        if(!data.get(position).isIfWin()){
            holder.tvIfWin.setTextColor(Color.rgb(154, 11, 35));
            holder.tvIfWin.setText("失败");
        }else{
            holder.tvIfWin.setTextColor(Color.rgb(56, 174, 47));
            holder.tvIfWin.setText("胜利");
        }
        holder.tvRecordItem.setText(data.get(position).getName());
        ImgUtils.getInstance().loadImage(D2Utils.getHeroPicHphover(data.get(position).getHeroId(), true), holder.imgHerePic);

        Bundle bundle = new Bundle();
        bundle.putSerializable(match.TAG, match);
        bundle.putSerializable(detail.TAG, data.get(position).getDetial());
        holder.llRecord.setTag(bundle);
        holder.llRecord.setOnClickListener(listener);
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
        LinearLayout llRecord;

        public MyHolder(View itemView) {
            super(itemView);
            imgHerePic = (ImageView) itemView.findViewById(R.id.here_pic);
            tvIfWin = (TextView) itemView.findViewById(R.id.if_win);
            tvRecordDetial = (TextView) itemView.findViewById(R.id.record_detail);
            tvRecordItem = (TextView) itemView.findViewById(R.id.record_item);
            llRecord = (LinearLayout) itemView.findViewById(R.id.ll_record);
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
