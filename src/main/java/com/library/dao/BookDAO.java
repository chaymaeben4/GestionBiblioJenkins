package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    

    // Ajouter un nouveau livre dans la base de données
    public void add(Book book) {
        String sql = "INSERT INTO books (id,title, author,publisher, isbn, publishedYear) VALUES (?, ?, ?,?,?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getIsbn());
            statement.setInt(6, book.getPublishedYear());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Livre inséré avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    // Récupérer un livre par son ISBN
    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        Book book = null;
        
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAuthor(resultSet.getString("publisher"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPublishedYear(resultSet.getInt("publishedYear"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du livre : " + e.getMessage());
        }
        
        return book;
    }
    
    // Récupérer tous les livres
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
             
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAuthor(resultSet.getString("publisher"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPublishedYear(resultSet.getInt("publishedYear"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error during books fetch : " + e.getMessage());
        }
        
        return books;
    }

    public Book getBookById(int id) {
        String request = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(request)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("publisher"),
                            resultSet.getString("isbn"),
                            resultSet.getInt("publishedYear")
                              );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during book fetching : " + e.getMessage());

        }
        return null;
    }


    public String delete(int id) {
        String request = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(request)) {

            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return "Book successfully deleted!";
            }
        } catch (SQLException e) {
            System.err.println("Error  : " + e.getMessage());
        }
        return "Error during book delete!";
    }
    public String update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?,publisher=?, isbn = ?, publishedYear = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setString(4, book.getIsbn());
            preparedStatement.setInt(5, book.getPublishedYear());
            preparedStatement.setInt(6, book.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return "Book updated successfully!";
            }
        } catch (SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }
        return "Error during Book update !";
    }

    public String truncate() {
            String sql = "DELETE FROM books";
            try (Connection connection = DbConnection.getConnection();
                 Statement statement = connection.createStatement()) {

                statement.executeUpdate(sql);
                return "All books are deleted successfully !";
            } catch (SQLException e) {
                return "Error during book delete  : ";
            }

    }
}
