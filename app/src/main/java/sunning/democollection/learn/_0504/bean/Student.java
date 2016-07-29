package sunning.democollection.learn._0504.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 16/5/4.
 */
public class Student {
    public String name;
    public List<Course> courses;


    public static Student create(String flag) {
        Student student = new Student();
        student.name = "王老三" + flag;
        List<Course> courses = new ArrayList();
        courses.add(new Course("语文" + flag));
        courses.add(new Course("数据" + flag));
        courses.add(new Course("计算机" + flag));
        courses.add(new Course("电影艺术" + flag));
        student.courses = courses;
        return student;
    }
}
