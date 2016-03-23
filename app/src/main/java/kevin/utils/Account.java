package kevin.utils;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

import kevin.api.base.BaseJson;
import kevin.api.dota2.bean.Dota2User;

/**
 * Created by Kevin on 2016/3/18.
 * DESCRIPTION:发送网络请求时包含在 ApiResult 类下
 * email:493243390@qq.com
 */
@DatabaseTable(tableName = "account")
public class Account extends BaseJson {

    @DatabaseField(id = true)
    String username;

    @DatabaseField(columnName = "password")
    String password;

    @DatabaseField(columnName = "forget_hint")
    String forgetHint;

    @DatabaseField(columnName = "forget_answer")
    String forgetAnswer;

    @ForeignCollectionField
    Collection<Dota2User> players;

    public Collection<Dota2User> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Dota2User> players) {
        this.players = players;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getForgetAnswer() {
        return forgetAnswer;
    }

    public void setForgetAnswer(String forgetAnswer) {
        this.forgetAnswer = forgetAnswer;
    }

    public String getForgetHint() {
        return forgetHint;
    }

    public void setForgetHint(String forgetHint) {
        this.forgetHint = forgetHint;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", forgetHint='" + forgetHint + '\'' +
                ", forgetAnswer='" + forgetAnswer + '\'' +
                ", players=" + players +
                '}';
    }
}
