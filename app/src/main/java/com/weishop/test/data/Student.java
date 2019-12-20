package com.weishop.test.data;

import com.weishop.test.util.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 16/12/15.
 */

public class Student {
    private String name;
    private int id;
    private List<Course> courses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        String info = name + ":";
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            info += info + course.getCourseName() + ",";
        }
        return info;
    }

    public static Generator<Student> generator =
            new Generator<Student>() {
                public Student next() {
                    return new Student();
                }
            };
}
