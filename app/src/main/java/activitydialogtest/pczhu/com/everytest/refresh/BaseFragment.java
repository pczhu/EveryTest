package activitydialogtest.pczhu.com.everytest.refresh;

import android.content.Context;
import android.support.v4.app.Fragment;

import activitydialogtest.pczhu.com.everytest.activity.TenthActivity;


/**
 * Created by sunger on 15/12/1.
 */
public class BaseFragment extends Fragment {


    private TenthActivity holder;

    /**
     * 有效附着之后 拿取context
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TenthActivity) {//做判断
            holder = (TenthActivity) context;
        }
    }

    /**
     * 获取Activity实例
     * @return
     */
    public TenthActivity getHolder() {
        if (holder == null) {
            throw new IllegalArgumentException("the acticity must be extends TenthActivity");
        }
        return holder;
    }
}
