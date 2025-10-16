package com.decofacade.course;

import com.decofacade.cli.CleanCode;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class User {
    public int Points = 0;
    public List<String> Finished = new ArrayList<String>();

    public Dictionary<String, Integer> grades = new Hashtable<String, Integer>(){};

    public User(){
        CleanCode.LoadCourses(grades);

    }

}
