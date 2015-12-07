package activitydialogtest.pczhu.com.everytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/11/30 下午1:55
 * 版本：V1.0
 * 修改历史：
 */
public class MyCustomView extends TextView {
    public static final String TAG = "MYCustomView";
    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int heigh = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("MeasureSpec",""+MeasureSpec.AT_MOST);
        Log.i("MeasureSpec",""+MeasureSpec.EXACTLY);
        Log.i("MeasureSpec",""+MeasureSpec.UNSPECIFIED);
        Log.i("w_mode",""+w_mode);
        Log.i("width",""+width);
        Log.i("h_mode",""+h_mode);
        Log.i("heigh",""+heigh);
        Log.i("defaultwidth",""+getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec));
        Log.i("defaultheigh",""+getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
        setMeasuredDimension(
                getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout "+changed+":"+left+":"+top+":"+right+":"+bottom);

        super.onLayout(changed, left, top, right, bottom);
    }

}
