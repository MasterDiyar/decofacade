package com.decofacade.course;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class User {
    public int Points = 0;
    public List<String> Finished = new ArrayList<String>();

    public Dictionary<String, Integer> grades = new Hashtable<String, Integer>(){};

    public User(){
        grades.put("Math", 0);
        grades.put("Programming", 0);

    }

}
