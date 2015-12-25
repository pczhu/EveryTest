package activitydialogtest.pczhu.com.everytest.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lidroid.xutils.bitmap.download.Downloader;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.app.HttpRetryHandler;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.Executor;

import activitydialogtest.pczhu.com.everytest.R;
import activitydialogtest.pczhu.com.everytest.db.Action.DbAction;
import activitydialogtest.pczhu.com.everytest.db.Action.WelcomeInfoData;
import activitydialogtest.pczhu.com.everytest.domain.WelcomeData;
import activitydialogtest.pczhu.com.everytest.utils.ImageUtils;
import activitydialogtest.pczhu.com.everytest.utils.LogUtils;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/24 下午2:39
 * 版本：V1.0
 * 修改历史：
 */
public class InitServices extends Service {

    private WelcomeInfoData welcomeInfoData;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        welcomeInfoData = DbAction.getInstance(this);

        super.onCreate();
        x.http().get(new RequestParams("http://192.168.2.4:8080/MyServerInterface/getWelcome"), new Callback.CommonCallback<String>() {

            private WelcomeData welcomeData;

            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                welcomeData = null;
                try {
                    welcomeData = gson.fromJson(s, WelcomeData.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (welcomeData != null) {
                    //getImage();
                    getLoadImage();


                }
            }

            private void getLoadImage() {
                x.image().loadFile(welcomeData.getData().getImg(),
                        ImageUtils.getImageOptions(),
                        new CommonCallback<File>(){

                    @Override
                    public void onSuccess(File file) {
                        if(file != null) {
                            welcomeData.getData().setPath(file.getAbsolutePath());
                            welcomeInfoData.setWelcomeInfo(welcomeData.getData(), false);
                            LogUtils.i("数据存入");
                        }
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }

            private void getImage() {
                RequestParams requestParams = new RequestParams(welcomeData.getData().getImg());
                requestParams.setSaveFilePath(Environment.getDataDirectory()+"/download/");
                requestParams.setAutoResume(false);
                requestParams.setAutoRename(false);
                requestParams.setHttpRetryHandler(new HttpRetryHandler());
                x.http().post(requestParams, new CommonCallback<File>() {
                   @Override
                   public void onSuccess(final File file) {
                       if(file != null) {
                           welcomeData.getData().setPath(file.getAbsolutePath());
                           welcomeInfoData.setWelcomeInfo(welcomeData.getData(), false);
                           LogUtils.i("数据存入");
                       }
                   }

                   @Override
                   public void onError(Throwable throwable, boolean b) {

                   }

                   @Override
                   public void onCancelled(CancelledException e) {

                   }

                   @Override
                   public void onFinished() {

                   }
               });
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}
