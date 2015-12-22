package kevin.api.dota2.jsonResponse;


import kevin.api.base.gameBase.bean.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kevin on 2015/7/20.
 */
public class Dota2User extends User {

    public final static String DOTA2USER = "dota2user";

    ArrayList<Players> players;

    public ArrayList<Players> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Players> players) {
        this.players = players;
    }

    public class Players implements Serializable{

        String steamid;
        String profilestate;
        String profileurl;
        String avatar;
        String avatarmedium;
        String avatarfull;
        String personastate;
        String primaryclanid;
        String personastateflags;
        String personaname;

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
            return "Players{" +
                    "steamid='" + steamid + '\'' +
                    ", profilestate='" + profilestate + '\'' +
                    ", profileurl='" + profileurl + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", avatarmedium='" + avatarmedium + '\'' +
                    ", avatarfull='" + avatarfull + '\'' +
                    ", personastate='" + personastate + '\'' +
                    ", primaryclanid='" + primaryclanid + '\'' +
                    ", personastateflags='" + personastateflags + '\'' +
                    ", personaname='" + personaname + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Dota2User{" +
                "players=" + players +
                '}';
    }
}