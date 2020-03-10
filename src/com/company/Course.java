package com.company;

import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    int courseId;
    String courseName;
    int MaxStudent;
    String RoomID;
    int Instructor;
    Map<Integer,List> StudentsEnrolled = new HashMap<>();
    List<Integer> Values = new ArrayList<>();
    HashMap<Integer,Double> StudentsScore = new HashMap<>();
    int sizes = Values.size();
    int[] StudentIds = new int[2];


    Course(String CourseID, String CourseName ,String MaxStudents,String RoomNumber){
        this.courseId = Integer.parseInt(CourseID);
        this.courseName = CourseName;
        this.MaxStudent = Integer.parseInt(MaxStudents);
        this.RoomID = RoomNumber;
    }
    @Override
    public String toString(){
        return "Course Number: " + courseId + "\n" + "Instructor: " +Instructor+"\n"+courseName + "\n" + RoomID  + "\n" + StudentsEnrolledClass() + ScoreAverage();
    }

    /**
     * Returns Student IDs score within the Class course that has been inputed;
     * ID taken and searches the values of which are held within the Values List, which in turn checks if it is in the courseID. If so
     *      *                  Return that ID search the StudentScore Hashmap for that ID and get Score
     * @param studentId - StudentId#
     * @return Score in HashMap;
     */
    public Double getScore(int studentId){
        for(int i =0; i < Values.size(); i++){
            if(Values.get(i) == studentId){
                return StudentsScore.get(studentId);
            }
        }
        return 0.0;
    }

    /**
     * returns string of total amount of students in class;
     * @return Total amount of Students in the Class
     */
    public String StudentsEnrolledClass(){
        String Students = "Total Enrolled: ";
        Students += Values.size();
        return Students;
    }
    /**
     * Check if StudentId is enrolled in class by search the Values "Student Ids enrolled in course, The key is the course ID, Values list is Student IDS so one key
     * can hold multiple Student Ids"
     * @param studentId - StudentId#
     * @return Sout - that value of the ID;
     */
    public void getStudentsEnrolled(int studentId){

            for(int i =0; i < Values.size(); i++){
                if(Values.get(i) == studentId){
                    System.out.println(Values.get(i));
                }
            }


    }

    /**
     * Calculate the average score of the students in the class
     * @return the average of the students;
     */
    public String ScoreAverage(){
        double size = StudentIds.length;
        double Score[] = new double[(int) size];
        double Average;
        double totalScore = 0;
        for(int i =0; i < Values.size(); i++){
            Score[i] = StudentsScore.get(Values.get(i));
            totalScore += Score[i];
        }

        Average = totalScore/Score.length;

        String TotalEnding = Double.toString(Average);
        return "\n" + "Course Average: " + TotalEnding;
    }


}
