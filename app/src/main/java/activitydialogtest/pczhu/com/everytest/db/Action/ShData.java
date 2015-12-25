package activitydialogtest.pczhu.com.everytest.db.Action;

import android.content.SharedPreferences;

/**
 * 名称：
 * 作用：SharedPreferences 存取接口
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/24 上午11:10
 * 版本：V1.0
 * 修改历史：
 */
public interface ShData {

    public SharedPreferences getShare(String name);
    /**
     * 通过SharePreference 读取
     * @param shKey
     * @return
     */
    public boolean getBoolean(String shKey);

    /**
     * 通过SharePreference 存入
     * @param shKey
     * @return
     */
    public boolean setBoolean(String shKey,boolean value);

    /**
     * String类型
     * @param shKey
     * @return
     */
    public String getString(String shKey);

    public void setString(String shkey,String value);

    public int getInt(String shKey);

    public void setInt(String shKey,int value);
}
