package kevin.mygamehelper.data.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import kevin.utils.D2Utils;
import kevin.api.dota2.bean.Dota2User;
import com.kevin.gamehelper.mygamehelper.R;
import kevin.mygamehelper.data.activity.Dota2PreviewActivity;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2015/9/2.
 */
public class UserIdListAdapter extends BaseAdapter {

    List<Dota2User>dataList = new ArrayList<Dota2User>();
    Context context ;
    BitmapUtils bitmapUtils;

    public UserIdListAdapter(Context context,List<Dota2User> dataList) {
        this.dataList = dataList;
        this.context = context;
        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Dota2User data = dataList.get(position);
        ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dota2_search_user_list_item, null);

            holder = new ViewHolder();
            holder.imgUserPortrait = (ImageButton)view.findViewById(R.id.img_search_user_portrait);
            holder.tvUserId = (TextView)view.findViewById(R.id.tv_search_userid);
            holder.tvUserName = (TextView)view.findViewById(R.id.tv_search_username);
            holder.linearLayout =(LinearLayout)view.findViewById(R.id.dota2_search_user_list_linearlayout);
            Listener listener = new Listener(data,context);
            holder.linearLayout.setOnClickListener(listener);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvUserId.setText("ID:"+D2Utils.getAccountId(data.getSteamid()));
        holder.tvUserName.setText(data.getPersonaname());
        bitmapUtils.display(holder.imgUserPortrait,data.getAvatarfull());
        return view;
    }

    static class ViewHolder{
        ImageButton imgUserPortrait;
        TextView tvUserId;
        TextView tvUserName;
        LinearLayout linearLayout;
    }

    class Listener implements OnClickListener{

        private Dota2User data;
        private Context context;

        public Listener(Dota2User data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Dota2User.TAG,data);
            Intent intent = new Intent();
            intent.setClass(context, Dota2PreviewActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    };
}
