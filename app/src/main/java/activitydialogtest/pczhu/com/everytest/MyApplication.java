package activitydialogtest.pczhu.com.everytest;

import android.app.Application;

import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/7 下午12:24
 * 版本：V1.0
 * 修改历史：
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.allowAll = true;
        LogUtils.allowD = false;
        LogUtils.allowW = false;
        LogUtils.allowI = false;
        LogUtils.allowV = false;
        LogUtils.allowV = false;
        LogUtils.allowString = new String[]{"onCreate","onResume"};
    }

}
