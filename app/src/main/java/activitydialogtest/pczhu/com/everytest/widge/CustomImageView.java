package activitydialogtest.pczhu.com.everytest.widge;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/9 下午2:04
 * 版本：V1.0
 * 修改历史：
 */
public class CustomImageView extends ImageView{
    private int MIN_WIDTH;
    private int MIN_HEIGHT;
    private int MAX_WIDTH;
    private int MAX_HEIGHT;
    private int[] tblr;
    MODE mode;
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        tblr = new int[]{top,bottom,left,right};
    }
    private enum MODE {
        NONE, DRAG, ZOOM
    };
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        int target_width = bm.getWidth();
        int target_height = bm.getHeight();
        //最小缩放为3倍
        MIN_WIDTH = target_width / 3;
        MIN_HEIGHT = target_height / 3;
        //最大放大为两倍
        MAX_WIDTH= target_width * 2;
        MAX_HEIGHT = target_height * 2;


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                singleDown(event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pointDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                moveAction(event);
                break;
            case MotionEvent.ACTION_UP:
                mode = MODE.NONE;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //pointUp(event);
                break;
        }
        return true;
    }

    private void singleDown(MotionEvent event) {

    }
    private void pointDown(MotionEvent event){

    }
    private void moveAction(MotionEvent event){

    }
}
