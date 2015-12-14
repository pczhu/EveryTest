package activitydialogtest.pczhu.com.everytest.activity;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.animotion.MyTransXAnimatorListener;
import activitydialogtest.pczhu.com.everytest.utils.ImageUtils;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;
import activitydialogtest.pczhu.com.everytest.utils.Utils;

/**
 * 名称：EightActivity
 * 作用：MyTransXAnimatorListener测试类
 * 描述：测试Matrix结合属性动画做的动画效果
 * 作者：pczhu
 * 创建时间： 15/12/10 上午10:38
 * 版本：V1.0
 * 修改历史：
 */
public class EightActivity extends AppCompatActivity implements  View.OnClickListener {
    private ImageView imageView;
    ValueAnimator animator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eightactivity);

        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setOnClickListener(this);
        imageView.setImageBitmap(ImageUtils.readBitMap(getApplicationContext(), R.drawable.i));
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.resume).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv:
                //setTranslateAnimation();
                //aBallWay();
                ObjectAnimator();
                break;
            case R.id.pause:
                if(animator!= null && animator.isRunning()){
                    animator.pause();
                }
                break;
            case R.id.resume:
                if(animator!=null){
                    animator.resume();
                }
                break;
            default:
                break;
        }

    }
    public void ObjectAnimator(){
        imageView.animate().translationX(200).alpha(0).setDuration(1000).start();
    }

    public void aBallWay(){
        int i =Utils.getScreenMaxWidth(getApplicationContext())[1];//1920
        PropertyValuesHolder px = PropertyValuesHolder.ofFloat("translationX",0,0);
        PropertyValuesHolder py = PropertyValuesHolder.ofFloat("translationY",0,((ViewGroup)imageView.getParent()).getMeasuredHeight()-imageView.getHeight()-imageView.getTop());
        LogUtils.i("top:"+imageView.getTop());
        animator = ValueAnimator.ofPropertyValuesHolder(px, py);
        animator.addUpdateListener(new MyTransXAnimatorListener(imageView.getImageMatrix(), imageView));
        animator.setDuration(2000);
        //animator.setInterpolator(new AccelerateInterpolator(1.98f));
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }
    /**
     * 各种练习
     */
    public void setTranslateAnimation() {
        PropertyValuesHolder px = PropertyValuesHolder.ofFloat("translationX",imageView.getX(),0.0f,90.0f,90.0f);
        PropertyValuesHolder py = PropertyValuesHolder.ofFloat("translationY",imageView.getY(),0.0f,90.0f,380.0f);

        PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX",imageView.getScaleX(),1.0f,0.0f,2.0f,1.0f);
        PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY",imageView.getScaleY(),1.0f,0.0f,2.0f,1.0f);
        PropertyValuesHolder pf = PropertyValuesHolder.ofFloat("rotation",0.0f,360.0f);


        //PropertyValuesHolder rx = PropertyValuesHolder.ofFloat("rotationX",imageView.getMeasuredWidth()/2);
        //PropertyValuesHolder ry = PropertyValuesHolder.ofFloat("rotationY", imageView.getMeasuredHeight()/2);

        animator = ValueAnimator.ofPropertyValuesHolder(px, py, sx, sy, pf);
        animator.addUpdateListener(new MyTransXAnimatorListener(imageView.getImageMatrix(),imageView));
        //ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, sx,sy);
        animator.setDuration(10000);
        animator.setInterpolator(new OvershootInterpolator());
        //animator.setStartDelay(500);
        animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("X:"+event.getX()+":Y:"+event.getY());
        return super.onTouchEvent(event);
    }
}


