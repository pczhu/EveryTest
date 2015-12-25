package activitydialogtest.pczhu.com.everytest.refresh.tentestpackage;

import activitydialogtest.pczhu.com.everytest.refresh.RefreshAndLoadFragment;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 上午10:50
 * 版本：V1.0
 * 修改历史：
 */
public class BaseHttpFragment extends RefreshAndLoadFragment {


    private TenAdapter tenAdapter;
    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        tenAdapter = new TenAdapter(getActivity());

    }

    @Override
    public void onRefreshData() {

    }

    @Override
    protected void onFragmentLoadMore() {

    }
    public TenAdapter getTenAdapter() {
        return tenAdapter;
    }

    public void setTenAdapter(TenAdapter tenAdapter) {
        this.tenAdapter = tenAdapter;
    }
}
