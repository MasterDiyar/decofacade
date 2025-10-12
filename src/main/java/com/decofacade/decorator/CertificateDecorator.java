package com.decofacade.decorator;

import com.decofacade.course.Course;
import com.decofacade.course.User;

public class CertificateDecorator implements Course {
    Course course;
    User user;
    public CertificateDecorator(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    @Override
    public Course Load(String path) {
        course.Load(path);
        return this;
    }

    @Override
    public void TextFromPage(int page) {
        course.TextFromPage(page);
    }

    @Override
    public boolean NextPage() {
        var end = course.NextPage();
        if (end) {
            System.out.println("Congratulations! You have successfully end!");
            user.Finished.add(course.getClass().getName());
        }
        return end;
    }

    @Override
    public boolean PreviousPage() {
        return course.PreviousPage();
    }

    @Override
    public void Test(String ans) {
        course.Test(ans);
    }

    @Override
    public int getCurrentPage() {
        return course.getCurrentPage();
    }
}
