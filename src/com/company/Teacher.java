package com.company;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    String Name;
    int Id;
    String Email;
    String PhoneNumber;
    List<Integer> Courses = new ArrayList();

    Teacher(String Id,String name,String email,String PhoneNumber){
        this.Name = name;
        this.Id = Integer.parseInt(Id);
        this.Email = email;
        this.PhoneNumber = PhoneNumber;
    }
    @Override
    public String toString(){
        return Name + ", " + Id+ ", " + Email + ", " +PhoneNumber ;
    }

    /**
     * Goes along with assignInstructor in School.java; adds the course to Teacher.java so the object teacher has a record of the class
     * then in School.java it adds the instructors ID to the course Id, in return tagging both the courses and the instructor together;
     * @param Courseid
     */
    public void assignInstructor(int Courseid){
        Courses.add(Courseid);
    }
}
