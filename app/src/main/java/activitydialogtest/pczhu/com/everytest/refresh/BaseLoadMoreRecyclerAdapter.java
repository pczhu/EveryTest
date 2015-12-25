package activitydialogtest.pczhu.com.everytest.refresh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import activitydialogtest.pczhu.com.everytest.R;

/**
 * 名称：BaseLoadMoreRecyclerAdapter
 * 作用：Adapter 需要集成的基类
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 上午9:50
 * 版本：V1.0
 * 修改历史：
 */
public abstract class BaseLoadMoreRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    public static final int TYPE_FOOTER = Integer.MIN_VALUE;
    public static final int TYPE_ITEM = 0;
    private boolean hasFooter;//设置是否显示Footer
    private boolean hasMoreData;//设置是否可以继续加载数据

    private final List<T> mList = new LinkedList<T>();

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        public final ProgressWheel mProgressView;//Loading图标
        public final TextView mTextView;//提示性文字

        public FooterViewHolder(View view) {
            super(view);
            mProgressView = (ProgressWheel) view.findViewById(R.id.progress_view);
            mTextView = (TextView) view.findViewById(R.id.tv_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    //数据itemViewHolder 实现
    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {//底部 加载view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view_load_more, parent, false);
            return new FooterViewHolder(view);
        } else {
            //数据itemViewHolder
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    //正常数据itemViewHolder 实现
    public abstract void onBindItemViewHolder(final VH holder, int position);

    /**
     * 绑定底部标签的Holder
     * @param holder
     * @param position
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            //没有更多数据
            if (hasMoreData) {//如果可以继续加载数据
                ((FooterViewHolder) holder).mProgressView.setVisibility(View.VISIBLE);//Loading显示
                //  ((FooterViewHolder) holder).mProgressView.spin();
                //((FooterViewHolder) holder).mProgressView.setIndeterminate(true);
                ((FooterViewHolder) holder).mTextView.setText("正在加载。。。");
            } else {//如果不能继续加载
                //   ((FooterViewHolder) holder).mProgressView.stopSpinning();
                ((FooterViewHolder) holder).mProgressView.setVisibility(View.GONE);//Loading消失
                //((FooterViewHolder) holder).mProgressView.st;
                ((FooterViewHolder) holder).mTextView.setText("没有更多数据了。。。。");
            }
        } else {
            onBindItemViewHolder((VH) holder, position);
        }
    }


    @Override
    public int getItemViewType(int position) {
        //如果允许显示底标  返回TYPE_FOOTER类型
        if (position == getBasicItemCount() && hasFooter) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;//0
    }

    /**
     * 返回数据链
     * @return
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 追加某段数据
     * @param list
     */
    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
    }

    /**
     * 追加某条数据
     * @param t
     */
    public void append(T t) {
        if (t == null) {
            return;
        }
        mList.add(t);
    }

    /**
     * 添加某条数据到数据链顶部
     * @param item
     */
    public void appendToTop(T item) {
        if (item == null) {
            return;
        }
        mList.add(0, item);
    }

    /**
     * 添加某段数据到数据链顶部
     * @param list
     */
    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(0, list);
    }

    /**
     * 干掉某条数据
     * @param position
     */
    public void remove(int position) {
        if (position < mList.size() - 1 && position >= 0) {
            mList.remove(position);
        }
    }

    /**
     * 清除数据
     */
    public void clear() {
        mList.clear();
    }

    /**
     * 获取基本数据个数
     * @return
     */
    private int getBasicItemCount() {
        return mList.size();
    }

    /**
     * 获取数据+底部标记的总个数
     * @return
     */
    @Override
    public int getItemCount() {
        return getBasicItemCount() + (hasFooter ? 1 : 0);
    }

    /**
     * 获取某个单项
     * @param position
     * @return
     */
    public T getItem(int position) {
        if (position > mList.size() - 1) {
            return null;
        }
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public boolean hasFooter() {
        return hasFooter;
    }

    /**
     * 设置底部标签显示
     * @param hasFooter
     */
    public void setHasFooter(boolean hasFooter) {
        if(this.hasFooter != hasFooter) {
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    /**
     * 返回是否有新数据
     * @return
     */
    public boolean hasMoreData() {
        return hasMoreData;
    }

    /**
     * 显示更多数据
     * @param isMoreData
     */
    public void setHasMoreData(boolean isMoreData) {
        if(this.hasMoreData != isMoreData) {
            this.hasMoreData = isMoreData;
            notifyDataSetChanged();
        }
    }

    /**
     * 同时更改是否有新数据 是否显示底部标记
     * @param hasMoreData
     * @param hasFooter
     */
    public void setHasMoreDataAndFooter(boolean hasMoreData, boolean hasFooter) {
        if(this.hasMoreData != hasMoreData || this.hasFooter != hasFooter) {
            this.hasMoreData = hasMoreData;
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }


}
