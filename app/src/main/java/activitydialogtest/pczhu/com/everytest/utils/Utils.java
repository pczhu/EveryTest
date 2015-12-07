package activitydialogtest.pczhu.com.everytest.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/2 下午2:19
 * 版本：V1.0
 * 修改历史：
 */
public class Utils {
    public static int[] getScreenMaxWidth(Context context){
        //DisplayMetrics metrics = new DisplayMetrics();
        //getW

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width,height};
    }
}
