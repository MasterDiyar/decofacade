package com.decofacade.decorator;

import com.decofacade.course.Course;
import com.decofacade.course.CourseFactory;

import java.io.File;

public class MentorSupportDecorator implements Course {

    Course course;

    public MentorSupportDecorator(Course course) {
        this.course = course;
        if (course instanceof CourseFactory builder) {
            var type = builder.GetPath()+".txt";
            Load(type);
        }
    }

    @Override
    public Course Load(String path) {
        System.out.println("Loading " + path + "\n*****\n*****\n*****\n*****\n*****");
        File file = new File("src/main/java/com/decofacade/courseFile/mentor/"+path);
        if (file.exists()) {
            System.out.println("File " + path + " already exists");
            course.Load("mentor/" + path);
        }
        else
            course.Load(path);
        return this;
    }

    @Override
    public void TextFromPage(int page) {
        course.TextFromPage(page);
    }

    @Override
    public boolean NextPage() {
        return course.NextPage();
    }

    @Override
    public boolean PreviousPage() {
        return course.PreviousPage();
    }

    @Override
    public void Test(String ans) {
        course.Test(ans);
    }

    public int getCurrentPage() {
        return course.getCurrentPage();
    }
}
