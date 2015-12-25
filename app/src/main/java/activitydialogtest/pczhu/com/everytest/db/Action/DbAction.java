package activitydialogtest.pczhu.com.everytest.db.Action;

import android.content.Context;
import android.os.Environment;


import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.ex.DbException;

import org.xutils.DbManager;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import activitydialogtest.pczhu.com.everytest.domain.UserInfo;
import activitydialogtest.pczhu.com.everytest.domain.WelcomeData;
import activitydialogtest.pczhu.com.everytest.domain.WelcomeInfo;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;
import activitydialogtest.pczhu.com.everytest.utils.MyLong;

import org.xutils.x;
/**
 * 名称：DbAction
 * 作用：数据库实现
 * 描述：数据库接口的具体实现
 * 作者：pczhu
 * 创建时间： 15/12/14 上午10:00
 * 版本：V1.0
 * 修改历史：
 */
public class DbAction implements DbData,UserData,WelcomeInfoData {
    private static Context mContext;
    private static DbAction dbAction = null;
    private static DbManager db;
    private static DbManager.DaoConfig daoConfig;
    public static DbAction getInstance(Context context){
        DbAction.mContext = context;
        if( dbAction == null){
            dbAction = new DbAction();
            daoConfig = new DbManager.DaoConfig()
                    .setDbName("mydb.db")
                    .setDbDir(new File(Environment.getExternalStorageState()+"/test"))
                    .setDbVersion(1)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // TODO: ...
                            // db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                        }
                    });
        }
        if(db == null){
            db =  x.getDb(daoConfig);
        }

        return dbAction;
    }
    private DbAction(){

    }

    @Override
    public <T> void setObject(T object) {

        try {
            db.save(object);
        } catch (org.xutils.ex.DbException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String getDataByKeyWord(Class clz,String key) {
        String result = null;

        DbModel first = null;
        try {
            first = db.selector(clz).select(key).findFirst();
            result = first.getString(key);
        } catch (DbException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public List<Object> getDataByObjectName(Class clz) {
        List all = null;
        try {
            all = db.findAll(clz);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public List<Object> getDataByObjectNameAndKey(Class clz, String name, String sign, String target) {
        List<Object> all = null;
        try {
            all = db.selector(clz).where(name,sign,target).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public boolean setColumn(Class clz, String name, String sign, String target,String... value) {
        try {
            db.update(clz, WhereBuilder.b(name,sign,target),value);

        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    @Override
    public void setUserInfo(UserInfo userInfo) {
        setObject(userInfo);
    }

    @Override
    public UserInfo getUserInfo(String id) {
        List<Object> result = getDataByObjectNameAndKey(UserInfo.class, "id", "=", id);
        if(result != null && result.size() == 1){
            return (UserInfo) result.get(0);
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(String key, String value) {
        List<Object> result = getDataByObjectNameAndKey(UserInfo.class, "key", "=", value);
        if(result != null && result.size() == 1){
            return (UserInfo) result.get(0);
        }
        return null;
    }

    @Override
    public String getUserName(String id) {
        UserInfo userinfo = getUserInfo(id);
        return (userinfo != null?userinfo.getName():null);
    }

    @Override
    public String getUserAge(String id) {
        UserInfo userinfo = getUserInfo(id);
        return (userinfo != null?userinfo.getAge():null);
    }

    @Override
    public String getHeadPic(String id) {
        UserInfo userinfo = getUserInfo(id);
        return (userinfo != null?userinfo.getHeadpic():null);
    }


    @Override
    public void setWelcomeInfo(WelcomeInfo welcomeInfo,boolean drop) {
        if(drop) {
            try {
                db.dropTable(WelcomeInfo.class);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        setObject(welcomeInfo);
    }

    @Override
    public WelcomeInfo getWelcomeInfo() {
        List<WelcomeInfo> all = null;
        try {
            all =  db.selector(WelcomeInfo.class).orderBy("adversion",true).limit(2).findAll();

        } catch (DbException e) {
            e.printStackTrace();
        }
        if(all == null){
            return null;
        }
        for(WelcomeInfo welcomeInfo : all){
            LogUtils.i(welcomeInfo.toString());
        }
        for (WelcomeInfo welcomeInfo : all){

            Calendar calendar = Calendar.getInstance();
            if(MyLong.parseString(welcomeInfo.getBeginData())<calendar.getTimeInMillis()
                && MyLong.parseString(welcomeInfo.getEndData()) > calendar.getTimeInMillis()){
                return welcomeInfo;
            }
        }
        return null;
    }
}
