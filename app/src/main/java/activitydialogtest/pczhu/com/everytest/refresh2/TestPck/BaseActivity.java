package activitydialogtest.pczhu.com.everytest.refresh2.TestPck;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.domain.Failed;
import activitydialogtest.pczhu.com.everytest.refresh2.PullToRefreshView;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;
import activitydialogtest.pczhu.com.everytest.utils.ToastShow;

/**
 * 名称：CustomTestxUtils
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/11/24 下午12:06
 * 版本：V1.0
 * 修改历史：
 */
public abstract class BaseActivity extends AppCompatActivity implements PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    protected int page = 1;
    protected MyBaseAdapter mainAdapter;
    private OnActionListener onActionListener;
    private PullToRefreshView mPullToRefreshView;
    private ListView listview;
    //protected boolean isRefreshFromTop;
    protected TextView tv_error;
    protected Class<?> clz;
    protected Object obj;
    protected ArrayList userlist;
    protected RequestParams requestParams;
    private boolean flag_is_load = false;
    private boolean flag_is_refresh = false;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

//    public void onLoadNext() {
//
//        page++;
//        loadData(page);
//    }
//    public void onRefresh() {
//        //pageStaggeredGridView.setState(LoadingFooter.State.Idle);
//        loadFirst();
//    }
    private void loadFirst(){
        page = 1;
        loadData(page);

    }

    private void loadData(int page) {
        if (flag_is_load || flag_is_refresh) {
            return;
        }

        sendToHttp(page);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {


        loadFirst();
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {

        page++;
        loadData(page);
    }

    public interface OnActionListener{

    }
//    public void start(OnActionListener onActionListener){
//        this.onActionListener = onActionListener;
//    }
    public void openListener( PullToRefreshView mPullToRefreshView,
                              ListView listview,
                              MyBaseAdapter adapter,
                              Class<?> clz,
                              RequestParams requestParams,
                              OnActionListener onActionListener){
        this.mPullToRefreshView = mPullToRefreshView;
        this.mainAdapter = adapter;
        this.clz = clz;
        this.onActionListener = onActionListener;
        this.requestParams = requestParams;
        try {
            obj = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        AnimationAdapter animationAdapter = new CardsAnimationAdapter(mainAdapter);//添加卡片式动画
        animationAdapter.setAbsListView(listview);
        listview.setAdapter(animationAdapter);

        loadFirst();
    }
    protected abstract View getRootView();

    private Callback.Cancelable cancelable;
    private void sendToHttp(final int page) {
        if(page == 1){
            flag_is_refresh = true;
        }else{
            flag_is_load = true;
        }
        RequestParams requestParams = getRequestParams(page);
        cancelable = x.http().post(requestParams, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                Failed failBean = null;
                try {
                    obj = gson.fromJson(s, clz);
                }catch (Exception e){
                    failBean = gson.fromJson(s, Failed.class);
                }

                if(failBean == null && obj!=null){
                    if(page == 1){
                       // userlist = project.getData();
                        userlist = getObjectList(obj);
                        LogUtils.i(userlist.get(0).toString());
                    }else if(userlist!=null){
                        //userlist.addAll(project.getData());
                        userlist.addAll(getObjectList(obj));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainAdapter.notifyDataSetChanged(userlist);
                            mPullToRefreshView.onFooterRefreshComplete();
                            mPullToRefreshView.onHeaderRefreshComplete();
                            showError(0,false);
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPullToRefreshView.onFooterRefreshComplete();
                            mPullToRefreshView.onHeaderRefreshComplete();
                            controlWhichShow(1);
                        }
                    });

                }
                flag_is_refresh = false;
                flag_is_load = false;

            }
            @Override
            public void onError(Throwable throwable, boolean b) {
                LogUtils.i(throwable.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.onFooterRefreshComplete();
                        mPullToRefreshView.onHeaderRefreshComplete();
                        controlWhichShow(2);

                    }
                });

            }
            @Override
            public void onCancelled(CancelledException e) {
                mPullToRefreshView.onFooterRefreshComplete();
                mPullToRefreshView.onHeaderRefreshComplete();
            }
            @Override
            public void onFinished() {

            }
            @Override
            public boolean onCache(String s) {
                return false;
            }
        });
    }

    @NonNull
    private RequestParams getRequestParams(int page) {
        //RequestParams requestParams = new RequestParams("http://app.renrentou.com/star/GetInvestor");
        requestParams.addBodyParameter("page",page+"");
        requestParams.addBodyParameter("page_num", "5");
        return requestParams;
    }

    /**
     * 添加错误布局
     */
    public void addErrorView(Activity activity){
       // LogUtils.i("添加了错误布局"+getRootView().getClass().getName());
        getRootView();
        View error_view = View.inflate(this, R.layout.error_layout,null);

        tv_error = (TextView) error_view.findViewById(R.id.tv_error);

        ((ViewGroup)getRootView())
                .addView(error_view,0);
        error_view.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }
    /**
     * 判断如何显示
     * @param statue  状态  1没有数据 2访问失败
     */
    public void controlWhichShow(int statue){
        boolean flag = true;
        if(userlist!=null && userlist.size()!=0){
            if(statue == 1){
                ToastShow.showTextToast(this, "没有找到相关数据");
            }else if(statue == 2){
                ToastShow.showTextToast(this, "网络请求出现问题");
            }
            flag = false;
        }
        showError(statue, flag);

    }
    /**
     * 显示文字
     * @param statue
     * @param flag
     *              true 显示错误布局 false 不显示错误布局
     */
    public void showError(int statue,boolean flag){
        if(statue == 1){
            tv_error.setText("无数据");
            //refreshView(LoadingFooter.State.TheEnd);
        }else if(statue == 2){
            tv_error.setText("访问失败");
            //refreshView(LoadingFooter.State.ERROR);
        }
        if(flag){
            ((ViewGroup)getRootView()).getChildAt(0).setVisibility(View.VISIBLE);
        }else{
            ((ViewGroup)getRootView()).getChildAt(0).setVisibility(View.GONE);
        }
//        if(flag){
//            ((ViewGroup)getRootView())
//                    .getChildAt(((ViewGroup) getRootView()).getChildCount() - 1)
//                    .setVisibility(View.VISIBLE);
//
//        }else{
//            ((ViewGroup)getRootView())
//                    .getChildAt(((ViewGroup) getRootView()).getChildCount() - 1)
//                    .setVisibility(View.GONE);
//
//        }
    }



    /**
     * 拿到集合类型
     * @param obj
     * @return
     */
    public abstract ArrayList getObjectList(Object obj);

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // activity = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(cancelable!=null)
            cancelable.cancel();
    }

}
