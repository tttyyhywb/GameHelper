package kevin.api.dota2.jsonResponse;

import kevin.api.base.gameBase.bean.User;

import java.util.ArrayList;

/**
 * Created by Kevin on 2015/8/10.
 */
public class Dota2Players extends User {

    public final static String DOTA2PLAYERS = "dota2players";

    String account_id;
    int player_slot;
    String hero_id;
    String item_0;
    String item_1;
    String item_2;
    String item_3;
    String item_4;
    String item_5;
    int kills;
    int deaths;
    int assists;
    String leaver_status;
    String gold;
    int last_hits;
    int denies;
    String gold_per_min;
    String xp_per_min;
    String gold_spent;
    String hero_damage;
    String tower_damage;
    String hero_healing;
    String level;
    ArrayList<Dota2Skill> ability_upgrades;

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

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public String getItem_0() {
        return item_0;
    }

    public void setItem_0(String item_0) {
        this.item_0 = item_0;
    }

    public String getItem_1() {
        return item_1;
    }

    public void setItem_1(String item_1) {
        this.item_1 = item_1;
    }

    public String getItem_2() {
        return item_2;
    }

    public void setItem_2(String item_2) {
        this.item_2 = item_2;
    }

    public String getItem_3() {
        return item_3;
    }

    public void setItem_3(String item_3) {
        this.item_3 = item_3;
    }

    public String getItem_4() {
        return item_4;
    }

    public void setItem_4(String item_4) {
        this.item_4 = item_4;
    }

    public String getItem_5() {
        return item_5;
    }

    public void setItem_5(String item_5) {
        this.item_5 = item_5;
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

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
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

    public String getGold_per_min() {
        return gold_per_min;
    }

    public void setGold_per_min(String gold_per_min) {
        this.gold_per_min = gold_per_min;
    }

    public String getXp_per_min() {
        return xp_per_min;
    }

    public void setXp_per_min(String xp_per_min) {
        this.xp_per_min = xp_per_min;
    }

    public String getGold_spent() {
        return gold_spent;
    }

    public void setGold_spent(String gold_spent) {
        this.gold_spent = gold_spent;
    }

    public String getHero_damage() {
        return hero_damage;
    }

    public void setHero_damage(String hero_damage) {
        this.hero_damage = hero_damage;
    }

    public String getTower_damage() {
        return tower_damage;
    }

    public void setTower_damage(String tower_damage) {
        this.tower_damage = tower_damage;
    }

    public String getHero_healing() {
        return hero_healing;
    }

    public void setHero_healing(String hero_healing) {
        this.hero_healing = hero_healing;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
