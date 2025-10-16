package com.decofacade.cli;

import com.decofacade.course.Course;
import com.decofacade.course.CourseBuilder;
import com.decofacade.course.MathCourse;
import com.decofacade.course.ProgrammingCourse;

import java.io.File;
import java.util.Dictionary;

public class CleanCode {
    public static final String[] textEN = new String[]
        {
           "Welcome to the Course Portal! Enter the course you will enroll in:",
           "Write 'start' to begin the course.",
           "Enter 'next', 'back', 'help' or 'exit'",
           "Write 'end' to end the course.",
                "Do you want to continue? Y/N",
        };

    public static String listTxtFiles(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            return "Papki netu";
        }

        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (files == null || files.length == 0) {
            return "bug";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.length; i++) {
            String nameWithoutExt = files[i].getName().replaceFirst("\\.txt$", "");
            sb.append(i).append(" - ").append(nameWithoutExt).append("\n");
        }

        return sb.toString();
    }

    public static String getFileNameByIndex(String folderPath, int index) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files == null || files.length == 0) {
            return "no txt file ";
        }
        if (index < 0 || index >= files.length) {
            return "error index :(";
        }

        return files[index].getName();
    }

    public static Course CB(String folderPath, String onetime) {
        return new CourseBuilder()
                .Load(CleanCode
                        .getFileNameByIndex(folderPath, Integer.parseInt(onetime)));
    }

    public static void LoadCourses(Dictionary<String, Integer> crsr){
        File folder = new File( "src/main/java/com/decofacade/courseFile");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        for (File file : files) {
            var dat = file.getName().replaceFirst("\\.txt$", "");
            System.out.println(dat + " added");
            crsr.put(dat, 0);
        }
    }
}
