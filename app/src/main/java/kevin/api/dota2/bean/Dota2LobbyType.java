package kevin.api.dota2.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import kevin.api.base.gameBase.bean.BaseResult;
import kevin.api.base.network.BaseRequest;

/**
 * Created by Kevin on 2016/3/28.
 * DESCRIPTION:游戏模式
 * email:493243390@qq.com
 */

@DatabaseTable( tableName = "data2_lobby_type")
public class Dota2LobbyType extends BaseResult {

    @DatabaseField(id = true)
    int id;

    @DatabaseField(columnName = "")
    String name;

    ArrayList<Dota2LobbyType> lobbies;

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

    public ArrayList<Dota2LobbyType> getLobbies() {
        return lobbies;
    }

    public void setLobbies(ArrayList<Dota2LobbyType> lobbies) {
        this.lobbies = lobbies;
    }

    @Override
    public String toString() {
        return "Dota2LobbyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lobbies=" + lobbies +
                '}';
    }
}
