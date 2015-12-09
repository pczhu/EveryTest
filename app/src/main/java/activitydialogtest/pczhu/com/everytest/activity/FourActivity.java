package activitydialogtest.pczhu.com.everytest.activity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.utils.ImageUtils;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：手势交互监听
 * 作者：pczhu
 * 创建时间： 15/12/8 下午4:01
 * 版本：V1.0
 * 修改历史：
 */
public class FourActivity extends AppCompatActivity implements View.OnTouchListener {
    GestureDetector gestureDetector;

    ViewFlipper viewFlipper;
    int[] imgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        imgs = new int[]{R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
        R.drawable.h};

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        gestureDetector = new GestureDetector(new Listener());
        viewFlipper.setOnTouchListener(this);
        for (int i = 0; i < imgs.length; i++) {  // 添加图片源
            ImageView iv = new ImageView(this);
            //iv.setImageResource(imgs[i]);
            //iv.setImageDrawable(imgs[i]);
            iv.setImageBitmap(ImageUtils.readBitMap(getApplicationContext(),imgs[i]));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(iv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class Listener implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{

        @Override
        public boolean onDown(MotionEvent e) {
            LogUtils.i("onDown起作用了");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            LogUtils.i("onShowPress起作用了");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            LogUtils.i("onSingleTapUp起作用了");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            LogUtils.i("onScroll起作用了"+distanceX+":"+distanceY);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            LogUtils.i("onLongPress起作用了");

        }
        private static final float VX= 100.0f;
        private static final float VY= 150.0f;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            LogUtils.i("onFling起作用了"+velocityX+":"+velocityY);
            float x = e1.getX()-e2.getX();
            float y = e1.getY()-e2.getY();
            if(Math.abs(x) > Math.abs(y)){//左右
                if(x > 0 && Math.abs(velocityX) > VX){//向左
                    LogUtils.i("向左Fling");
                    Animation left_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_left_out);
                    Animation right_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_right_in);
                    viewFlipper.setOutAnimation(left_out);
                    viewFlipper.setInAnimation(right_in);
                    viewFlipper.showNext();

                }else if( Math.abs(velocityX) > VX){
                    LogUtils.i("向右Fling");
                    Animation left_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_left_in);
                    Animation right_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_right_out);
                    viewFlipper.setOutAnimation(right_out);
                    viewFlipper.setInAnimation(left_in);
                    viewFlipper.showPrevious();

                }
            }else{//上下
                if(y > 0 && Math.abs(velocityY) > VY){//向上
                    LogUtils.i("向上Fling");
                    Animation top_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_top_out);
                    Animation bottom_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_bottom_in);
                    viewFlipper.setOutAnimation(top_out);
                    viewFlipper.setInAnimation(bottom_in);
                    viewFlipper.showNext();
                }else if( Math.abs(velocityY) > VY){
                    LogUtils.i("向下Fling");
                    Animation top_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_top_in);
                    Animation bottom_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.four_bottom_out);
                    viewFlipper.setOutAnimation(bottom_out);
                    viewFlipper.setInAnimation(top_in);
                    viewFlipper.showPrevious();
                }
            }
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            LogUtils.i("onSingleTapConfirmed起作用了");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            LogUtils.i("onDoubleTap起作用了");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            LogUtils.i("onDoubleTapEvent起作用了");
            return true;
        }
    }

}
