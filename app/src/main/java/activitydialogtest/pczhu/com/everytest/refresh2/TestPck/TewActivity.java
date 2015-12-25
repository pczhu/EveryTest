package activitydialogtest.pczhu.com.everytest.refresh2.TestPck;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xutils.http.RequestParams;

import java.util.ArrayList;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.domain.BlackBean;
import activitydialogtest.pczhu.com.everytest.refresh2.PullToRefreshView;

/**
 * 名称：CustomTestxUtils
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/11/25 上午9:34
 * 版本：V1.0
 * 修改历史：
 */
public class TewActivity extends BaseActivity{
    private PullToRefreshView pullToRefreshView;
    private ListView listview;
    private ArrayList<BlackBean.Data> userlist;
    private ThirdAdapter mainAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview);
        pullToRefreshView = (PullToRefreshView) findViewById(R.id.main_pull_refresh_view);
        listview = (ListView) findViewById(R.id.xListView);
        mainAdapter = new ThirdAdapter(this,userlist);
        RequestParams requestParams = new RequestParams("http://app.dev2.renrentou.com/user/GetBlackList");
        requestParams.addBodyParameter("type", "2");
        openListener(pullToRefreshView,
                listview,
                mainAdapter,
                BlackBean.class,
                requestParams,
                new OnActionListener() {

        });
        addErrorView(this);
    }

    @Override
    protected View getRootView() {
        return ((ViewGroup)this.findViewById(R.id.ll_root)).getChildAt(1);
    }

    @Override
    public ArrayList getObjectList(Object obj) {

        return ((BlackBean)obj).getData();
    }
}
