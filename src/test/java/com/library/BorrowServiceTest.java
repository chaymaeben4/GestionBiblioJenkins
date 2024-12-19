package com.library;


import com.library.dao.BorrowDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BorrowServiceTest {

    @InjectMocks
    private BorrowService borrowService;

    @Mock
    private BorrowDAO borrowDAO;

    private Borrow borrow;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Création d'un objet Borrow de test
        Student student = new Student(1, "John Doe", "john@example.com");
        Book book = new Book(1, "Effective Java", "Joshua Bloch", "Programming","isbn",200);
        borrow = new Borrow(1, student, book, new Date(Calendar.getInstance().getTimeInMillis()), new Date(Calendar.getInstance().getTimeInMillis()));
    }

    @Test
    public void testBorrowBookSuccess() {
        // Configuration du mock pour retourner une chaîne de succès
        when(borrowDAO.addBorrow(any(Borrow.class))).thenReturn("book borrowed successfully!");

        // Appel de la méthode à tester
        String result = borrowService.borrowBook(borrow);

        // Vérification du résultat
        assertEquals("book borrowed successfully!", result);

        // Vérification que la méthode du DAO a été appelée une fois
        verify(borrowDAO, times(1)).addBorrow(any(Borrow.class));
    }

    @Test
    public void testBorrowBookFailure() {
        // Configuration du mock pour simuler une erreur
        when(borrowDAO.addBorrow(any(Borrow.class))).thenReturn("Error while borrowing the book !");

        // Appel de la méthode à tester
        String result = borrowService.borrowBook(borrow);

        // Vérification du résultat
        assertEquals("Error while borrowing the book !", result);

        // Vérification que la méthode du DAO a été appelée une fois
        verify(borrowDAO, times(1)).addBorrow(any(Borrow.class));
    }

    @Test
    public void testDeleteBorrowSuccess() {
        // Configuration du mock pour retourner une chaîne de succès
        when(borrowDAO.deleteBorrow(anyInt())).thenReturn("book returned successfully!");

        // Appel de la méthode à tester
        String result = borrowService.deleteBorrow(1);

        // Vérification du résultat
        assertEquals("book returned successfully!", result);

        // Vérification que la méthode du DAO a été appelée une fois
        verify(borrowDAO, times(1)).deleteBorrow(anyInt());
    }

    @Test
    public void testDeleteBorrowFailure() {
        // Configuration du mock pour simuler une erreur
        when(borrowDAO.deleteBorrow(anyInt())).thenReturn("Error during book return !");

        // Appel de la méthode à tester
        String result = borrowService.deleteBorrow(1);

        // Vérification du résultat
        assertEquals("Error during book return !", result);

        // Vérification que la méthode du DAO a été appelée une fois
        verify(borrowDAO, times(1)).deleteBorrow(anyInt());
    }
}
