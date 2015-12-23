package kevin.database.Dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Kevin on 2015/12/23.
 * DESCRIPTION:操作dota2英雄数据库的类
 * email:493243390@qq.com
 */
public class DHeroDao extends BaseDaoImpl {

    protected DHeroDao(ConnectionSource connectionSource, Class dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected DHeroDao(ConnectionSource connectionSource, DatabaseTableConfig tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    protected DHeroDao(Class dataClass) throws SQLException {
        super(dataClass);
    }


}
