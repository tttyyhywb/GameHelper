package kevin.api.dota2.bean;

import kevin.api.base.gameBase.bean.Competator;
import kevin.api.base.gameBase.bean.User;

import java.util.ArrayList;

/**
 * Created by Kevin on 2015/8/10.
 */
public class Dota2Players extends Competator {

    public final static String TAG = "dota2players";

    String account_id;
    int player_slot;
    int hero_id;
    int item_0;
    int item_1;
    int item_2;
    int item_3;
    int item_4;
    int item_5;
    int kills;
    int deaths;
    int assists;
    String leaver_status;
    int gold;
    int last_hits;
    int denies;
    int gold_per_min;
    int xp_per_min;
    String gold_spent;
    int hero_damage;
    int tower_damage;
    int hero_healing;
    int level;
    ArrayList<Dota2Skill> ability_upgrades;

    public int getGold_per_min() {
        return gold_per_min;
    }

    public void setGold_per_min(int gold_per_min) {
        this.gold_per_min = gold_per_min;
    }

    public int getXp_per_min() {
        return xp_per_min;
    }

    public void setXp_per_min(int xp_per_min) {
        this.xp_per_min = xp_per_min;
    }

    public int getHero_damage() {
        return hero_damage;
    }

    public void setHero_damage(int hero_damage) {
        this.hero_damage = hero_damage;
    }

    public int getTower_damage() {
        return tower_damage;
    }

    public void setTower_damage(int tower_damage) {
        this.tower_damage = tower_damage;
    }

    public int getHero_healing() {
        return hero_healing;
    }

    public void setHero_healing(int hero_healing) {
        this.hero_healing = hero_healing;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getItem_5() {
        return item_5;
    }

    public void setItem_5(int item_5) {
        this.item_5 = item_5;
    }

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public int getItem_0() {
        return item_0;
    }

    public void setItem_0(int item_0) {
        this.item_0 = item_0;
    }

    public int getItem_1() {
        return item_1;
    }

    public void setItem_1(int item_1) {
        this.item_1 = item_1;
    }

    public int getItem_2() {
        return item_2;
    }

    public void setItem_2(int item_2) {
        this.item_2 = item_2;
    }

    public int getItem_3() {
        return item_3;
    }

    public void setItem_3(int item_3) {
        this.item_3 = item_3;
    }

    public int getItem_4() {
        return item_4;
    }

    public void setItem_4(int item_4) {
        this.item_4 = item_4;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public int getPlayer_slot() {
        return player_slot;
    }

    public void setPlayer_slot(int player_slot) {
        this.player_slot = player_slot;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getLeaver_status() {
        return leaver_status;
    }

    public void setLeaver_status(String leaver_status) {
        this.leaver_status = leaver_status;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLast_hits() {
        return last_hits;
    }

    public void setLast_hits(int last_hits) {
        this.last_hits = last_hits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public String getGold_spent() {
        return gold_spent;
    }

    public void setGold_spent(String gold_spent) {
        this.gold_spent = gold_spent;
    }

    public ArrayList<Dota2Skill> getAbility_upgrades() {
        return ability_upgrades;
    }

    public void setAbility_upgrades(ArrayList<Dota2Skill> ability_upgrades) {
        this.ability_upgrades = ability_upgrades;
    }

    @Override
    public String toString() {
        return "Dota2Players{" +
                "account_id='" + account_id + '\'' +
                ", player_slot='" + player_slot + '\'' +
                ", hero_id='" + hero_id + '\'' +
                ", item_0='" + item_0 + '\'' +
                ", item_1='" + item_1 + '\'' +
                ", item_2='" + item_2 + '\'' +
                ", item_3='" + item_3 + '\'' +
                ", item_4='" + item_4 + '\'' +
                ", item_5='" + item_5 + '\'' +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", leaver_status='" + leaver_status + '\'' +
                ", gold='" + gold + '\'' +
                ", last_hits=" + last_hits +
                ", denies=" + denies +
                ", gold_per_min='" + gold_per_min + '\'' +
                ", xp_per_min='" + xp_per_min + '\'' +
                ", gold_spent='" + gold_spent + '\'' +
                ", hero_damage='" + hero_damage + '\'' +
                ", tower_damage='" + tower_damage + '\'' +
                ", hero_healing='" + hero_healing + '\'' +
                ", level='" + level + '\'' +
                ", ability_upgrades=" + ability_upgrades +
                '}';
    }

}
