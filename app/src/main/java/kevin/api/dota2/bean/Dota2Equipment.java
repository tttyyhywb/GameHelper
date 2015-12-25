package kevin.api.dota2.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import kevin.api.base.gameBase.bean.Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2015/7/20.
 */
@DatabaseTable(tableName = "dota2_item")
public class Dota2Equipment extends Equipment {

    public final String DOTA2EQUIPMENT = "dota2equipment";

    ArrayList<Dota2Equipment> items;

    @DatabaseField(id = true)
    int id;

    String status;

    @DatabaseField(columnName = "name")
    String name;

    @DatabaseField(columnName = "secret_shop")
    String secret_shop;

    @DatabaseField(columnName = "side_shop")
    String side_shop;

    @DatabaseField(columnName = "recipe")
    String recipe;

    @DatabaseField(columnName = "cost")
    String cost;

    @DatabaseField(columnName = "localized_name")
    String localized_name;
    @Override
    public String toString() {
        return "Dota2Equipment{" +
                "items=" + items +
                ", status='" + status + '\'' +
                '}';
    }

    public Map<String, Dota2Equipment> getItems() {

        Map<String, Dota2Equipment> map = new HashMap<String, Dota2Equipment>();
        for (Dota2Equipment item : items) {
            if (map.get(item.getId()) == null) {
                map.put(item.getId()+"", item);
            }
        }
        return map;
    }

    public ArrayList<Dota2Equipment> get(){
        return this.items;
    }

    public void setItems(ArrayList<Dota2Equipment> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


}
