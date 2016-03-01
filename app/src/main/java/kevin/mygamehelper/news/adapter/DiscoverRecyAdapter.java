package kevin.mygamehelper.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

/**
 * Created by Kevin on 2016/3/1.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class DiscoverRecyAdapter extends RecyclerView.Adapter<DiscoverRecyAdapter.MyHolder> {

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        ImageView imgPicture;

        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            imgPicture = (ImageView) itemView.findViewById(R.id.img_dicover);
        }
    }
}
