package kevin.database.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import kevin.api.dota2.bean.Dota2LobbyType;
import kevin.utils.Account;
import kevin.api.dota2.bean.Dota2Equipment;
import kevin.api.dota2.bean.Dota2Hero;
import kevin.api.dota2.bean.Dota2User;
import kevin.mygamehelper.news.utils.News;

/**
 * Created by Kevin on 2015/11/11.
 */
public class DBHelperDota2 extends OrmLiteSqliteOpenHelper {

    private final static String SQL_NAME = "my_game_helper.db";

    private final static int VERSION = 1;

    private static DBHelperDota2 instance;

    private DBHelperDota2(Context context) {
        super(context, SQL_NAME, null, VERSION);
    }

    public static void init(Context context){
        if(instance ==  null) {
            synchronized (DBHelperDota2.class) {
                if (instance == null) {
                    instance = new DBHelperDota2(context);
                }
            }
        }
    }

    public static DBHelperDota2 getInstance(){
        if(instance == null){
            throw(new IllegalArgumentException("instance is null , call init(Context) , D2Utils must be initalize after this"));
        }else{
            return instance;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Dota2Hero.class);
            TableUtils.createTable(connectionSource, Dota2Equipment.class);
            TableUtils.createTable(connectionSource, Dota2User.class);
            TableUtils.createTable(connectionSource, News.class);
            TableUtils.createTable(connectionSource, Account.class);
            TableUtils.createTable(connectionSource, Dota2LobbyType.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

}
