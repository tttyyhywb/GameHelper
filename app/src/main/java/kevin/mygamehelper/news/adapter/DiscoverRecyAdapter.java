package kevin.mygamehelper.news.adapter;

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

import java.util.List;

import kevin.mygamehelper.data.Dota2MainMatchActivity;
import kevin.mygamehelper.news.NewsActivity;
import kevin.mygamehelper.news.utils.News;
import kevin.utils.D2Utils;
import kevin.utils.ImgUtils;

/**
 * Created by Kevin on 2016/3/1.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class DiscoverRecyAdapter extends RecyclerView.Adapter<DiscoverRecyAdapter.MyHolder> {

    private List<News> data;
    private Context context;

    public DiscoverRecyAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.dota2_discover_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        News news = data.get(position);
        ImgUtils.getInstance().loadImage( news.getImgUrl() , holder.imgPicture);
        holder.content.setText( news.getContent());
        holder.title.setText(news.getTitle());

        Bundle bundle = new Bundle();
        bundle.putSerializable(News.TAG, news);
        holder.llNews.setTag(bundle);
        holder.llNews.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        ImageView imgPicture;
        LinearLayout llNews;
        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            imgPicture = (ImageView) itemView.findViewById(R.id.img_dicover);
            llNews = (LinearLayout) itemView.findViewById(R.id.ll_news_pre);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = (Bundle) v.getTag();
            Intent intent = new Intent();
            intent.setClass(context, NewsActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    };
}
