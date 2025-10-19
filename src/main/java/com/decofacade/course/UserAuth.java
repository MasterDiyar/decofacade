package com.decofacade.course;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserAuth {

    public User thisUser;

    public Scanner sc = new Scanner(System.in);
    static void print(String n){
        System.out.println(n);
    }

    public UserAuth() {
        String np="";
        int a=-1;

        while (a != 0 && a != 1) {
            print("0 - register, 1 - login");
            if (sc.hasNextInt()) {
                a = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
            }
        }

        while (np.split(" ").length !=2){
            print("Write name and password");
            np = sc.nextLine();
        }

        String name = np.split(" ")[0];
        String password = np.split(" ")[1];

        if (a == 0){
            if (new File("src/main/java/com/decofacade/users/"+name+".txt").exists()){
                print("this person already exists");
            }
            else thisUser = Register(name, password);
        }
        else if (a == 1)
            thisUser = Login(name, password);
        else print("HOW?????");

    }

    public User Register(String name, String password){
        File f = new File("src/main/java/com/decofacade/users/"+name+".txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(name+"\n"+password+"\n");
            print("Welcome "+name+"!"+"\nyou registered successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
        User r  = new User();
        r.name=name;
        r.password=password;
        return r;
    }

    public User Login(String name, String password){
        File f = new File("src/main/java/com/decofacade/users/"+name+".txt");
        List<String > Userthings =new ArrayList<String>();

        if (!f.exists()) {
            print("User not found. Please register first.");
            return null;
        }

        try    (Scanner sp = new Scanner(f)) {
            while (sp.hasNextLine()) {
                Userthings.add(sp.nextLine());
            }
        } catch (IOException e) {
            print("File Not Found");
            return null;
        }

        if (Userthings.size() < 2){
            print("File corrupted lol");
            return null;
        }

        String sName = Userthings.get(0),
            sPassword = Userthings.get(1);

        if (Objects.equals(sName, name) && Objects.equals(sPassword, password)) {
            print("User logged in successfully!");
            return CreateUser(Userthings);
        } else {
            print("Incorrect username or password.");
            return null;
        }
    }

    public User CreateUser(List<String> Userthings) {
        User user = new User();

        user.name = Userthings.get(0);
        user.password = Userthings.get(1);

        for (int i = 2; i < Userthings.size(); i++) {
            String[] parts = Userthings.get(i).split(" ");
            if (parts.length == 2)user.grades.put(parts[0], Integer.parseInt(parts[1]));
        }

        return user;
    }
}
