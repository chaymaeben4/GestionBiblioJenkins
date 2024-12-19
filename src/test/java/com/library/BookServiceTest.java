package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookDAO bookDAO;  // Simule l'accès à la base de données via DAO

    @InjectMocks
    private BookService bookService;  // Injecte automatiquement le mock DAO dans le service

    @BeforeEach
    void setUp() {
        // Initialisation des mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book(10, "Java Programming", "John Doe", "Publisher1", "ISBN12", 2002);

        // Simule l'ajout d'un livre dans la base de données
        doNothing().when(bookDAO).add(book);

        // Appelle la méthode à tester
        bookService.addBook(book);

        // Vérifie que le DAO a bien appelé la méthode add
        verify(bookDAO, times(1)).add(book);
    }

    @Test
    void testUpdateBook() {
        Book updatedBook = new Book(20, "Advanced Java", "Jane Doe", "Publisher2", "ISBN22", 2002);

        // Simule la mise à jour d'un livre
        when(bookDAO.update(updatedBook)).thenReturn("Book updated successfully!");

        // Appelle la méthode à tester
        bookService.updateBook(updatedBook);

        // Vérifie que le DAO a bien mis à jour le livre
        verify(bookDAO, times(1)).update(updatedBook);
    }

    @Test
    void testDeleteBook() {
        int bookId = 10;

        // Simule la suppression d'un livre
        when(bookDAO.delete(bookId)).thenReturn("Book successfully deleted!");

        // Appelle la méthode à tester
        bookService.deleteBook(bookId);

        // Vérifie que le DAO a bien supprimé le livre
        verify(bookDAO, times(1)).delete(bookId);
    }

    @Test
    void testFindBookById() {
        int bookId = 10;
        Book book = new Book(10, "Java Programming", "John Doe", "Publisher1", "ISBN12", 2002);

        // Simule la recherche d'un livre par ID
        when(bookDAO.getBookById(bookId)).thenReturn(book);

        // Appelle la méthode à tester
        Book foundBook = bookService.findBookById(bookId);

        // Vérifie que le livre est bien celui attendu
        assertEquals(book, foundBook);
        verify(bookDAO, times(1)).getBookById(bookId);
    }

    @Test
    void testDisplayBooks() {
        Book book1 = new Book(10, "Java Programming", "John Doe", "Publisher1", "ISBN12", 2002);
        Book book2 = new Book(20, "Advanced Java", "Jane Doe", "Publisher2", "ISBN22", 2002);

        // Simule la récupération de tous les livres
        when(bookDAO.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        // Appelle la méthode à tester
        assertEquals(2, bookService.displayBooks().size());
        verify(bookDAO, times(1)).getAllBooks();
    }

    @Test
    void testTruncateBooks() {
        // Simule la suppression de tous les livres
        when(bookDAO.truncate()).thenReturn("All books are deleted successfully !");

        // Appelle la méthode à tester
        String result = bookService.truncate();

        // Vérifie que la méthode truncate du DAO a été appelée
        verify(bookDAO, times(1)).truncate();
        assertEquals("All books are deleted successfully !", result);
    }
}
