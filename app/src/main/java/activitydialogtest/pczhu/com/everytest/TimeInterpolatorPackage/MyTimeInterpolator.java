package activitydialogtest.pczhu.com.everytest.TimeInterpolatorPackage;

import android.animation.TimeInterpolator;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/10 下午4:29
 * 版本：V1.0
 * 修改历史：
 */
public class MyTimeInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }
}
