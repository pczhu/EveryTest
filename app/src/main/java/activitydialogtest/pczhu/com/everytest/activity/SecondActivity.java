package activitydialogtest.pczhu.com.everytest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import activitydialogtest.pczhu.com.everytest.FlowLayout;
import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：SecondActivity
 * 作用：流标签
 * 描述：一个流标签的简单实现 为了学习页面布局
 * 作者：pczhu
 * 创建时间： 15/11/30 下午5:27
 * 版本：V1.0
 * 修改历史：
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FlowLayout flowLayout = (FlowLayout) findViewById(R.id.cl);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("1");
        arrayList.add("132434");
        arrayList.add("daf");
        arrayList.add("adfadfdsaaadfadffdsafdsaf");
        arrayList.add("这是数据");
        arrayList.add("这是一段");
        arrayList.add("这是一段测试");
        arrayList.add("这是一段数据类型为未知");
        arrayList.add("adfadf");
        arrayList.add("13209u4j qcdiu9");
        arrayList.add("aidgfh98");
        arrayList.add("230984908230-4-203");
        arrayList.add("adsjflkd");
        arrayList.add("这是类型为未知");
        arrayList.add("这是一段数据类型为未知的发货");
        arrayList.add("这是一段数据类型为未");
        arrayList.add("213");
        arrayList.add("喝的是拉开积分；受打击佛地偶发几哦啊是大家发");
        arrayList.add("2地发呆发呆时是否");
        arrayList.add("打发士大夫撒地方");

        flowLayout.setTextlist(arrayList);
        LogUtils.d("无");
        LogUtils.e("吗");
        LogUtils.e("waht",new Throwable());
        LogUtils.i("吴");
        LogUtils.v("aaadafasfa");
        LogUtils.w("警告",new Exception());
        //LogUtils.e("无", "打击");
        //LogUtils.e("时","打击");
        pt();
    }
    public void pt(){
        LogUtils.d("无3");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("无er");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
