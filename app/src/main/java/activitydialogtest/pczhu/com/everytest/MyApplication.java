package activitydialogtest.pczhu.com.everytest;

import android.app.Application;

import activitydialogtest.pczhu.com.everytest.db.DbSource;
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
        LogUtils.allowString = new String[]{"NinethActivity"};
        DbSource.getInstance(this).createSource("everytest");
    }

}

/**
 * 名称：MainActivity
 * 作用：斐波那契数列的优化
 * 描述：采用缓存优化常用算法会大大增加性能
 * 作者：pczhu
 * 创建时间： 15/12/9 下午1:52
 * 版本：V1.0
 * 修改历史：
 */

/**
 * 名称：SecondActivity
 * 作用：流标签
 * 描述：一个流标签的简单实现 为了学习页面布局
 * 作者：pczhu
 * 创建时间： 15/11/30 下午5:27
 * 版本：V1.0
 * 修改历史：
 */

/**
 * 名称：ThirdActvity
 * 作用：测试柱状图
 * 描述：测试柱状图
 * 作者：pczhu
 * 创建时间： 15/12/8 下午2:05
 * 版本：V1.0
 * 修改历史：
 */



/**
 * 名称：FourActivity
 * 作用：
 * 描述：手势交互监听 一个图片上下滑动 左右滑动的东东
 * 作者：pczhu
 * 创建时间： 15/12/8 下午4:01
 * 版本：V1.0
 * 修改历史：
 */
/**
 * 名称：FifthActivity
 * 作用：
 * 描述：一个图片手势缩放
 * 作者：pczhu
 * 创建时间： 15/12/9 下午1:59
 * 版本：V1.0
 * 修改历史：
 */
/**
 * 名称：SixthActivity
 * 作用：MoveGestureDetector等的测试
 * 描述：第三方手势操作监听
 * 作者：pczhu
 * 创建时间： 15/12/9 下午4:43
 * 版本：V1.0
 * 修改历史：
 */

/**
 * 名称：SevenActivity
 * 作用：Matrix各种方法测试
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/10 上午10:27
 * 版本：V1.0
 * 修改历史：
 */

/**
 * 名称：EightActivity
 * 作用：MyTransXAnimatorListener测试类
 * 描述：测试Matrix结合属性动画做的动画效果
 * 作者：pczhu
 * 创建时间： 15/12/10 上午10:38
 * 版本：V1.0
 * 修改历史：
 */