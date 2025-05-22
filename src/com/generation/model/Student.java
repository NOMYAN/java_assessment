package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
    extends Person
    implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();

    //Hashmap is blank
    private final Map<String, Course> approvedCourses = new HashMap<>();

    //Create hashmap for passed/failed, and average. Declaration of attributes. private final= memory location
    private final Map<String, Integer> courseCreditsAwarded = new HashMap<>();

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    //Add Getter - to point to private final approvedCourses
    public List<Course> getCourses() {
        return courses;
    }

    //Getter- no use?
    public Map<String, Integer> getCourseCreditsAwarded() {
        return courseCreditsAwarded;
    }

    public boolean enrollToCourse(Course course )
    {
        //TODO DONE! add the student to the attribute "courses"
        //boolean -contains
        if(courses.contains(course)) return false;
        //not to have duplicates(this is why this exists)

        this.courses.add(course);
        registerApprovedCourse(course); //added. After you enroll the person, immediately approved the person
        return true;
    }

    //Add gradeCourse - and change to string
    public String getGrade(String courseCode)
    {
        //TODO -Done?
        //Per course
        if(courseCreditsAwarded.containsKey(courseCode)) {
            return (Integer.toString(courseCreditsAwarded.get(courseCode)));
        }
        return "Not graded";
    }

    //Add gradCourse
    public boolean gradeCourse(String courseCode, Integer grade)
    {
        //TODO -Done?
        if(courseCreditsAwarded.containsKey(courseCode)) {
            return false;
        }
        this.courseCreditsAwarded.put(courseCode, grade);
        return true;
    }

    public void registerApprovedCourse( Course course )
    {
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO DONE. WHAT IS THE COURSE APPROVED.. can return directly, not necessarily need T&F
        return approvedCourses.containsKey( courseCode );
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve. 
    public List<Course> findPassedCourses( Course course )
    {
        //TODO implement this method
        return null;
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //TODO -DONE!
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double getAverage()
    {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO
        return courses;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}
