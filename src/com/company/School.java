package com.company;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class School {
    public String SchoolName;

    ArrayList<com.company.Course> Course = new ArrayList<>();
    ArrayList<com.company.Teacher> Teacher = new ArrayList<>();
    ArrayList<com.company.Student> StudentP = new ArrayList<>();


    School(String nameofSchool) {
        this.SchoolName = nameofSchool;
    }


    /**
     * Read in the file contents and assign a NEW OBJECT according to the "object"
     * School, Course,Teacher;
     *
     * @param dataFile Data text file location
     */
    public void readData(String dataFile) {


        try {
            String tempFile = "";
            FileReader file = new FileReader(dataFile);
            Scanner scan = new Scanner(file);
            int p, m, k;


            String s1;
            String sArry1[];
            p = scan.nextInt();
            scan.nextLine();

            for (int i = 0; i < p; i++) {
                s1 = scan.nextLine();
                sArry1 = s1.split(",");
                Teacher.add(new Teacher(sArry1[0], sArry1[1], sArry1[2], sArry1[3]));
            }

            String s2;
            String sArry2[];
            m = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < m; i++) {
                s2 = scan.nextLine();
                sArry2 = s2.split(",");
                Course.add(new Course(sArry2[0], sArry2[1], sArry2[2], sArry2[3]));
            }


            String s3;
            String sArry3[];
            //brings the scan down to the next INT;
            k = scan.nextInt();
            // set the line @ the next Line
            scan.nextLine();

            for (int i = 0; i < k; i++) {
                s3 = scan.nextLine();
                sArry3 = s3.split(",");
               int idCheck = Integer.parseInt(sArry3[0]);

               if(!StudentP.isEmpty() && i < StudentP.size()) {
                   if (StudentP.get(i).ID == idCheck) {
                       System.out.println("Student Id of " + idCheck + " already registered.");
                   }
               } else {
                   StudentP.add(new Student(sArry3[0], sArry3[1]));
               }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found..." + e);
        } catch (NoSuchElementException e) {
            System.out.println("Line not found..." + e);
        }


    }

    /**
     * Display the course information, all information in the system;
     */
    public void schoolInfo() {
        for (int i = 0; i < Teacher.size(); i++) {
            System.out.println(Teacher.get(i).toString());
        }
        for (int i = 0; i < Course.size(); i++) {
            System.out.println(Course.get(i).toString());
        }
        for (int i = 0; i < StudentP.size(); i++) {
            System.out.println(StudentP.get(i).toString());
        }

    }

    /**
     * @param search by email
     * @return return email
     */
    public String searchByEmail(String search) {
        for (int i = 0; i < Teacher.size(); i++) {
            if (Teacher.get(i).Email.equals(search)) {
                return Teacher.get(i).toString();
            }
        }
        return "Invalid email";
    }

    /**
     * @param CourseId Delete by Course#
     * @return return If sucessful or not
     */
    public String deleteCourse(int CourseId) {
        for (int i = 0; i < Course.size(); i++) {
            if(Course.get(i).Values.get(i) == null){
            if (Course.get(i).courseId == CourseId) {
                Course.remove(i);
                return "Course has been deleted!";

                }
            }else if (Course.get(i).Values.get(i) != null){
                return "Students enrolled in class, Cannot delete";
            }
        }
        return " Course doesnt Exist";
    }

    /**
     * Add course to school system
     *
     * @param ID             Course ID
     * @param CourseName     Course Name
     * @param Studentsnumber Students enrolled
     * @param RoomN          Room number
     * @return return if the course exists or if its been added
     */
    public String addCourse(String ID, String CourseName, String Studentsnumber, String RoomN) {
        int id = Integer.parseInt(ID);

        for (int i = 0; i < Course.size(); i++) {
            if (Course.get(i).courseId == id) {
                return "Course Already exists!";
            }
        }
        Course.add(new Course(ID, CourseName, Studentsnumber, RoomN));
        int s = Course.size();
        System.out.println(Course.get(s-1) + " entered into the system...");
        return "Course Added!";

    }

    /**
     * Add teacher into the system;
     * @param CourseID    Course Id
     * @param Name        Name of instructor;
     * @param email       - email
     * @param phoneNumber - Phone Number
     * @return return confirmations
     */
    public String addInstructor(String CourseID, String Name, String email, String phoneNumber) {
        for (int i = 0; i < Teacher.size(); i++) {
            if (Teacher.get(i).Id == Integer.parseInt(CourseID)) {
                return "Teacher of CourseID" + CourseID + " Already exists";
            }
        }
        Teacher.add(new Teacher(CourseID, Name, email, phoneNumber));
        int s = Teacher.size();
        System.out.println(Teacher.get(s-1) + " entered into the system...");

        return "Teacher has been added to System!";
    }

    public void addStudent(int studentId, String name){
        for(int i = 0; i < StudentP.size(); i++){
            if(StudentP.get(i).ID == studentId){
                System.out.println("Student of ID " + studentId + " is already in the system");
            }
        }
        String s = Integer.toString(studentId);
        StudentP.add(new Student(s,name));
    }

    /**
     * Assign a instructor to a Course;
     *
     * @param CourseId     Course @
     * @param InstructorID Instructor ID
     * @return return Instructor assigned or unassigned;
     */
    public String assignInstructor(int CourseId, int InstructorID) {

        for(int i = 0; i < Teacher.size(); i++){
            if(Teacher.get(i).Id == InstructorID){
                Teacher.get(i).assignInstructor(CourseId);
                if(Teacher.get(i).Courses.get(i) == CourseId){
                    Course.get(i).Instructor = InstructorID;
                }
                return "Instructor Assigned to Course!";
            }
        }

        return "Instructor Already Assigned to this Course or no Instructor of that ID!";
    }

    /**
     * Register student into a Course;
     *
     * @param CourseId  Course #
     * @param StudentID Student #
     * @return return if enrolled or not
     */
    public void register(int CourseId, int StudentID) {

        for (int i = 0; i < Course.size(); i++) {
            if (Course.get(i).courseId == CourseId) {
                Course.get(i).Values.add(StudentID);
                Course.get(i).StudentsEnrolled.put(CourseId,Course.get(i).Values);
                StudentP.get(i).CurrentCourses.add(CourseId);
                System.out.println( "Student Registered! " + Course.get(i).StudentsEnrolled.get(CourseId));
            }
        }

        System.out.println("Student already enrolled");
    }

    /**
     * Unregister the student from the class
     *
     * @param courseID  course ID
     * @param StudentID Student ID
     */
    public String unRegister(int courseID, int StudentID) {
        for (int i = 0; i < Course.size(); i++) {
            if (Course.get(i).StudentsEnrolled.containsValue(StudentID)) {
                Course.get(i).StudentsEnrolled.remove(StudentID);
                return "Student has been Removed";
            }
        }

        return "Student isn't in the class!";
    }

    /**
     * @param StudentId StudentID#
     * @param Score     Input score they received;
     * @return return confirmation
     */
    public String putScore(int CourseID, int StudentId, double Score){
        for (int i = 0; i < StudentP.size(); i++) {
           if(Course.get(i).courseId == CourseID){
               Course.get(i).getStudentsEnrolled(StudentId);
               Course.get(i).StudentsScore.put(StudentId,Score);
               Course.get(i).StudentIds[i] = StudentId;
             String d = Double.toString(Course.get(i).getScore(StudentId));
             return d;
           }
        }
        return "Student not registered in Class!";
    }


        /**
         * Retrieve course based off ID Parameter.
         * @param CourseId Course ID
         * @return return information off Course ID
         */
        public String courseInfo ( int CourseId){
            for (int i = 0; i < Course.size(); i++) {
                if (Course.get(i).courseId == CourseId) {
                    return Course.get(i).toString();
                }
            }
            return "Course Doesnt Exist";
        }

    /**
     * A non Parameter function that displayed all enrolled within the course ID;
     * EX: Course id: 305: 2 enrolled
     *                205: 4 enrolled
     *                101: 0 enrolled
     */
    public void courseInfo(){
            for (int i = 0; i < Course.size(); i++){
                System.out.println(Course.get(i).courseId + ": " + Course.get(i).Values.size() + " enrolled");
            }
        }

        /**
         * Get toString from Student object
         * @param ID Student ID
         * @return return Student Information
         */
        public String getStudent ( int ID){
            for (int i = 0; i < StudentP.size(); i++) {
                if (StudentP.get(i).ID == ID) {
                    return StudentP.get(i).toString();
                }
            }
            return "No Student ID found of " + ID;
        }

    /**
     *
     * @param ID
     * @param CourseID
     * @return
     */
        public String getStudentEnrolled(int ID, int CourseID){
            for (int i = 0; i < Course.size();i ++){
                if(StudentP.get(i).ID == ID){
                    if(Course.get(i).StudentsEnrolled != null){
                        List s = Course.get(i).StudentsEnrolled.get(CourseID);
                        return null;
                    }
                }
            }
            return "null";
        }

    /**
     * Drops all student classes with this Id of StudentId
     * @param StudentId - Student Id number
     */
    public void graduateStudent(int StudentId){
        for(int i = 0; i < Course.size(); i++){
            if(StudentId == StudentP.get(i).ID){
                if(StudentP.get(i).CurrentCourses!= null){
                    StudentP.get(i).CurrentCourses.remove(i);
                }
            if(Course.get(i).Values.get(i) == StudentId){
                Course.get(i).Values.remove(i);
            }
            }
        }

        }
    }

