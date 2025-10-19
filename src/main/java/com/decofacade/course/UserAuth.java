package com.decofacade.course;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;

public class UserAuth {

    public Scanner sc = new Scanner(System.in);
    static void print(String n){
        System.out.println(n);
    }

    public UserAuth() {
        String np="";
        int a=-1;

        while (a !=0 && a!= 1 ) {
            print("0 - register, 1 - login");
            a = sc.nextInt();
            sc.nextLine();
        }

        while (np.split(" ").length !=2){
            print("Write name and password");
            np = sc.nextLine();
        }
        if (a == 0){
            while (!new File("src/main/java/com/decofacade/users/"+np.split(" ")[0]+".txt").exists()){
                print("this person already exists");
                print("Write name and password");
                np = sc.nextLine();
            }
            Register(np.split(" ")[0], np.split(" ")[1]);
        }
        else if (a == 1){
            Login(np.split(" ")[0], np.split(" ")[1]);
        }
        else print("HOW?????");

    }

    public void Register(String name, String password){
        File f = new File("src/main/java/com/decofacade/users/"+name+".txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(name+"\n"+password);
            print("Welcome "+name+"!"+"\n you registered successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Login(String n, String p){
        File f = new File("src/main/java/com/decofacade/users/"+n+".txt");
        if (f.exists()){
            Scanner sp = new Scanner(f);
            int i = 0;
            while (sp.hasNextLine()){
                i++;
                String nl = sp.nextLine();
                if (i ==1 && Objects.equals(nl, p)){
                    print("User Logged Successfully");
                }


            }
        }
    }

    public User CreateUser(String username, String password) {
        User user = new User();


        return user;
    }
}
