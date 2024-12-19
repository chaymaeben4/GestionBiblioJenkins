
package com.library.service;


import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

public class BorrowService {

    private BorrowDAO borrowDAO;

    // Constructeur avec BorrowDAO
    public BorrowService() {
        this.borrowDAO=new BorrowDAO();
    }

    // Méthode pour emprunter un livre
    public String borrowBook(Borrow borrow) {
        // Sauvegarde de l'emprunt dans la base de données

        return borrowDAO.addBorrow(borrow);
    }

    // Afficher les emprunts (méthode fictive, à adapter)
    public void displayBorrows() {
        System.out.println("Liste des emprunts...");
        // Afficher les emprunts enregistrés (adapté selon votre DAO)
    }
    public String deleteBorrow(int id) {
        return borrowDAO.deleteBorrow(id);
    }
    public String truncate() {
        return borrowDAO.truncate();
    }
}
