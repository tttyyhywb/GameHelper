package kevin.api.dota2.bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import kevin.api.base.gameBase.bean.User;
import kevin.utils.Account;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kevin on 2015/7/20.
 */
@DatabaseTable(tableName = "dota2_user")
public class Dota2User extends User implements Serializable {

    public final static String TAG = "dota2user";

    ArrayList<Dota2User> players;

    @DatabaseField(id=true)
    String steamid;

    @DatabaseField( columnName = "profilestate")
    String profilestate;

    @DatabaseField( columnName = "profileurl")
    String profileurl;

    @DatabaseField( columnName = "avatar")
    String avatar;

    @DatabaseField( columnName = "avatarmedium")
    String avatarmedium;

    @DatabaseField( columnName = "avatarfull")
    String avatarfull;

    @DatabaseField( columnName = "personastate")
    String personastate;

    @DatabaseField( columnName = "primaryclanid")
    String primaryclanid;

    @DatabaseField( columnName = "personastateflags")
    String personastateflags;

    @DatabaseField( columnName = "personaname")
    String personaname;

    @DatabaseField(columnName = "account",canBeNull = true,foreign = true)
    Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<Dota2User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Dota2User> players) {
        this.players = players;
    }

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getProfilestate() {
        return profilestate;
    }

    public void setProfilestate(String profilestate) {
        this.profilestate = profilestate;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarmedium() {
        return avatarmedium;
    }

    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    public String getAvatarfull() {
        return avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public String getPersonastate() {
        return personastate;
    }

    public void setPersonastate(String personastate) {
        this.personastate = personastate;
    }

    public String getPrimaryclanid() {
        return primaryclanid;
    }

    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    public String getPersonastateflags() {
        return personastateflags;
    }

    public void setPersonastateflags(String personastateflags) {
        this.personastateflags = personastateflags;
    }

    @Override
    public String toString() {
        return "Dota2User{" +
                "avatar='" + avatar + '\'' +
                ", players=" + players +
                ", steamid='" + steamid + '\'' +
                ", profilestate='" + profilestate + '\'' +
                ", profileurl='" + profileurl + '\'' +
                ", avatarmedium='" + avatarmedium + '\'' +
                ", avatarfull='" + avatarfull + '\'' +
                ", personastate='" + personastate + '\'' +
                ", primaryclanid='" + primaryclanid + '\'' +
                ", personastateflags='" + personastateflags + '\'' +
                ", personaname='" + personaname + '\'' +
                '}';
    }
}