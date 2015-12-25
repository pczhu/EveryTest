package activitydialogtest.pczhu.com.everytest.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.Date;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.db.Action.DbAction;
import activitydialogtest.pczhu.com.everytest.db.Action.WelcomeInfoData;
import activitydialogtest.pczhu.com.everytest.domain.WelcomeData;
import activitydialogtest.pczhu.com.everytest.domain.WelcomeInfo;
import activitydialogtest.pczhu.com.everytest.service.InitServices;
import activitydialogtest.pczhu.com.everytest.utils.ImageUtils;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：测试DB内容
 * 作者：pczhu
 * 创建时间： 15/12/24 下午1:47
 * 版本：V1.0
 * 修改历史：
 */
public class FourteenthActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourteenth);
        Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText3);

        final ImageView imageview = (ImageView) findViewById(R.id.imageView);


        final WelcomeInfoData welcomeInfoData = DbAction.getInstance(FourteenthActivity.this);
        final Intent intent = new Intent(FourteenthActivity.this, InitServices.class);


        Button btn3 = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  startService(intent);
//                WelcomeInfo welcomeInfo = new WelcomeInfo();
//                welcomeInfo.setAdVersion(Integer.parseInt(editText.getText().toString()));
//                welcomeInfo.setImg("http://pic2.ooopic.com/01/03/51/25b1OOOPIC19.jpg");
//                welcomeInfo.setBeginData((new Date().getTime() - 1000000) + "");
//                welcomeInfo.setEndData((new Date().getTime() + 5000000) + "");
//                welcomeInfo.setMilltime(2000 + "");
//                welcomeInfo.setPath("/sdcard/imgae/a.png");
//
//                welcomeInfoData.setWelcomeInfo(welcomeInfo, false);
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(welcomeInfoData.getWelcomeInfo() == null){
                    return;
                }
                textView.setText(welcomeInfoData.getWelcomeInfo().toString());

                Toast.makeText(FourteenthActivity.this,
                        " " + new File(welcomeInfoData.getWelcomeInfo().getPath()).exists(),
                        Toast.LENGTH_LONG).show();
                x.image().bind(imageview,welcomeInfoData.getWelcomeInfo().getPath(), ImageUtils.getImageOptions());
               // imageview.setImageBitmap(new Bitmap(Uri.fromFile(new File(welcomeInfoData.getWelcomeInfo().getPath()))));
            }
        });


    }

}
