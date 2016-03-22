package kevin.utils;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import kevin.api.bean.Account;
import kevin.api.dota2.bean.Dota2User;
import kevin.database.DataBase.DBHelperDota2;

/**
 * Created by Kevin on 2016/3/22.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class AccountManager {

    private static AccountManager instance;

    private static Account account;

    private static Dao<Account, String> accountDao;

    public static void init() {
        if (instance == null) {
            synchronized (D2Utils.class) {
                if (instance == null) {
                    instance = new AccountManager();
                    try {
                        accountDao = DBHelperDota2.getInstance().getDao(Account.class);
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
    }

    public static void bindPlayer(Dota2User player) {
        account.setAssociatedPlayer(player);
        updata();
    }

    public static Dota2User getPlayer() {
        return account.getAssociatedPlayer();
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
