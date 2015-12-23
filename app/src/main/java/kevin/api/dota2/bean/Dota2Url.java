package kevin.api.dota2.bean;

import kevin.utils.Utils;

/**
 * Created by kevin on 2015/7/20.
 */
public class Dota2Url {

    public static String DOTA2MAIN = "https://api.steampowered.com/";
    public static String MATCHHISTORY = "IDOTA2Match_570/GetMatchHistory/v001/";
    public static String MATCHDETIALS = "IDOTA2Match_570/GetMatchDetails/v001/";
    public static String HERO = "IEconDOTA2_570/GetHeroes/v0001/";
    public static String PLAYERSUMMARIES = "ISteamUser/GetPlayerSummaries/v0002/";
    private static String KEY = "?key=07000CBBE0A53DB404881143C118DDF7";

    /**
     * @param userId 用户的32bit Id
     * @return url
     */
    public String getMatchHistory(String userId) {
        return DOTA2MAIN + MATCHHISTORY + KEY + "&account_id=" + userId;
    }

    /**
     * @param userId 用户的32bit Id
     * @param requestCount 要显示的比赛数目
     * @return url
     */
    public String getMatchHistory(String userId , int requestCount) {
        return DOTA2MAIN + MATCHHISTORY + KEY + "&account_id=" + userId + "&matches_requested=" + requestCount ;
    }
    /**
     * @param userId 用户的64bit Id
     * @return
     */
    public String getPlayerSummaries(String userId) {
        return DOTA2MAIN + PLAYERSUMMARIES + KEY + "&steamids=" + Utils.getSteamId(userId);
    }


    /**
     * @param matchId 比赛Id
     * @return
     */
    public String getMatchDetials(String matchId){
        return DOTA2MAIN + MATCHDETIALS + KEY + "&match_id=" + matchId;
    }

    @SuppressWarnings("unused")
    public String getHero(){
        return "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=07000CBBE0A53DB404881143C118DDF7&language=en_us";
    }

    @SuppressWarnings("unused")
    public String getItem(){
        return "https://api.steampowered.com/IEconDOTA2_570/GetGameItems/V001/?key=07000CBBE0A53DB404881143C118DDF7&language=en_us";
    }
}