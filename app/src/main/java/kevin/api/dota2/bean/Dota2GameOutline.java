package kevin.api.dota2.bean;

import kevin.api.base.gameBase.bean.GameEnvirnment;

import java.util.ArrayList;

/**
 * Created by Kevin on 2015/7/20.
 */
public class Dota2GameOutline extends GameEnvirnment {

    public final static String TAG = "dota2gamedetials";

    String match_id ;
    String match_seq_num;
    String start_time;
    String lobby_type ;
    String radiant_team_id;
    String dire_team_id;
    ArrayList<Dota2User> players;

    Dota2MatchDetails details;

    public Dota2MatchDetails getDetails() {
        return details;
    }

    public void setDetails(Dota2MatchDetails details) {
        this.details = details;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getMatch_seq_num() {
        return match_seq_num;
    }

    public void setMatch_seq_num(String match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getLobby_type() {
        return lobby_type;
    }

    public void setLobby_type(String lobby_type) {
        this.lobby_type = lobby_type;
    }

    public String getRadiant_team_id() {
        return radiant_team_id;
    }

    public void setRadiant_team_id(String radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }

    public String getDire_team_id() {
        return dire_team_id;
    }

    public void setDire_team_id(String dire_team_id) {
        this.dire_team_id = dire_team_id;
    }

    public ArrayList<Dota2User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Dota2User> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Dota2GameOutline{" +
                "match_id='" + match_id + '\'' +
                ", match_seq_num='" + match_seq_num + '\'' +
                ", start_time='" + start_time + '\'' +
                ", lobby_type='" + lobby_type + '\'' +
                ", radiant_team_id='" + radiant_team_id + '\'' +
                ", dire_team_id='" + dire_team_id + '\'' +
                ", players=" + players +
                ", details=" + details +
                '}';
    }

    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
}
