package activitydialogtest.pczhu.com.everytest.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

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
@Table(name = "parent", onCreated = "CREATE UNIQUE INDEX index_name ON UserInfo(name,email,headpic,age)")
public class UserInfo implements Serializable {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "headpic")
    private String headpic;
    @Column(name = "age")
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
