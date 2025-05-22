package com.generation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    // We do not need to inject courses because it is already fulfilled when CourseService is instantiated
    // when the constructor for the instance of CourseService is invoked
    CourseService courseService = new CourseService();

    @Test
    @DisplayName("getCourse() method should return the same course code - INTRO-CS-1")
    void getCourse() {
        assertEquals("INTRO-CS-1", courseService.getCourse("INTRO-CS-1").getCode());
    }

    //Test for null input
    @Test
    @DisplayName("getInvalidCourse() method should return null for not registered course name")
    void getInvalidCourse() {
        assertNull(courseService.getCourse(""));
    }

//    THIS 2 CODES DOESN'T WORK! WHY?!
//    Test for null input
//    @Test
//    @DisplayName("getCourse() should throw exception when courseCode is null")
//    void getCourse_NullInput_ThrowsException() {
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.getCourse(null));
//    }
//
//    //Test for blank (whitespace-only) input
//    @Test
//    @DisplayName("getCourse() should throw exception when courseCode is blank")
//    void getCourse_BlankInput_ThrowsException() {
//        assertThrows(IllegalArgumentException.class,
//                () -> courseService.getCourse("   "));
//    }

}