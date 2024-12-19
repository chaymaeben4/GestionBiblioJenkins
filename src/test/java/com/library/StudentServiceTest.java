package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    public void setUp() {
        studentDAO = Mockito.mock(StudentDAO.class);  // Mock du DAO
        studentService = new StudentService(studentDAO);  // Injection du mock dans le service
    }

    @Test
    public void testAddStudent() {
        Student student = new Student(1, "John Doe", "john.doe@example.com");

        doNothing().when(studentDAO).addStudent(student);  // Simulation de l'ajout sans exception

        studentService.addStudent(student);

        verify(studentDAO, times(1)).addStudent(student);  // Vérification que la méthode addStudent est bien appelée
    }

    @Test
    public void testFindStudentById() {
        Student student = new Student(1, "John Doe", "john.doe@example.com");

        when(studentDAO.getStudentById(1)).thenReturn(student);  // Simulation du retour de l'étudiant

        Student foundStudent = studentService.findStudentById(1);

        assertNotNull(foundStudent);  // Vérification que l'étudiant est trouvé
        assertEquals(1, foundStudent.getId());
        assertEquals("John Doe", foundStudent.getName());

        verify(studentDAO, times(1)).getStudentById(1);  // Vérification que la méthode getStudentById est appelée une seule fois
    }

    @Test
    public void testDisplayStudents() {
        List<Student> mockStudents = Arrays.asList(
                new Student(1, "John Doe", "john.doe@example.com"),
                new Student(2, "Jane Doe", "jane.doe@example.com")
        );

        when(studentDAO.getAllStudents()).thenReturn(mockStudents);  // Simulation de la liste d'étudiants

        studentService.displayStudents();

        verify(studentDAO, times(1)).getAllStudents();  // Vérification que la méthode getAllStudents est appelée une seule fois
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student(1, "John Doe", "john.doe@example.com");

        doNothing().when(studentDAO).updateStudent(student);  // Simulation de la mise à jour sans exception

        studentService.updateStudent(student);

        verify(studentDAO, times(1)).updateStudent(student);  // Vérification que la méthode updateStudent est bien appelée
    }

    @Test
    public void testRemoveStudent() {
        doNothing().when(studentDAO).deleteStudent(1);  // Simulation de la suppression sans exception

        studentService.removeStudent(1);

        verify(studentDAO, times(1)).deleteStudent(1);  // Vérification que la méthode deleteStudent est bien appelée
    }

    @Test
    public void testTruncate() {
        doNothing().when(studentDAO).truncate();  // Simulation de la suppression de tous les étudiants

        studentService.truncate();

        verify(studentDAO, times(1)).truncate();  // Vérification que la méthode truncate est bien appelée
    }
}
