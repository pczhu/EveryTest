package activitydialogtest.pczhu.com.everytest.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

import java.io.InputStream;

import activitydialogtest.pczhu.com.everytest.R;

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
    public static ImageOptions getImageOptions() {
        return new ImageOptions.Builder()
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(2))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                        //.setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(R.drawable.a)
                .setFailureDrawableId(R.drawable.b)
                .build();
    }
}
