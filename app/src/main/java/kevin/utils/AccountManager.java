package kevin.utils;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import kevin.api.dota2.bean.Dota2User;
import kevin.database.DataBase.DBHelperDota2;

/**
 * Created by Kevin on 2016/3/22.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class AccountManager {

    public static String TAG = AccountManager.class.getSimpleName();

    private static AccountManager instance;

    private static Account account;

    private static Dao<Account, String> accountDao;

    private static Dao<Dota2User, String> playeresDao;

    public static void init() {
        if (instance == null) {
            synchronized (D2Utils.class) {
                if (instance == null) {
                    instance = new AccountManager();
                    try {
                        accountDao = DBHelperDota2.getInstance().getDao(Account.class);
                        playeresDao = DBHelperDota2.getInstance().getDao(Dota2User.class);
                        String accountUsername = SPUtils.getInstance().getString(TAG, "");
                        account = accountDao.queryForId(accountUsername);
//                        Log.e(TAG, account.toString() );
//                        for(Dota2User a : account.getPlayers()){
//                            Log.e(TAG, a.toString() );
//                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            throw new IllegalArgumentException("instance is null ,call init() first");
        }
        return instance;
    }

    public static void modifyPassword(String password) {
        account.setPassword(password);
        updata();
    }

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        AccountManager.account = account;
        create();
        SPUtils.getInstance().putString(TAG, account.getUsername());
    }

    public static void removeAccount() {
        SPUtils.getInstance().putString(TAG, "");
        AccountManager.account = null;
    }

    public static void addBindPlayer(Dota2User player) {
        player.setAccount(account);
        updatePlayer(player);
    }

    public static ArrayList<Dota2User> getBindPlayer() {
        ArrayList<Dota2User> players = new ArrayList<>();
        if(account.getPlayers() == null){
            return null;
        }
        for (Dota2User player : account.getPlayers()) {
            players.add(player);
        }
        return players;
    }

    private static void updatePlayer(Dota2User player) {
        try {
            playeresDao.update(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updata() {
        try {
            accountDao.update(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void create() {
        try {
            accountDao.create(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
