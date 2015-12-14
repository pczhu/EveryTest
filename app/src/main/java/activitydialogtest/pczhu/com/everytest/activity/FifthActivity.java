package activitydialogtest.pczhu.com.everytest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：FifthActivity
 * 作用：
 * 描述：一个图片手势缩放 未完成
 * 作者：pczhu
 * 创建时间： 15/12/9 下午1:59
 * 版本：V1.0
 * 修改历史：
 */
public class FifthActivity extends AppCompatActivity implements View.OnTouchListener {
    ScaleGestureDetector scaleGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        findViewById(R.id.ll_fifth).setOnTouchListener(this);

        scaleGestureDetector = new ScaleGestureDetector(getApplicationContext(),new Listener());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return scaleGestureDetector.onTouchEvent(event);
    }

    public class Listener implements ScaleGestureDetector.OnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //LogUtils.i("缩放onScale");
//            LogUtils.i(detector.getCurrentSpan()
//                    + "--:--"+detector.getCurrentSpanX()
//                    +"--:--"+detector.getCurrentSpanY()
//                    +"--:--"+detector.getFocusX()
//                    +"--:--"+detector.getFocusY()
//                    +"--:--"+detector.getPreviousSpan()
//                    +"--:--"+detector.getPreviousSpanX()
//                    +"--:--"+detector.getPreviousSpanY()
//                    +"--:--"+detector.getEventTime()
//                    +"--:--"+detector.getTimeDelta()
//                    +"--:--"+detector.getScaleFactor());
            if(detector.getCurrentSpan() < detector.getPreviousSpan()
                    && Math.abs(detector.getCurrentSpan()-detector.getPreviousSpan()) > 2){
                LogUtils.i("距离跨度变小---缩放"+(detector.getCurrentSpan() - detector.getPreviousSpan()));
            }else if(detector.getCurrentSpan() > detector.getPreviousSpan()
                    && Math.abs(detector.getCurrentSpan()-detector.getPreviousSpan()) > 2){
                LogUtils.d("距离跨度变大---放大"+(detector.getCurrentSpan() - detector.getPreviousSpan()));
            }else{
                LogUtils.e("---不动"+(detector.getCurrentSpan() - detector.getPreviousSpan()));
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            LogUtils.i("缩放onScaleBegin");


//            detector.getCurrentSpan()      //两点间的距离跨度
//            detector.getCurrentSpanX();//两点间的x距离
//            detector.getCurrentSpanY();//两点间的y距离
//            detector.getFocusX();       //
//            detector.getFocusY();       //
//            detector.getPreviousSpan(); //上次
//            detector.getPreviousSpanX();//上次
//            detector.getPreviousSpanY();//上次
//            detector.getEventTime();    //当前事件的事件
//            detector.getTimeDelta();    //两次事件间的时间差
//            detector.getScaleFactor();  //与上次事件相比，得到的比例因子
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            LogUtils.i("缩放onScaleEnd");
        }
    }
}
