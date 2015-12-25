package activitydialogtest.pczhu.com.everytest.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import activitydialogtest.pczhu.com.everytest.R;


/**
 * 名称：RefreshAndLoadFragment
 * 作用：继承了加载更多的Fragment 在加载更多的基础上添加了刷新
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 上午10:11
 * 版本：V1.0
 * 修改历史：
 */
public abstract class RefreshAndLoadFragment<T> extends LoadMoreRecyclerFragemnt implements SwipeRefreshLayout.OnRefreshListener {
    protected final static int STATE_REFRESH = 2;
    private SwipeRefreshLayout mSwipeRefreshWidget;//刷新控件
    public static final int ACTION_NONE = 0;//无动作

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_video_refresh_loadmore_layout, container, false);
    }

    @Override
    protected void onFragmentCreate() {
        mSwipeRefreshWidget = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    /**
     * 刷新请求完成之后 调用 处理新请求下来的数据
     * @param dataList
     */
    public void showRefreshData(final List<T> dataList) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;//变更状态为普通状态
                mSwipeRefreshWidget.setRefreshing(false);//刷新状态改为否
                currentPage = 2;//page号码改为第二页
                mAdapter.getList().clear();//清除数据
                mAdapter.appendToList(dataList);//添加新数据
                mAdapter.notifyDataSetChanged();//刷新页面
            }
        }, delay);
    }

    /**
     * 刷新数据的网络请求
     */
    public abstract void onRefreshData();

    /**
     * 刷新监听
     */
    @Override
    public void onRefresh() {
        if (currentState == STATE_NORMAL) {//如果状态为普通
            currentState = STATE_REFRESH;//状态改为刷新中
            currentTime = TimeUtils.getCurrentTime();//获取当前时间
            mAdapter.setHasFooter(true);
            onRefreshData();//刷新的网络操作
        } else {
            getHolder().showMsgInBottom(R.string.msg_waitting_loding);
        }
    }

    public SwipeRefreshLayout getSwipeRefreshWidget() {
        return mSwipeRefreshWidget;
    }


    public void showError(int newcurrentState,String errorMsg) {
        currentState = newcurrentState;
        if (getSwipeRefreshWidget().isRefreshing()) {
            getSwipeRefreshWidget().setRefreshing(false);
            getHolder().showMsgBelowTabLayout(errorMsg);
        } else {
            getHolder().showMsgInBottom(errorMsg);
            mAdapter.setHasFooter(false);
        }

    }

}
