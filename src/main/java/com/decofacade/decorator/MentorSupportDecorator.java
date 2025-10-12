package com.decofacade.decorator;

import com.decofacade.course.Course;

public class MentorSupportDecorator implements Course {

    Course course;

    public MentorSupportDecorator(Course course) {
        this.course = course;
    }

    @Override
    public Course Load(String path) {
        course.Load("mentor"+path);
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
