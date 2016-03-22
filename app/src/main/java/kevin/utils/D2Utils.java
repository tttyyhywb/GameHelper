package kevin.utils;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;

import kevin.api.dota2.bean.Dota2Equipment;
import kevin.api.dota2.bean.Dota2Hero;
import kevin.api.base.network.ApiResult;
import kevin.database.DataBase.DBHelperDota2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.sql.SQLException;

/**
 * Created by Kevin on 2015/7/21.
 */
public class D2Utils {

    public static final String PREFIX_ASSETS = "assets://";

    AssetManager assetManager;

    Dota2Hero dota2Hero = new Dota2Hero();

    ApiResult<Dota2Hero> resultHero = new ApiResult<Dota2Hero>(dota2Hero);

    Dota2Equipment dota2Equipment = new Dota2Equipment();

    ApiResult<Dota2Equipment> resultItem = new ApiResult<Dota2Equipment>(dota2Equipment);

    private static D2Utils instantce;

    private static DBHelperDota2 dbHelper;

    private static Dao<Dota2Equipment,Integer> itemDao ;

    private static Dao<Dota2Hero,Integer> heroDao ;

    static Utils utils = Utils.getInstance();

    public static D2Utils getInstantce(){
        return instantce;
    }

    public static void init(){
        if(instantce == null){
            synchronized (D2Utils.class){
                if(instantce == null){
                    instantce = new D2Utils();
                }
            }
        }
    }

    private D2Utils() {
        assetManager = Utils.getInstance().getAssetManager();
        Gson gson = new Gson();
        InputStream is = null;
        dbHelper = DBHelperDota2.getInstance();

        try {
            itemDao = dbHelper.getDao(Dota2Equipment.class);
            heroDao = dbHelper.getDao(Dota2Hero.class);

            is = assetManager.open("Response/GetHeroes");
            resultHero = gson.fromJson(inputSteam2String(is).toString(), new TypeToken<ApiResult<Dota2Hero>>() {
            }.getType());

            for (Dota2Hero h : resultHero.getResult().get()) {
                dbHelper.getDao(Dota2Hero.class).create(h);
            }

            is = assetManager.open("Response/GetItems");
            resultItem = gson.fromJson(inputSteam2String(is).toString(), new TypeToken<ApiResult<Dota2Equipment>>() {
            }.getType());

            for (Dota2Equipment item : resultItem.getResult().get()) {
                dbHelper.getDao(Dota2Equipment.class).create(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param is 待转换的inpusteam
     */
    @NonNull
    public String inputSteam2String(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * convert 32bit to 64bit steamId
     *
     * @param accountId 用户的32bit数字账号
     * @return 用户的64bit数字账号
     */
    public static String getSteamId(String accountId) {
        //判断是不是只有0-9（使用正则表达式）
        BigInteger steamId = new BigInteger(accountId);
        BigInteger _64BitId = new BigInteger("76561197960265728");
        steamId = steamId.add(_64BitId);
        return steamId.toString();
    }

    /**
     * convert 64bit 2 32bit steamId
     *
     * @param steamId 64bit Id
     * @return 32bit Id
     */
    public static String getAccountId(String steamId) {
        //判断是不是只有0-9（使用正则表达式）
        BigInteger account = new BigInteger(steamId);
        BigInteger _64BitId = new BigInteger("76561197960265728");
        account = account.subtract(_64BitId);
        return account.toString();
    }

    public static String getAccountId(long steamId) {

        return getAccountId(utils.int2string(steamId));
    }

    public static boolean allNumber(String accountId) {
        return accountId.matches("[0-9]+");
    }

    public static String getItemUrl(int itemId) {
        try {
            return "items/" + itemDao.queryForId(itemId).getName() + "_lg.png";
        } catch (SQLException e) {
            Log.e("getItemUrl","no such item");
            return null;
        }
    }

    public static String getHeroPicHphover(int heroId) {
        try {
            //Log.e("hero",heroId + heroDao.queryForId(heroId).getName() );
            return "heroes/" +  heroDao.queryForId(heroId).getName() + "_hphover.png";
        } catch (SQLException e) {
            Log.e("getHeroPicHphover","no such hero picture");
            return null;
        }
    }

    public static String getHeroPicHphover(int heroId, boolean hasAssets) {
        if (hasAssets == true) {
            return PREFIX_ASSETS + getHeroPicHphover(heroId);
        } else {
            return getHeroPicHphover(heroId);
        }
    }

    public static String getHeroPicVert(int heroId) {
        try {
            return "heroes/" +  heroDao.queryForId(heroId).getName() + "_vert.jpg";
        } catch (SQLException e) {
            Log.e("getHeroPicVert","no such heroId");
            return null;
        }
    }

    /**
     * 解决ScrollView与ListView共存导致ListView显示不全的问题,适用于Item高度固定
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String getItemUrl(int itemId, boolean hasAssets) {
        if (hasAssets == true) {
            return PREFIX_ASSETS + getItemUrl(itemId);
        } else {
            return getItemUrl(itemId);
        }
    }
}
