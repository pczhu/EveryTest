package activitydialogtest.pczhu.com.everytest.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/24 下午2:53
 * 版本：V1.0
 * 修改历史：
 */
@Table(name = "WelcomeInfo")
public class WelcomeInfo implements Serializable {
    public WelcomeInfo() {
    }
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "img")
    private String img;
    @Column(name = "adversion",property = "UNIQUE")
    private int adversion;
    @Column(name = "milltime")
    private String milltime;
    @Column(name = "begindata")
    private String beginData;
    @Column(name = "path")
    private String path;
    @Column(name = "enddata")
    private String endData;

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAdVersion() {
        return adversion;
    }

    public void setAdVersion(int adversion) {
        this.adversion = adversion;
    }

    public String getMilltime() {
        return milltime;
    }

    public void setMilltime(String milltime) {
        this.milltime = milltime;
    }

    public String getBeginData() {
        return beginData;
    }

    public void setBeginData(String begindata) {
        this.beginData = begindata;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "WelcomeInfo{" +
                "img='" + img + '\'' +
                ", adversion='" + adversion + '\'' +
                ", milltime='" + milltime + '\'' +
                ", beginData='" + beginData + '\'' +
                ", path='" + path + '\'' +
                ", endData='" + endData + '\'' +
                '}';
    }
}
