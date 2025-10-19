package com.decofacade.course;

import com.decofacade.cli.CleanCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class User {
    public int Points = 0;
    public List<String> Finished = new ArrayList<String>();

    public String name, password;

    public Dictionary<String, Integer> grades = new Hashtable<String, Integer>(){};

    public User(){
        CleanCode.LoadCourses(grades);

    }

    public void ShowScore(){
        var keys = grades.keys();

        while (keys.hasMoreElements()) {
            String course = keys.nextElement();
            int grade = grades.get(course);
            System.out.println("Course: " + course + ", Your grade: " + grade);
        }
    }

    public void SaveUser(){
        if (name == null || password == null) {
            System.out.println("User not initialized. Cannot save.");
            return;
        }

        File f = new File("src/main/java/com/decofacade/users/" + name + ".txt");
        f.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(f)) {
            fw.write(name + "\n" + password + "\n");
            var keys = grades.keys();
            while (keys.hasMoreElements()) {
                String course = keys.nextElement();
                int grade = grades.get(course);
                fw.write(course + " " + grade + "\n");
            }
            System.out.println("User data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

}
