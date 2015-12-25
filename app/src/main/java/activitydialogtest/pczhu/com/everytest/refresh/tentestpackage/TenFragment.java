package activitydialogtest.pczhu.com.everytest.refresh.tentestpackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;


import org.xutils.http.RequestParams;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.activity.TenthActivity;
import activitydialogtest.pczhu.com.everytest.domain.BlackBean;
import activitydialogtest.pczhu.com.everytest.refresh.MGridLayoutManager;
import activitydialogtest.pczhu.com.everytest.refresh.RefreshAndLoadFragment;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 上午10:31
 * 版本：V1.0
 * 修改历史：
 */
public class TenFragment extends RefreshAndLoadFragment {
    private TenAdapter adapter;
    private BaseListener baseListener;
    private TenthActivity holder;
    private RecyclerView recyclerView;
    private static final int GRID_COLUMN = 2;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TenthActivity) {//做判断
            holder = (TenthActivity) context;
        }
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        baseListener = new BaseListenerImpl<BlackBean>(holder);
        adapter = new TenAdapter(holder);
        recyclerView =getRecyclerView();
        recyclerView.setHasFixedSize(true);
        setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new MGridLayoutManager(holder,GRID_COLUMN,adapter));
        autoRefresh();
    }

    /**
     * 加载更多的请求
     */
    @Override
    protected void onFragmentLoadMore() {
        RequestParams requestParams = new RequestParams("http://app.dev2.renrentou.com/user/GetBlackList");
        requestParams.addBodyParameter("type", "2");
        baseListener.loadMore(requestParams,
                getCurrentPage(), BlackBean.class, new HttpCallBack<BlackBean>() {
                    @Override
                    public void successForData(BlackBean o) {
                        showMoreData(o.getData());
                    }

                    @Override
                    public void successButNoResult(Exception e, String statue, String msg) {
                        if("-102".equals(statue))
                            showLoadError("没有更多数据");

                    }

                    @Override
                    public void failed(Throwable e, String msg) {
                       // showError(ACTION_NONE,getString(R.string.msg_error_load));
                        showLoadError(getString(R.string.msg_error_load));
                    }
                });
    }

    /**
     * 刷新的请求
     */
    @Override
    public void onRefreshData() {
        RequestParams requestParams = new RequestParams("http://app.dev2.renrentou.com/user/GetBlackList");
        requestParams.addBodyParameter("type","2");
        baseListener.refresh(requestParams, 1, BlackBean.class, new HttpCallBack<BlackBean>() {
            @Override
            public void successForData(BlackBean o) {
                showRefreshData(o.getData());
            }

            @Override
            public void successButNoResult(Exception e, String statue, String msg) {
                if ("-102".equals(statue))
                    showError(ACTION_NONE, "没有数据");
            }

            @Override
            public void failed(Throwable e, String msg) {
                showError(ACTION_NONE, getString(R.string.msg_error_refresh));
            }

        });
    }

    private void autoRefresh() {
        getSwipeRefreshWidget().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_REFRESH;
                getSwipeRefreshWidget().setRefreshing(true);
                RequestParams requestParams = new RequestParams("http://app.dev2.renrentou.com/user/GetBlackList");
                requestParams.addBodyParameter("type","2");
                baseListener.refresh(requestParams,
                        1, BlackBean.class, new HttpCallBack<BlackBean>() {
                            @Override
                            public void successForData(BlackBean o) {
                                showRefreshData(o.getData());
                            }

                            @Override
                            public void successButNoResult(Exception e, String statue, String msg) {
                                if ("-102".equals(statue))
                                    showError(ACTION_NONE, "没有数据");
                            }

                            @Override
                            public void failed(Throwable e, String msg) {
                                showError(ACTION_NONE, "找不到相关数据");
                            }

                        });
            }
        }, 600);
    }

}
