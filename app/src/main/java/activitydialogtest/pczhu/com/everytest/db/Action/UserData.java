package activitydialogtest.pczhu.com.everytest.db.Action;

import activitydialogtest.pczhu.com.everytest.domain.UserInfo;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/14 下午12:02
 * 版本：V1.0
 * 修改历史：
 */
public interface UserData {

    public void setUserInfo(UserInfo userInfo);
    public UserInfo getUserInfo(String id);
    public UserInfo getUserInfo(String key,String value);
    public String getUserName(String id);
    public String getUserAge(String id);
    public String getHeadPic(String id);
}
