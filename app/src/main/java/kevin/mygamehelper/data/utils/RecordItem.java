package kevin.mygamehelper.data.utils;

import com.kevin.gamehelper.mygamehelper.R;

import kevin.api.dota2.bean.Dota2MatchDetails;
import kevin.api.dota2.bean.Dota2Players;
import kevin.api.dota2.bean.Dota2User;
import kevin.utils.Utils;

/**
 * Created by Kevin on 2016/2/23.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class RecordItem {

    Dota2MatchDetails detial;
    Dota2User account;

    private int heroId;
    private float record;
    private String name;
    private boolean ifWin;


    //最高击杀
    public static String RECORD_KILLS = Utils.getResource().getString(R.string.record_kills);

    //最高死亡
    public static String RECORD_DEATHS = Utils.getResource().getString(R.string.record_deaths);

    //最高助攻
    public static String RECORD_ASSISTS = Utils.getResource().getString(R.string.record_assists);

    //最高反补
    public static String RECORD_DENIES = Utils.getResource().getString(R.string.record_denies);

    //最高每分钟经验
    public static String RECORD_XP_PER_MIN = Utils.getResource().getString(R.string.record_ex_per_min);

    //最高每分钟金钱
    public static String RECORD_GOLD_PER_MIN = Utils.getResource().getString(R.string.record_gold_per_min);

    //最高英雄伤害
    public static String RECORD_HERO_DAMAGE = Utils.getResource().getString(R.string.record_hero_damage);

    //最高建筑伤害
    public static String RECORD_TOWER_DAMAGE = Utils.getResource().getString(R.string.record_tower_damage);

    //最高英雄治疗
    public static String RECORD_HERO_HEALING = Utils.getResource().getString(R.string.record_hero_healing);

    //最高正补
    public static String RECORD_LAST_HITS = Utils.getResource().getString(R.string.record_last_hits);

    public RecordItem(Dota2User account, Dota2MatchDetails detial,String name,float max) {
        this.account = account;
        this.detial = detial;
        this.name = name;
        handleData(max);
    }

    private void handleData(float max){
        Dota2Players player = detial.getPlayer(account);
        this.heroId = player.getHero_id();
        this.name = switchRecordName(name);
        this.record = max;
        if ((player.getPlayer_slot() > 100 && detial.getRadiant_win()) || (player.getPlayer_slot() < 100 && !detial.getRadiant_win())) {
            this.ifWin = false;
        } else {
           this.ifWin = true;
        }
    }

    private String switchRecordName(String name) {
        switch (name){
            case "kills":
                return RECORD_KILLS;
            case "deaths":
                return RECORD_DEATHS;
            case "assists":
                return RECORD_ASSISTS;
            case "last_hits":
                return RECORD_LAST_HITS;
            case "denies":
                return RECORD_DENIES ;
            case "gold_per_min":
                return RECORD_GOLD_PER_MIN;
            case "xp_per_min":
                return RECORD_XP_PER_MIN;
            case "hero_damage":
                return RECORD_HERO_DAMAGE;
            case "tower_damage":
                return RECORD_TOWER_DAMAGE;
            case  "hero_healing":
                return RECORD_HERO_HEALING;
            default:
                return null;
        }
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public boolean isIfWin() {
        return ifWin;
    }

    public void setIfWin(boolean ifWin) {
        this.ifWin = ifWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRecord() {
        return record;
    }

    public void setRecord(float record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "RecordItem{" +
                "heroId=" + heroId +
                ", record=" + record +
                ", name='" + name + '\'' +
                ", ifWin=" + ifWin +
                '}';
    }
}
