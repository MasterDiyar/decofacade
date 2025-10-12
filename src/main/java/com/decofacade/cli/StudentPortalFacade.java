package com.decofacade.cli;

import com.decofacade.course.Course;
import com.decofacade.course.MathCourse;
import com.decofacade.course.ProgrammingCourse;
import com.decofacade.course.User;
import com.decofacade.decorator.CertificateDecorator;
import com.decofacade.decorator.GamificationDecorator;
import com.decofacade.decorator.MentorSupportDecorator;

import java.util.Scanner;

public class StudentPortalFacade {

    Course currentCourse, msd;
    User currentUser;
    String type;

    static void print(String thing){System.out.println(thing);}

    public static Scanner scanner = new Scanner(System.in);

    public StudentPortalFacade() {
        currentUser = new User();

        print(CleanCode.textEN[0]);

        var onetime = scanner.nextLine();
        if(onetime.equals("math")) {
            EnrollInCourse(new MathCourse(), new MathCourse(), "math.txt");
        }
        else if(onetime.equals("programing")) {
            EnrollInCourse(new ProgrammingCourse(), new ProgrammingCourse(),"programming.txt");
        }

        while (true) {
            print(CleanCode.textEN[1]);
            var txt = scanner.nextLine();

            if (txt.equals("start")) {
                startLearning(); break;
            }
        }
    }

    public void EnrollInCourse(Course course, Course ment, String path){
        currentCourse = course;
        currentCourse.Load(path);

        msd = new MentorSupportDecorator(ment).Load(path);
    }

    public void startLearning(){
        GamificationDecorator gd = new GamificationDecorator(currentCourse, currentUser);
        String txt;

        if (currentCourse instanceof MathCourse) {type = "Math";}
        else if (currentCourse instanceof ProgrammingCourse) {type = "Programming";}

        currentCourse.TextFromPage(0);

        while(currentUser.grades.get(type) < 30){
            print("");
            print(CleanCode.textEN[2]);
            txt = scanner.nextLine();

            switch (txt) {
                case "next":
                    gd.NextPage();
                    break;
                case "back":
                    gd.PreviousPage();
                    break;
                case "help":
                    msd.TextFromPage(gd.getCurrentPage());
                    break;
            }
        }
        completeCourse();
    }

    public void completeCourse () {
        CertificateDecorator cd = new CertificateDecorator(currentCourse, currentUser);

        cd.TextFromPage(cd.getCurrentPage());
        cd.NextPage();

    }

}
