package activitydialogtest.pczhu.com.everytest.refresh.tentestpackage;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/16 上午9:47
 * 版本：V1.0
 * 修改历史：
 */
public abstract class HttpCallBack<T> {
    public void start(){

    }
    public void success(String result){

    }
    public abstract void successForData(T t);
    public void successButNoResult(Exception e,String statue,String msg){

    }
    public void failed(Throwable e,String msg){

    }
    public void finished(){

    }
}
