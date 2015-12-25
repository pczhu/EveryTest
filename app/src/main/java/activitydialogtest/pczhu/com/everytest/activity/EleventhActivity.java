package activitydialogtest.pczhu.com.everytest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import activitydialogtest.pczhu.com.everytest.R;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/17 上午9:23
 * 版本：V1.0
 * 修改历史：
 */
public class EleventhActivity extends AppCompatActivity{
    String targetHtml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><html><body>abcdefg</body></html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh);
        WebView we = (WebView) findViewById(R.id.webView1);
        we.loadData(targetHtml,"text/html","UTF-8");
    }
}
