package com.library.dao;

import com.library.model.Student;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public StudentDAO() {
            }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (id, name, mail) VALUES (?, ?,?)";
        try (PreparedStatement statement = DbConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getMail());
            statement.executeUpdate();
            System.out.println("student ajoute :"+ student.getId());
        } catch (SQLException e) {
            System.err.println("Error while adding student");
            System.out.println(e);
        }
    }

    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement statement = DbConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(resultSet.getInt("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while getting student");

        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (PreparedStatement statement = DbConnection.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(new Student(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.err.println("Error while getting student");

        }
        return students;
    }

    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ? WHERE id = ?";
        try (PreparedStatement statement = DbConnection.getConnection().prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating student");

        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = DbConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("student deleted");
        } catch (SQLException e) {
            System.err.println("Error while deleting student");

        }
    }



    public void truncate() {
        String query = "DELETE FROM students";
        try (PreparedStatement statement = DbConnection.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while getting students");
        }
    }
}
