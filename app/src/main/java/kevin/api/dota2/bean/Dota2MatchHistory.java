package kevin.api.dota2.bean;

import kevin.api.base.gameBase.bean.GameEnvirnment;

import java.util.ArrayList;

/**
 * Created by kevin on 2015/8/10.
 */
public class Dota2MatchHistory extends GameEnvirnment {
    public final static String DOTA2MATCHHISTORY = "dota2matchhistory";
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNum_results() {
        return num_results;
    }

    public void setNum_results(String num_results) {
        this.num_results = num_results;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getResults_remaining() {
        return results_remaining;
    }

    public void setResults_remaining(String results_remaining) {
        this.results_remaining = results_remaining;
    }

    public ArrayList<Dota2GameOutline> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Dota2GameOutline> matches) {
        this.matches = matches;
    }

    int status ;
    String num_results;
    String total_results ;
    String results_remaining;
    ArrayList<Dota2GameOutline> matches;

    @Override
    public String toString() {
        return "Dota2MatchHistory{" +
                "status='" + status + '\'' +
                ", num_results='" + num_results + '\'' +
                ", total_results='" + total_results + '\'' +
                ", results_remaining='" + results_remaining + '\'' +
                ", matches=" + matches +
                '}';
    }
}
