package kevin.mygamehelper.news.utils;

import java.sql.SQLException;
import java.util.List;

import kevin.database.DataBase.DBHelperDota2;

/**
 * Created by Kevin on 2016/3/3.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class SqlNewsGetter implements NewsGetter {
    @Override
    public List<News> getData(int startIndex, int endIndex) {
        try {
            return DBHelperDota2.getInstance().getDao(News.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
