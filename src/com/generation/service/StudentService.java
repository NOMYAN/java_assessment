package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public boolean isSubscribed( String studentId )
    {
        //TODO implement this method
        if ( students.containsKey( studentId ) ) {
            return true;
        }
        return false;
    }

    public void showSummary() {
        //TODO implement
        //What should I display for the students in this system
        //In line 11, StudentService, all student data is stored in students(HashMap)
        //In Student, line 15 stores the courses that a student is enrolled into(ArrayList)
        //In Student, line 17 stores the approved courses that a student is enrolled into(HashMap)
        //What can I display for the students in this system when showSummary()

        //For each student, show the student's details (id, name, email)
        //On top of that, show the course(s) that each student is taking


        System.out.println("Existing students:");
        for (String key : students.keySet()) {

            //For each student, show the student's details(id, name, email)
            Student student = students.get(key);
            System.out.println(student);

            //On top of that, show the course(s)that each student is taking
            System.out.println("Courses taken by" + student.getId());
            for (Course course : student.getCourses()) {
                System.out.println(course + " grade=" +student.getGrade(course.getCode()));
            }
        }
    }

    public boolean enrollToCourse(String studentId, Course course )
    {
        boolean status = false;
        if ( students.containsKey( studentId ) )
        {
            status = students.get( studentId ).enrollToCourse( course );
            students.get( studentId ).registerApprovedCourse( course );
        }
        return status;
    }


}
