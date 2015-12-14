package activitydialogtest.pczhu.com.everytest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import activitydialogtest.pczhu.com.everytest.db.Action.DbAction;
import activitydialogtest.pczhu.com.everytest.db.Action.DbData;
import activitydialogtest.pczhu.com.everytest.db.Action.UserData;
import activitydialogtest.pczhu.com.everytest.domain.UserInfo;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/14 下午12:27
 * 版本：V1.0
 * 修改历史：
 */
public class NinethActivity extends AppCompatActivity{
    private UserData db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DbAction.getInstance(this);
        testDb();
        getDb();
    }

    private void getDb() {
        UserInfo info = db.getUserInfo("123");

        LogUtils.i(info!=null?info.toString():"nothing");
        LogUtils.d("age" + db.getUserName("123"));
        LogUtils.d("name"+db.getUserAge("123"));
        LogUtils.d("headpic"+db.getHeadPic("456"));
    }

    private void testDb() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("456");
        userInfo.setName("userinfotwo");
        userInfo.setAge("135");
        userInfo.setHeadpic("http://www.sina.com");
        db.setUserInfo(userInfo);
    }

}
