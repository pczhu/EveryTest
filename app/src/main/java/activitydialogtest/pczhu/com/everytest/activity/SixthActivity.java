package activitydialogtest.pczhu.com.everytest.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.library.multouch.BaseGestureDetector;
import activitydialogtest.pczhu.com.everytest.library.multouch.MoveGestureDetector;
import activitydialogtest.pczhu.com.everytest.library.multouch.RotateGestureDetector;
import activitydialogtest.pczhu.com.everytest.library.multouch.ShoveGestureDetector;
import activitydialogtest.pczhu.com.everytest.library.multouch.TwoFingerGestureDetector;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：SixthActivity
 * 作用：MoveGestureDetector等的测试
 * 描述：第三方手势操作监听
 * 作者：pczhu
 * 创建时间： 15/12/9 下午4:43
 * 版本：V1.0
 * 修改历史：
 */
public class SixthActivity extends AppCompatActivity implements View.OnTouchListener {
    MoveGestureDetector moveGestureDetector;
    RotateGestureDetector rotateGestureDetector;
    ShoveGestureDetector shoveGestureDetector;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        MultiTouchListener multiTouchListener = new MultiTouchListener();

        moveGestureDetector = new MoveGestureDetector(getApplicationContext(),multiTouchListener);
        rotateGestureDetector = new RotateGestureDetector(getApplicationContext(),multiTouchListener);
        shoveGestureDetector = new ShoveGestureDetector(getApplicationContext(),multiTouchListener);
        findViewById(R.id.ll_sixth).setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        moveGestureDetector.onTouchEvent(event);
        rotateGestureDetector.onTouchEvent(event);
        shoveGestureDetector.onTouchEvent(event);
        return true;
    }

    public class MultiTouchListener implements MoveGestureDetector.OnMoveGestureListener,
            RotateGestureDetector.OnRotateGestureListener,
            ShoveGestureDetector.OnShoveGestureListener{

        @Override
        public boolean onMove(MoveGestureDetector detector) {
            LogUtils.i("onMove");
            return true;
        }

        @Override
        public boolean onMoveBegin(MoveGestureDetector detector) {
            LogUtils.i("onMoveBegin");
            return true;
        }

        @Override
        public void onMoveEnd(MoveGestureDetector detector) {
            LogUtils.i("onMoveEnd");

        }

        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            LogUtils.i("onRotate");
            return true;
        }

        @Override
        public boolean onRotateBegin(RotateGestureDetector detector) {
            LogUtils.i("onRotateBegin");
            return true;
        }

        @Override
        public void onRotateEnd(RotateGestureDetector detector) {
            LogUtils.i("onRotateEnd");

        }

        @Override
        public boolean onShove(ShoveGestureDetector detector) {
            LogUtils.i("onShove");
            return true;
        }

        @Override
        public boolean onShoveBegin(ShoveGestureDetector detector) {
            LogUtils.i("onShoveBegin");
            return true;
        }

        @Override
        public void onShoveEnd(ShoveGestureDetector detector) {
            LogUtils.i("onShoveEnd");
        }

    }
}
