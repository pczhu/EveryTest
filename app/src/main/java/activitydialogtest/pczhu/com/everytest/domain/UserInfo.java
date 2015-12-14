package activitydialogtest.pczhu.com.everytest.domain;

import java.io.Serializable;

/**
 * 名称：EveryTest
 * 作用：
 * 描述：
 * 作者：pczhu
 * 创建时间： 15/12/14 上午10:54
 * 版本：V1.0
 * 修改历史：
 */
public class UserInfo implements Serializable {

    private String id;
    private String name;
    private String headpic;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", headpic='" + headpic + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
