package kevin.lib.dota2;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kevin.lib.dota2.jsonResponse.Dota2Equipment;
import kevin.lib.dota2.jsonResponse.Dota2Hero;
import kevin.lib.base.gameBase.json.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Kevin on 2015/7/21.
 */
public class Utils {

    static Context context;
    AssetManager assetManager;

    Dota2Hero dota2Hero = new Dota2Hero();
    Result<Dota2Hero> resultHero = new Result<Dota2Hero>(dota2Hero);
    static Map<String, Dota2Hero.Hero> heroes = null;

    Dota2Equipment dota2Equipment = new Dota2Equipment();
    Result<Dota2Equipment> resultItem = new Result<Dota2Equipment>(dota2Equipment);
    static Map<String, Dota2Equipment.Item> items = null;

    public Utils(Context context) {
        this.context = context;
        assetManager = context.getResources().getAssets();
        Gson gson = new Gson();
        InputStream is = null;
        if (heroes == null) {
            try {
                is = assetManager.open("Response/GetHeroes");
                resultHero = gson.fromJson(inputSteam2String(is).toString(), new TypeToken<Result<Dota2Hero>>() {
                }.getType());
                heroes = resultHero.getResult().getHeroes();
                //Log.e("hero", heroes.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (items == null) {
            try {
                is = assetManager.open("Response/GetItems");
                resultItem = gson.fromJson(inputSteam2String(is).toString(), new TypeToken<Result<Dota2Equipment>>() {
                }.getType());
                items = resultItem.getResult().getItems();
                //Log.e("item", items.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public static boolean allNumber(String accountId) {
        return accountId.matches("[0-9]+");
    }

    public static String getItemUrl(String itemId) {
       // Log.e("item0", "items/" + items.get(itemId).getName() + "_lg.png");
        return "items/" + items.get(itemId).getName() + "_lg.png";
    }

    public static String getHeroPicHphover(String heroId) {
        Log.e("heroId", heroId);
        //Log.e("    ",heroes.toString());
        String name = heroes.get(heroId).getName();
        return "heroes/" + name + "_hphover.png";
    }
    public static String getHeroPicHphover(String string, boolean hasAssets) {
        if (hasAssets == true) {
            return "assets/" + getHeroPicHphover(string);
        } else {
            return getHeroPicHphover(string);
        }
    }
    public static String getHeroPicVert(String heroId) {
        return "heroes/" + heroes.get(heroId).getName() + "_vert.jpg";
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



    public static String getItemUrl(String itemId, boolean hasAssets) {
        if (hasAssets == true) {
            return "assets/" + getItemUrl(itemId);
        } else {
            return getItemUrl(itemId);
        }
    }
}
