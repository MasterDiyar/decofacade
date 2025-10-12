package com.decofacade.cli;

import com.decofacade.course.Course;
import com.decofacade.course.MathCourse;
import com.decofacade.course.User;
import com.decofacade.decorator.MentorSupportDecorator;

import java.util.Scanner;

public class CommandLineInterface {
    private User user;
    public void Start(User user){
        this.user = user;
        boolean App = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to course enrollment program");
        System.out.println("Please enter your choice:");
        System.out.println("1) Math");
        System.out.println("2) Java Basics");
        while(!App){
            var choice = sc.nextInt();
            switch(choice){
                case 1:
                    Math(sc);
            }

        }
    }

    void Math(Scanner sc) {

        var mentorCourse = new MentorSupportDecorator(new MathCourse().
                Load("math.txt"));
        var course = new MathCourse().
                Load("math.txt");
        course.TextFromPage(0);
        KakoitoWhile:
        while (true) {
            System.out.println(" ");
            System.out.println("enter next to next page");

            var txt = sc.nextLine();

            switch (txt) {
                case "exit":
                    break KakoitoWhile;
                case "next":
                    course.NextPage();
                    break;
                case "back":
                    course.PreviousPage();
                    break;
                case "points":
                    System.out.println("u have " + user.Points + " points,");
                    break;
                case "help":
                    mentorCourse.TextFromPage(course.getCurrentPage());
                    break;
            }
        }
    }
}
