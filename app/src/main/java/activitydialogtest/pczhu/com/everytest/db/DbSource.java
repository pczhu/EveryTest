package activitydialogtest.pczhu.com.everytest.db;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/14 上午9:57
 * 版本：V1.0
 * 修改历史：
 */
public class DbSource {
    private static Context mContext;
    private static DbSource dbSource= null;
    private static DbUtils.DaoConfig daoConfig;
    public static DbSource getInstance(Context context){
        DbSource.mContext = context;
        if(dbSource == null){
            dbSource = new DbSource();
        }
        if(daoConfig == null){
            daoConfig = new DbUtils.DaoConfig(mContext);
        }
        return dbSource;
    }
    public void createSource(String name){
        daoConfig.setDbName(""+name);
    }

}
