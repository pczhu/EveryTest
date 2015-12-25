package activitydialogtest.pczhu.com.everytest.refresh.tentestpackage;


import org.xutils.http.RequestParams;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 下午12:04
 * 版本：V1.0
 * 修改历史：
 */
public interface BaseListener<T> {
    /**
     * 加载更多网络请求的实际操作类
     * @param requestParams
     * @param page
     */
     void loadMore(RequestParams requestParams,int page,Class clz,HttpCallBack<T> httpCallBack);

    /**
     * 刷新的网络请求实际操作类
     * @param requestParams
     * @param page
     */
     void refresh(RequestParams requestParams,int page,Class clz,HttpCallBack<T> httpCallBack);

}
