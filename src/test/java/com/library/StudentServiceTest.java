package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() {
        studentDAO = new StudentDAO();
        studentService = new StudentService(studentDAO);
        studentService.truncate();
    }
    @Test
    void testAddStudent() {
        studentService.addStudent(new Student(1, "Alice","meryam@gmail.com"));
        assertEquals(1, studentDAO.getAllStudents().size());
        assertEquals("Alice", studentDAO.getStudentById(1).getName());
    }
    @Test
    void testUpdateStudent() {
        studentService.addStudent(new Student(6, "Alice","meryam@gmail.com"));
        studentService.updateStudent(new Student(6, "Alice Smith","aliceSmith@gmail.com"));
        assertEquals("Alice Smith", studentDAO.getStudentById(6).getName());
    }
    @Test
    void testDeleteStudent() {
        studentService.addStudent(new Student(7, "Alice","meryam@gmail.com"));
        studentService.removeStudent(7);
        assertNull(studentDAO.getStudentById(7));
    }
    @Test
    void testGetAllStudents() {
        studentService.addStudent(new Student(10, "Alice","meryam@gmail.com"));
        studentService.addStudent(new Student(2, "Bob","bob@gmail.com"));
        assertEquals(2, studentDAO.getAllStudents().size());
    }
}