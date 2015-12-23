package kevin.api.dota2.bean;


import kevin.api.base.gameBase.bean.Skill;

/**
 * Created by Kevin on 2015/7/20.
 */
public class Dota2Skill extends Skill {

    String ability;

    String time;

    String level;

    public final static String TAG = "dota2skill";

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
