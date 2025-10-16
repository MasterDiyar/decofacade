package com.decofacade.decorator;

import com.decofacade.course.*;

public class GamificationDecorator implements Course {
    private Course course;
    private User user;
    private String type ="";

    public GamificationDecorator(Course course, User user) {
        this.course = course;
        this.user = user;

        if (course instanceof CourseBuilder builder) {
            type = builder.GetPath();
        }
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

        var what = course.NextPage();
        System.out.println("You gain: 5 points.");
        user.grades.put(type, user.grades.get(type) + 5);
        return what;
    }

    @Override
    public boolean PreviousPage() {
        return course.PreviousPage();
    }

    @Override
    public void Test(String text) {
        course.Test(text);
    }

    @Override
    public int getCurrentPage(){
        return course.getCurrentPage();
    }
}
