package com.decofacade.course;

public interface Course {
    String[] Pages = new String[] {};

    Course Load(String path);

    void TextFromPage(int page);

    boolean NextPage();

    boolean PreviousPage();

    void Test(String ans);

    int getCurrentPage();
}
