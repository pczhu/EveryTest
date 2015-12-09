package activitydialogtest.pczhu.com.everytest.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：简单的本地图片加载工具类
 * 作者：pczhu
 * 创建时间： 15/12/9 上午10:07
 * 版本：V1.0
 * 修改历史：
 */
public class ImageUtils {
    public  static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new  BitmapFactory.Options();
        opt.inPreferredConfig =  Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //  获取资源图片
        InputStream is =  context.getResources().openRawResource(resId);
        return  BitmapFactory.decodeStream(is, null, opt);
    }
}
