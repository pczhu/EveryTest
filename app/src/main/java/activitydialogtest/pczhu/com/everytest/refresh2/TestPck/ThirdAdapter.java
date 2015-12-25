package activitydialogtest.pczhu.com.everytest.refresh2.TestPck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.domain.BlackBean;

/**
 * 名称：CustomTestxUtils
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/11/25 上午9:46
 * 版本：V1.0
 * 修改历史：
 */
public class ThirdAdapter extends MyBaseAdapter<BlackBean.Data>{
    public ThirdAdapter(Context context, ArrayList<BlackBean.Data> userList) {
        super(context, userList);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.item_layout,null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_caption);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_normal);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        BlackBean.Data bean = getOneDataCell(position);
        holder.tv.setText(bean.getPid()+":"+bean.getProject_name());
        x.image().bind(holder.iv, bean.getCover(), ImageOptions.DEFAULT);
        return convertView;
    }

    public class Holder {
        TextView tv;
        ImageView iv;
    }
}
