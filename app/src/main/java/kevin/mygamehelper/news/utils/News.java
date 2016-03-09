package kevin.mygamehelper.news.utils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import kevin.api.base.gameBase.BaseJson;

/**
 * Created by Kevin on 2016/3/1.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
@DatabaseTable(tableName = "news")
public class News extends BaseJson {

    ArrayList<News> news ;

    @DatabaseField(id = true)
    String title;

    @DatabaseField(columnName = "content")
    String content;

    @DatabaseField(columnName = "img_url")
    String imgUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    public News(String content, String imgUrl, String title) {
        this.content = content;
        this.imgUrl = imgUrl;
        this.title = title;
    }

}
