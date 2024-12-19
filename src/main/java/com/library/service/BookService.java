package com.library.service;
import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.List;


public class BookService {
     BookDAO bookDAO;  // Utilisation de DAO pour la gestion des livres

    // Constructeur qui initialise l'objet BookDAO

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;  // Initialisation de la variable bookDAO à l'objet BookDAO fourni en argument du constructeur.  // Constructeur qui initialise l'objet BookDAO
    }
    public BookService() {
        this.bookDAO = new BookDAO();
    }

    // Ajouter un livre
    public void addBook(Book book) {
        bookDAO.add(book);
    }

    // Afficher tous les livres
    public List<Book> displayBooks() {
        return bookDAO.getAllBooks();
    }

    // Trouver un livre par ID
    public Book findBookById(int id) {
        return bookDAO.getBookById(id);
    }

    // Supprimer un livre par ID
    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    // Mise à jour des informations d'un livre
    public void updateBook(Book book) {
        bookDAO.update(book);
    }

    public String truncate() {
              return bookDAO.truncate();
    }
}
