package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        bookService.truncate();


    }

    @Test
    void testAddBook() {
        Book book = new Book(10, "Java Programming", "John Doe","Publisher1", "ISBN12",2002);
        bookService.addBook(book);
        assertEquals(1, bookService.displayBooks().size());
        assertEquals("Java Programming", bookService.findBookById(10).getTitle());
    }

    @Test
    void testUpdateBook() {
        Book book = new Book(20, "Java Programming", "John Doe","Publisher2", "ISBN22",2002);
        bookService.addBook(book);
        Book book1=new Book(20, "Advanced Java", "Jane Doe", "Publisher2", "ISBN22",2002);
        bookService.updateBook(book1);
        assertEquals("Advanced Java", bookService.findBookById(20).getTitle());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book(10, "Java Programming", "John Doe","publisher5", "ISBN22",2002);
        bookService.addBook(book);
        bookService.deleteBook(10);
        assertNull(bookService.findBookById(10));
    }
}