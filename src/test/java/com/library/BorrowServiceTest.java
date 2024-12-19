package com.library;


import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BorrowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.service.BookService;
import com.library.service.StudentService;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {
    private BorrowService borrowService;
    private BookService bookService;
    private StudentService studentService;
    private Student student;
    private Book book;
    private  Date borrowDate,returnDate;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        studentService = new StudentService();
        borrowService = new BorrowService();
        borrowService.truncate();
        bookService.truncate();
        studentService.truncate();


        student=  new Student(5, "Alice", "alice@example.com");
        book=  new Book(5, "Java Programming", "John Doe","Publisher2", "ISBN22",2002);

        borrowDate = new Date(System.currentTimeMillis());
        returnDate = new Date(System.currentTimeMillis() + 604800000);

        studentService.addStudent(student);
        bookService.addBook(book);

    }

    @Test
    void testBorrowBook() {

        Borrow borrow = new Borrow(1, student, book, borrowDate, returnDate);
        assertEquals("book borrowed successfully!", borrowService.borrowBook(borrow));
    }

    @Test
    void testReturnBook() {
        assertEquals("book returned successfully!", borrowService.deleteBorrow(1));
    }


    @Test
    void testBorrowBookStudentNotFound() {
        student.setId(9999);
        Borrow borrow = new Borrow(7,student, book, borrowDate, returnDate);
        assertEquals("student or book not found.", borrowService.borrowBook(borrow));

    }
}