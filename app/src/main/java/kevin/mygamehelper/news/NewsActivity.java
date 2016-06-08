package kevin.mygamehelper.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kevin.gamehelper.mygamehelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import kevin.mygamehelper.news.utils.News;

/**
 * Created by Administrator on 2016/6/6.
 */
public class NewsActivity extends Activity {

    public static final String TAG = NewsActivity.class.getSimpleName();

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dota2_news);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        Intent intent = getIntent();
        News news = (News)intent.getSerializableExtra(News.TAG);
        title.setText(news.getTitle());
        content.setText(news.getContent());
    }
}
