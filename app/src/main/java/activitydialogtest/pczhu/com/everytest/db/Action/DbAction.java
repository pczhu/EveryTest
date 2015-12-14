package activitydialogtest.pczhu.com.everytest.db.Action;

import android.content.Context;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import java.util.List;
import activitydialogtest.pczhu.com.everytest.domain.UserInfo;

/**
 * 名称：DbAction
 * 作用：数据库实现
 * 描述：数据库接口的具体实现
 * 作者：pczhu
 * 创建时间： 15/12/14 上午10:00
 * 版本：V1.0
 * 修改历史：
 */
public class DbAction implements DbData,UserData{
    private static Context mContext;
    private static DbAction dbAction = null;
    private static DbUtils db;
    public static DbAction getInstance(Context context){
        DbAction.mContext = context;
        if( dbAction == null){
            dbAction = new DbAction();

        }
        if(db == null){
            db = DbUtils.create(mContext);
        }
        return dbAction;
    }
    private DbAction(){

    }

    @Override
    public void setObject(Object object) {
        try {
            db.save(object);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getDataByKeyWord(Class clz,String key) {
        String result = null;
        try {
            DbModel dbModelFirst = db.findDbModelFirst(Selector.from(clz).select(key));
            result = dbModelFirst.getString(key);
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
             all = db.findAll(Selector.from(clz).where(name, sign, target));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return all;
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

}
