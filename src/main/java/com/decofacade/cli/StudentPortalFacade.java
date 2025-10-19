package com.decofacade.cli;

import com.decofacade.course.*;
import com.decofacade.decorator.CertificateDecorator;
import com.decofacade.decorator.GamificationDecorator;
import com.decofacade.decorator.MentorSupportDecorator;

import java.util.Scanner;

public class StudentPortalFacade {


    public static final Scanner scanner = new Scanner(System.in);
    private final User currentUser =  new User();
    String type;
    Course currentCourse, msd;

    boolean work = true;


    static void print(String thing){System.out.println(thing);}


    public StudentPortalFacade() {
        initialize();
    }

    void initialize(){
        var fPath = "src/main/java/com/decofacade/courseFile";

        print(CleanCode.textEN[0]);
        print(CleanCode.listTxtFiles(fPath));

        while(true){
            try{
                var onetime = scanner.nextLine();

                EnrollInCourse(CleanCode.CB(fPath, onetime), CleanCode.CB(fPath, onetime));
                break;}
            catch(Exception e){System.out.println("Enter number");}
        }




        while (work) {
            startLearning();
        }
    }

    public void EnrollInCourse(Course course, Course ment){
        currentCourse = course;

        msd = new MentorSupportDecorator(ment);

    }

    public void startLearning(){
        GamificationDecorator gd = new GamificationDecorator(currentCourse, currentUser);

        boolean lastPage = true;
        currentCourse.TextFromPage(0);

        while(lastPage){
            print("");
            print(CleanCode.textEN[2]);
            String txt = scanner.nextLine();

            switch (txt) {
                case "next" -> lastPage = !gd.NextPage();
                case "back" -> gd.PreviousPage();
                case "help" -> msd.TextFromPage(gd.getCurrentPage());
                case "exit" -> lastPage = false;
            }
        }
        completeCourse();
    }

    public void completeCourse () {
        CertificateDecorator cd = new CertificateDecorator(currentCourse, currentUser);

        cd.TextFromPage(cd.getCurrentPage());
        cd.NextPage();

        print("");
        currentUser.ShowScore();
        print("");

        print(CleanCode.textEN[3]);




        work = false;
        if (scanner.nextLine().equals("Y")) {

            initialize();
        }


    }

}
