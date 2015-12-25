package activitydialogtest.pczhu.com.everytest.refresh.tentestpackage;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.domain.BlackBean;
import activitydialogtest.pczhu.com.everytest.refresh.BaseLoadMoreRecyclerAdapter;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 上午10:35
 * 版本：V1.0
 * 修改历史：
 */
public class TenAdapter extends BaseLoadMoreRecyclerAdapter<BlackBean.Data,TenAdapter.ViewHolder>{
    private Activity activity;
    private final BitmapUtils bitmapUtils;

    public TenAdapter(Activity activity){
        this.activity = activity;
        bitmapUtils = new BitmapUtils(activity);
    }
    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final ViewHolder holder, final int position) {
        BlackBean.Data blackBean =  getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(activity,""+position,Toast.LENGTH_LONG).show();
            }
        });
        holder.tv.setText(blackBean.getProject_name());

        bitmapUtils.display(holder.iv, blackBean.getCover());

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView tv ;
        public final ImageView iv;

        public ViewHolder(View view){
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_caption);
            iv = (ImageView) view.findViewById(R.id.iv_normal);
        }
    }
}
