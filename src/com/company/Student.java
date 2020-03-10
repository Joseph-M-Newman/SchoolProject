package com.company;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    int ID;
    int Score = 0;
    List<Integer> CurrentCourses = new ArrayList<>();

    Student(String ID, String name){
    this.ID = Integer.parseInt(ID);
    this.name = name;
    }

    @Override
    public String toString(){
        return this.ID + "\n" + this.name + "\n" + Courses();
    }

    public String Courses(){
        String s = "Courses: ";
        for (int i =0; i < CurrentCourses.size(); i++){
            s+= CurrentCourses.get(i);
        }
        return s;
    }
}
