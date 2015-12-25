package activitydialogtest.pczhu.com.everytest.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import activitydialogtest.pczhu.com.everytest.R;


/**
 * Created by sunger on 2015/11/20.
 */
public abstract class LoadMoreRecyclerFragemnt<T> extends BaseFragment {
    protected static final int DEF_DELAY = (int) (1 * 1000);
    protected final static int STATE_LOAD = 0;//状态标记--
    protected final static int STATE_NORMAL = 1;//状态标记
    protected BaseLoadMoreRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    protected int currentState = STATE_NORMAL;//当前状态
    protected long currentTime = 0;
    protected int currentPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_loadmore_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);//获取RecyclerView
        mRecyclerView.addOnScrollListener(new OnRecycleViewScrollListener() {
            @Override
            public void onLoadMore() {
                if (currentState == STATE_NORMAL) {//当前状态为普通状态
                    currentState = STATE_LOAD;//变为加载状态
                    currentTime = TimeUtils.getCurrentTime();//获取当前时间点
                    mAdapter.setHasFooter(true);//显示底部控件
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);//滑动到底部
                    onFragmentLoadMore();//加载更多
                } else {
                    getHolder().showMsgInBottom(R.string.msg_waitting_loding);//如果已经是正在加载 则提示正在加载
                }
            }
        });
        onFragmentCreate();//初始化Fragment
    }

    /**
     * 请求已经完成针对新数据进行处理
     * @param dataList
     */
    public void showMoreData(final List<T> dataList) {
        int delay = 0;
        if (TimeUtils.getCurrentTime() - currentTime < DEF_DELAY) {
            delay = DEF_DELAY;//小于1秒要置为1秒 让动画显示至少一秒
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentState = STATE_NORMAL;//刷新状态结束  改为普通状态
                if (dataList.isEmpty()) {//如果新的数据为空
                    mAdapter.setHasMoreDataAndFooter(false, false);//无数据 不显示标记
                    getHolder().showMsgInBottom(R.string.msg_no_more_data);//给提示性文字
                } else {
                    mAdapter.appendToList(dataList);//有新的数据 添加
                    currentPage++;//page++
                    mAdapter.setHasMoreDataAndFooter(true, false);//有数据 不显示标记
                }
                mAdapter.notifyDataSetChanged();
            }
        }, delay);
    }

    /**
     * 设置Adapter
     * @param mAdapter
     */
    public void setAdapter(BaseLoadMoreRecyclerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 关闭底部标记 提示错误信息
     * @param errorMsg
     */
    public void showLoadError(String errorMsg) {
        mAdapter.setHasFooter(false);
        getHolder().showMsgInBottom(errorMsg);
    }

    /**
     * 提示错误信息
     * @param errorMsgId
     */
    public void showLoadError(int errorMsgId) {
        showLoadError(getString(errorMsgId));
    }

    /**
     * 获取当前页面
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 加载更多
     */
    protected abstract void onFragmentLoadMore();

    /**
     * Fragment加载
     */
    protected abstract void onFragmentCreate();

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


}
