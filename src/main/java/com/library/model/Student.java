package com.library.model;

public class Student {
    private int id;
    private String name;

    private String mail;
    // Constructeur par défaut
    public Student() {
    }

    // Constructeur complet
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public Student(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
