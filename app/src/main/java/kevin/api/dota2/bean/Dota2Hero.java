package kevin.api.dota2.bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import kevin.api.base.gameBase.bean.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2015/7/20.
 */
@DatabaseTable(tableName = "dota2_hero")
public class Dota2Hero extends Hero {

    public final static String TAG = "dota2hero";

    ArrayList<Dota2Hero> heroes;

    int count;

    String status;

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(columnName = "name")
    String name;

    @DatabaseField(columnName = "localized_name")
    String localized_name;

    public void setHeroes(ArrayList<Dota2Hero> heroes) {
        this.heroes = heroes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", localized_name='" + localized_name + '\'' +
                '}';
    }

    public ArrayList<Dota2Hero> get(){
        return this.heroes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalized_name() {
        return localized_name;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }

    public Map<String, Dota2Hero> getHeroes() {
        Map<String, Dota2Hero> map = new HashMap<String, Dota2Hero>();
        for (Dota2Hero hero : heroes) {
            if (map.get(hero.getId()) == null) {
                map.put(hero.getId()+"", hero);
            }
        }
        return map;
    }
}
