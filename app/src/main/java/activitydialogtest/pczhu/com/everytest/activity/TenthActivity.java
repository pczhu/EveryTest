package activitydialogtest.pczhu.com.everytest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;


import java.util.logging.LogRecord;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.utils.SolidToast;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/14 下午5:35
 * 版本：V1.0
 * 修改历史：
 */
public class TenthActivity extends AppCompatActivity{
    private android.app.AlertDialog.Builder commondialog;
    private Handler handlrer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth);
        handlrer = new Handler();

    }

    /**
     * 显示文字在屏幕区域
     * @param msg
     */
    public void showMsgInBottom(String msg) {
        SolidToast.make(this, msg, Gravity.BOTTOM).setBackgroundColorId(R.color.colorPrimaryDark).show();
    }

    /**
     * 显示文字在屏幕区域
     * @param msgSringId
     */
    public void showMsgInBottom(int msgSringId) {
        showMsgInBottom(getString(msgSringId));
    }

    public void showMsgBelowTabLayout(String string) {
        commondialog = new android.app.AlertDialog.Builder(this);
        commondialog.setTitle(string);
        //commondialog.setMessage(message);
        //commondialog.setPositiveButton(buttontxt, onClickListener);
        commondialog.setCancelable(false);
        final AlertDialog aleardialog = commondialog.create();
        aleardialog.show();
        handlrer.postDelayed(new Runnable() {
            @Override
            public void run() {
                aleardialog.dismiss();
            }
        },1500);

    }
}
