
package com.library.dao;

import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.StudentService;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    public List<Borrow> getAllBorrows() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        BookDAO bookDAO = new BookDAO();
        List<Borrow> borrows = new ArrayList<Borrow>();
        String query = "SELECT * FROM borrows";
        Student student;
        Book book;
        try ( Statement stmt = DbConnection.getConnection().createStatement();
              ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                student = studentDAO.getStudentById(rs.getInt("member"));

                book = bookDAO.getBookById(rs.getInt("book"));
                Borrow borrow = new Borrow(
                        rs.getInt("id"),
                        student,
                        book,
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date"));
                borrows.add(borrow);
            }
            return borrows;
        } catch (SQLException e) {
            System.err.println("Error during fetching borrows: " + e.getMessage());
        }
        return null;
    }

    public String  save(Borrow borrow) {
        String query = "UPDATE borrows SET member = ?, book = ?, borrow_date = ?, return_date = ? WHERE id = ?";
        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, borrow.getStudent().getId());
            stmt.setInt(2, borrow.getBook().getId());
            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));
            stmt.setInt(5, borrow.getId());
            stmt.executeUpdate();
            return "book borrowed successfully!";

        } catch (SQLException e) {
            System.err.println("Error during borrow's update : " + e.getMessage());
            return "Error";
        }
    }



    public String addBorrow(Borrow borrow) {
        String query = "INSERT INTO borrows(id,student_id, book_id, borrowDate, returnDate) VALUES (?, ?, ?, ?,?)";
        if(new StudentService().findStudentById(borrow.getStudent().getId()) == null || new BookDAO().getBookById(borrow.getBook().getId()) == null){
            return "student or book not found.";
        }
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1,borrow.getId());
            stmt.setInt(2, borrow.getStudent().getId());
            stmt.setInt(3, borrow.getBook().getId());
            stmt.setDate(4, new java.sql.Date(borrow.getBorrowDate().getTime()));
            stmt.setDate(5, new java.sql.Date(borrow.getReturnDate().getTime()));
            stmt.executeUpdate();
            return "book borrowed successfully!";
        } catch (SQLException e) {
            System.out.println(e);
            return "Error while borrowing the book !";
        }
    }
    public String deleteBorrow(int id) {
        String query = "DELETE FROM borrows WHERE id = ?";
        try (PreparedStatement stmt = DbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "book returned successfully!";
        } catch (SQLException e) {
            System.out.println(e);
            return "Error during book return !";

        }
    }
    public String truncate() {
        String query = "DELETE FROM borrows";
        try (Statement stmt = DbConnection.getConnection().createStatement()) {
            stmt.executeUpdate(query);
            return "All the books are returned successfully!";
        } catch (SQLException e) {
            return "Error during books return !";
        }
    }
}
