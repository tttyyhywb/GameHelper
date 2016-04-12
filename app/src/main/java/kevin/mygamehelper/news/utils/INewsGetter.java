package kevin.mygamehelper.news.utils;

import java.util.List;

/**
 * Created by Kevin on 2016/3/2.
 * DESCRIPTION:用于得到新闻的数据list
 * email:493243390@qq.com
 */
public interface INewsGetter {

    public List<News> getData(int startIndex, int endIndex);

}
