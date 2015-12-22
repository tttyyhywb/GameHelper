package kevin.api.dota2.jsonResponse;

import kevin.api.base.gameBase.bean.Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2015/7/20.
 */
public class Dota2Equipment extends Equipment {

    public final String DOTA2EQUIPMENT = "dota2equipment";

    ArrayList<Item> items;
    String status;

    @Override
    public String toString() {
        return "Dota2Equipment{" +
                "items=" + items +
                ", status='" + status + '\'' +
                '}';
    }

    public Map<String, Item> getItems() {

        Map<String, Item> map = new HashMap<String, Item>();
        for (Item item : items) {
            if (map.get(item.getId()) == null) {
                map.put(item.getId(), item);
            }
        }
        return map;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Item {
        String id;
        String name;
        String secret_shop;
        String side_shop;
        String recipe;
        String cost;
        String localized_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSecret_shop() {
            return secret_shop;
        }

        public void setSecret_shop(String secret_shop) {
            this.secret_shop = secret_shop;
        }

        public String getSide_shop() {
            return side_shop;
        }

        public void setSide_shop(String side_shop) {
            this.side_shop = side_shop;
        }

        public String getRecipe() {
            return recipe;
        }

        public void setRecipe(String recipe) {
            this.recipe = recipe;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getLocalized_name() {
            return localized_name;
        }

        public void setLocalized_name(String localized_name) {
            this.localized_name = localized_name;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", secret_shop='" + secret_shop + '\'' +
                    ", side_shop='" + side_shop + '\'' +
                    ", recipe='" + recipe + '\'' +
                    ", cost='" + cost + '\'' +
                    ", localized_name='" + localized_name + '\'' +
                    '}';
        }
    }
}
