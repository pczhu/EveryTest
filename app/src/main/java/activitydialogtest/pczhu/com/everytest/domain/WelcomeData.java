package activitydialogtest.pczhu.com.everytest.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 名称：获取欢迎页面的图片数据
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/24 上午9:55
 * 版本：V1.0
 * 修改历史：
 */

public class WelcomeData implements Serializable{
    public WelcomeData() {
    }

    private String status;
    private String msg;
    private WelcomeInfo data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WelcomeInfo getData() {
        return data;
    }

    public void setData(WelcomeInfo data) {
        this.data = data;
    }
}
