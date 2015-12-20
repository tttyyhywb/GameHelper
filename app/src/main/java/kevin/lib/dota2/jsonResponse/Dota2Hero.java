package kevin.lib.dota2.jsonResponse;


import kevin.lib.base.gameBase.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2015/7/20.
 */
public class Dota2Hero extends Hero {

    public final static String DOTA2HERO = "dota2hero";
    ArrayList<Hero> heroes;
    int count;
    String status;

    @Override
    public String toString() {
        return "Dota2Hero{" +
                "heroes=" + heroes +
                ", count=" + count +
                ", status='" + status + '\'' +
                '}';
    }

    public Map<String,Hero> getHeroes() {
        Map<String, Hero> map = new HashMap<String, Hero>();
        for (Hero hero : heroes) {
            if (map.get(hero.getId()) == null) {
                map.put(hero.getId(), hero);
            }
        }
        return map;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
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

    public class Hero{
        String name;
        String id;
        String localized_name;

        @Override
        public String toString() {
            return "Hero{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", localized_name='" + localized_name + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocalized_name() {
            return localized_name;
        }

        public void setLocalized_name(String localized_name) {
            this.localized_name = localized_name;
        }
    }
}
