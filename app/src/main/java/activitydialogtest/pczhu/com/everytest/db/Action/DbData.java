package activitydialogtest.pczhu.com.everytest.db.Action;

import java.util.List;

import activitydialogtest.pczhu.com.everytest.domain.UserInfo;

/**
 * 名称：DbData
 * 作用：数据库接口
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/14 上午10:02
 * 版本：V1.0
 * 修改历史：
 */
public interface DbData {


    /**
     * 存入对象
     * @param object 目标对象
     */
    public void setObject(Object object);

    /**
     * 通过关键字取出数据
     * @param clz 表
     * @param key 关键词
     * @return
     */
    public String getDataByKeyWord(Class clz,String key);

    /**
     * 通过类名取出数据
     * @param clz 类名
     * @return
     */
    public List<Object> getDataByObjectName(Class clz);

    /**
     * 通过类名和限制条件取出数据
     * @param clz 类名
     * @param name 限制条件名称
     * @param sign 限制条件符号 > = <
     * @param target 限制条件的目标
     * @return
     */
    public List<Object> getDataByObjectNameAndKey(Class clz,String name,String sign,String target);



}
