package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Module;
import com.generation.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

//    StudentService studentService = new StudentService();
//
//    @BeforeEach
//    void setUp() {
//        //consider registering students into studentService
//
//    }
//
//    @AfterEach
//    void tearDown() {
//        //remove all students from studentService
//    }
//
//    @Test
//    @DisplayName("method findStudent() should not return any students")
//    void findStudent() {
//        assertEquals(null, studentService.findStudent("S01"));
//    }

    StudentService studentService;
    Student testStudent;
    Course testCourse;
    Module module; //4th parameter in student
    Date birthDate;  //

    @BeforeEach
    void setUp() {
        studentService = new StudentService();

        testStudent = new Student("S01", "Test Student", "test@uni.edu", birthDate);
        testCourse = new Course("INTRO-CS-1", "Introduction to Computer Science", 9, module);

        // Register a test student before each test
        studentService.subscribeStudent(testStudent);
    }

    @AfterEach
    void tearDown() {
        // Clear all students after each test
        // Note: Since StudentService uses a HashMap that's recreated for each test,
        // this isn't strictly necessary but good practice for future modifications
        studentService = null;
    }

    @Test
    @DisplayName("findStudent() should return null for unknown student")
    void findStudentUnknown() {
        assertNull(studentService.findStudent("UNKNOWN_ID"));
    }

    @Test
    @DisplayName("findStudent() should return correct student for known ID")
    void findStudentKnown() {
        Student found = studentService.findStudent("S01");
        assertNotNull(found);
        assertEquals("Test Student", found.getName());
    }

    @Test
    @DisplayName("isSubscribed() should return true for subscribed student")
    void isSubscribedPositive() {
        assertTrue(studentService.isSubscribed("S01"));
    }

    @Test
    @DisplayName("isSubscribed() should return false for unknown student")
    void isSubscribedNegative() {
        assertFalse(studentService.isSubscribed("UNKNOWN_ID"));
    }

    @Test
    @DisplayName("subscribeStudent() should add new student")
    void subscribeNewStudent() {
        Student newStudent = new Student("S02", "New Student", "new@uni.edu", birthDate);
        studentService.subscribeStudent(newStudent);
        assertNotNull(studentService.findStudent("S02"));
    }

    @Test
    @DisplayName("subscribeStudent() should overwrite existing student")
    void subscribeExistingStudent() {
        Student updatedStudent = new Student("S01", "Updated Name", "updated@uni.edu", birthDate);
        studentService.subscribeStudent(updatedStudent);
        assertEquals("Updated Name", studentService.findStudent("S01").getName());
    }

    @Test
    @DisplayName("enrollToCourse() should successfully enroll subscribed student")
    void enrollToCourseSuccess() {
        assertTrue(studentService.enrollToCourse("S01", testCourse));
        assertTrue(testStudent.getCourses().contains(testCourse));
    }

    @Test
    @DisplayName("enrollToCourse() should fail for unknown student")
    void enrollToCourseFailure() {
        assertFalse(studentService.enrollToCourse("UNKNOWN_ID", testCourse));
    }

    @Test
    @DisplayName("showSummary() should display student and course information")
    void showSummary() {
        studentService.enrollToCourse("S01", testCourse);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        studentService.showSummary();

        String output = outContent.toString();
        assertTrue(output.contains("Test Student"));
        assertTrue(output.contains("INTRO-CS-1"));
        System.setOut(System.out); // Reset stdout
    }
}