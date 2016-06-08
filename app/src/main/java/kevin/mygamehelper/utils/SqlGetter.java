package kevin.mygamehelper.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import kevin.api.base.Status;
import kevin.api.base.network.ApiResult;
import kevin.utils.Account;
import kevin.database.DataBase.DBHelperDota2;

/**
 * Created by Kevin on 2016/3/22.
 * DESCRIPTION:
 * email:493243390@qq.com
 */
public class SqlGetter implements AccountGetter {

    Gson gson;
    ApiResult<Account> result = new ApiResult<>();

    @Override
    public String getAccount(String resultString) {
        gson = new Gson();
        ApiResult<Account> apiResult;
        apiResult = gson.fromJson(resultString, new TypeToken<ApiResult<Account>>() {
        }.getType());
        switch (apiResult.getStatus()) {
            case Status.SUCCESS: {
                return signUp(apiResult.getResult());
            }
            default: {
                result.setStatus(Status.FAIL);
                return gson.toJson(result);
            }
        }
    }

    private String signUp(Account account) {
        try {
            Dao<Account, String> accountDao = DBHelperDota2.getInstance().getDao(Account.class);
            Account user = accountDao.queryForId(account.getUsername());
            if(user == null){
                accountDao.create(account);
                result.setStatus(Status.SUCCESS);
                result.setResult(account);
            }else{
                result.setStatus(Status.FAIL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gson.toJson(result);
    }
}
