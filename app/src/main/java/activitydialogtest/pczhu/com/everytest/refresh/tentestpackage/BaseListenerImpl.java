package activitydialogtest.pczhu.com.everytest.refresh.tentestpackage;

import android.content.Context;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;



import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import activitydialogtest.pczhu.com.everytest.domain.Failed;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/15 下午12:06
 * 版本：V1.0
 * 修改历史：
 */
public class BaseListenerImpl<T> implements BaseListener {
    private Context mContext;
    private Gson gson;
    private HttpUtils httpUtils;

    public BaseListenerImpl(Context mContext){
        this.mContext = mContext;
        gson = new Gson();
        httpUtils = new HttpUtils();
    }


    @Override
    public void loadMore(RequestParams requestParams, int page,Class clz, HttpCallBack httpCallBack) {
        requestBegin(requestParams, page, clz, httpCallBack);
    }

    @Override
    public void refresh(RequestParams requestParams, int page,Class clz, HttpCallBack httpCallBack) {

        requestBegin(requestParams, page, clz, httpCallBack);
    }

    private void requestBegin(RequestParams requestParams, int page, final Class clz, final HttpCallBack httpCallBack) {
        httpCallBack.start();
        requestParams.addBodyParameter("page",page+"");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String responseInfo) {
                httpCallBack.success(responseInfo);
                try {
                    Object o = gson.fromJson(responseInfo, clz);
                    httpCallBack.successForData(o);
                } catch (Exception e) {
                    try {
                        Failed failed = gson.fromJson(responseInfo, Failed.class);
                        httpCallBack.successButNoResult(e, failed.getStatus(), failed.getMsg());
                    } catch (Exception e2) {
                        httpCallBack.failed(e2, "两次解析均失败");
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                httpCallBack.failed(throwable, "请求失败");
                httpCallBack.finished();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {
                httpCallBack.finished();
            }
        });
    }

//
//    private void updateView(final List entities, int delay, final int page) {
//        //定时器延时刷新接界面
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (page == 1) {
//                    //view.refreshView(entities);
//                } else {
//                    //view.loadMoreView(entities);
//                }
//            }
//        }, delay);
//    }
}
