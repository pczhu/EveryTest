package activitydialogtest.pczhu.com.everytest.db.Action;

import activitydialogtest.pczhu.com.everytest.domain.WelcomeData;
import activitydialogtest.pczhu.com.everytest.domain.WelcomeInfo;

/**
 * 名称：欢迎页面数据存储接口
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/24 上午9:50
 * 版本：V1.0
 * 修改历史：
 */
public interface WelcomeInfoData {
    /**
     * 存入欢迎信息
     * @param welcomeInfo
     */
    public void setWelcomeInfo(WelcomeInfo welcomeInfo,boolean drop);

    /**
     * 获取整个信息
     * @return
     */
    public WelcomeInfo getWelcomeInfo();
//    /**
//     * 存入图片地址
//     * @param url
//     */
//    public void setPath(String url,int version);
//    /**
//     * 获取图片网址
//     * @return
//     */
//    public String getPic();
//
//    /**
//     * 获取图片地址
//     * @return
//     */
//    public String getPath();
//    /**
//     * 获取图片版本
//     * @return
//     */
//    public String getVersion(int i);


//
//    /**
//     * 获取展示长度
//     * @return
//     */
//    public String getTime(int i);
//
//    /**
//     * 获取展示开始日期
//     * @return
//     */
//    public String getData(int i);
//
//    /**
//     * 获取结束日期
//     * @return
//     */
//    public String endData(int i);
}
