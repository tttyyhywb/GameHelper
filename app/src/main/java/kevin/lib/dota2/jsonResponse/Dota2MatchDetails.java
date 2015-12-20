package kevin.lib.dota2.jsonResponse;

import kevin.lib.base.gameBase.GameEnvirnment;

import java.util.ArrayList;

/**
 * Created by kevin on 2015/8/10.
 */
public class Dota2MatchDetails extends GameEnvirnment {

    public final static String DOTA2MATCHDETAILS = "dota2matchdetails";

    public Dota2MatchDetails details;

    ArrayList<Dota2Players> players;
    String radiant_win;
    int duration;
    String match_seq_num;
    String start_time;
    String match_id;
    String tower_status_radiant;
    String tower_status_dire;
    String barracks_status_radiant;
    String barracks_status_dire;
    String cluster;
    String first_blood_time;
    String lobby_type;
    String human_players;
    String leagueid;
    String positive_votes;
    String negative_votes;
    String game_mode;
    String engine;

    public ArrayList<Dota2Players> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Dota2Players> players) {
        this.players = players;
    }

    public String getRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(String radiant_win) {
        this.radiant_win = radiant_win;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getTower_status_radiant() {
        return tower_status_radiant;
    }

    public void setTower_status_radiant(String tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }

    public String getTower_status_dire() {
        return tower_status_dire;
    }

    public void setTower_status_dire(String tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }

    public String getBarracks_status_radiant() {
        return barracks_status_radiant;
    }

    public void setBarracks_status_radiant(String barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }

    public String getBarracks_status_dire() {
        return barracks_status_dire;
    }

    public void setBarracks_status_dire(String barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getFirst_blood_time() {
        return first_blood_time;
    }

    public void setFirst_blood_time(String first_blood_time) {
        this.first_blood_time = first_blood_time;
    }

    public String getLobby_type() {
        return lobby_type;
    }

    public void setLobby_type(String lobby_type) {
        this.lobby_type = lobby_type;
    }

    public String getHuman_players() {
        return human_players;
    }

    public void setHuman_players(String human_players) {
        this.human_players = human_players;
    }

    public String getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }

    public String getPositive_votes() {
        return positive_votes;
    }

    public void setPositive_votes(String positive_votes) {
        this.positive_votes = positive_votes;
    }

    public String getNegative_votes() {
        return negative_votes;
    }

    public void setNegative_votes(String negative_votes) {
        this.negative_votes = negative_votes;
    }

    public String getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(String game_mode) {
        this.game_mode = game_mode;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Dota2MatchDetails{" +
                "players=" + players +
                ", radiant_win='" + radiant_win + '\'' +
                ", duration='" + duration + '\'' +
                ", match_seq_num='" + match_seq_num + '\'' +
                ", start_time='" + start_time + '\'' +
                ", match_id='" + match_id + '\'' +
                ", tower_status_radiant='" + tower_status_radiant + '\'' +
                ", tower_status_dire='" + tower_status_dire + '\'' +
                ", barracks_status_radiant='" + barracks_status_radiant + '\'' +
                ", barracks_status_dire='" + barracks_status_dire + '\'' +
                ", cluster='" + cluster + '\'' +
                ", first_blood_time='" + first_blood_time + '\'' +
                ", lobby_type='" + lobby_type + '\'' +
                ", human_players='" + human_players + '\'' +
                ", leagueid='" + leagueid + '\'' +
                ", positive_votes='" + positive_votes + '\'' +
                ", negative_votes='" + negative_votes + '\'' +
                ", game_mode='" + game_mode + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }

}
