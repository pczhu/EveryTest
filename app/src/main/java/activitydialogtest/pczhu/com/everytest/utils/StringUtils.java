package activitydialogtest.pczhu.com.everytest.utils;

/**
 * 名称：${FILE_NAME}
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/17 上午10:40
 * 版本：V1.0
 * 修改历史：
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if(str == null || ("").equals(str))
            return true;
        else
            return false;
    }
}
