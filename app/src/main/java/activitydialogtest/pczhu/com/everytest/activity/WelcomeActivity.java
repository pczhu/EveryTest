package activitydialogtest.pczhu.com.everytest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.xutils.x;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.db.Action.DbAction;
import activitydialogtest.pczhu.com.everytest.db.Action.WelcomeInfoData;
import activitydialogtest.pczhu.com.everytest.utils.ImageUtils;
import activitydialogtest.pczhu.com.everytest.utils.MyLong;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/24 上午9:24
 * 版本：V1.0
 * 修改历史：
 */
public class WelcomeActivity extends AppCompatActivity {
    private static long DURATION = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        WelcomeInfoData welcomeInfoData = DbAction.getInstance(this);

        ImageView imageView = (ImageView)findViewById(R.id.image);
        if(welcomeInfoData.getWelcomeInfo()!= null) {
            x.image().bind(imageView, welcomeInfoData.getWelcomeInfo().getPath(), ImageUtils.getImageOptions());
            DURATION = MyLong.parseString(welcomeInfoData.getWelcomeInfo().getMilltime(),2000L);
        }else{
            imageView.setImageBitmap(ImageUtils.readBitMap(this, R.drawable.a));
        }
        // 闪屏的核心代码
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, FourteenthActivity.class); //从启动动画ui跳转到主ui
                startActivity(intent);
                WelcomeActivity.this.finish(); // 结束启动动画界面
            }
        }, DURATION); //启动动画持续3秒钟
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
    }
}