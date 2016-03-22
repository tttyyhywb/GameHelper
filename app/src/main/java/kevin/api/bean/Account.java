package kevin.api.bean;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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

    @DatabaseField(columnName = "account_steamId",canBeNull = true,foreign = true)
    Dota2User associatedPlayer;

    public Dota2User getAssociatedPlayer() {
        return associatedPlayer;
    }

    public void setAssociatedPlayer(Dota2User associatedPlayer) {
        this.associatedPlayer = associatedPlayer;
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
}
