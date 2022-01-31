package com.github.hackathon.advancedsecurityjava.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Book {
    public String name;
    public String author;
  
    public Boolean read = false;
  
    public Book(String name, String author) {
      this.name = name;
      this.author = author;
    }
  
    public Book(String name, String author, Boolean read) {
      this.name = name;
      this.author = author;
      this.read = read;
    }

    public static List<Book> all(Statement statement) throws SQLException {
        return Book.find(statement, "SELECT * FROM Books");
    }

    public static List<Book> find(Statement statement, String query) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            Book book = new Book(results.getString("name"), results.getString("author"), (results.getInt("read") == 1));
            books.add(book);
        }
        return books;
    }

    public static List<Book> findAuthor(Statement statement, String author) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        String query = "SELECT * FROM Books WHERE author LIKE '%" + author + "%'";
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            Book book = new Book(results.getString("name"), results.getString("author"), (results.getInt("read") == 1));
            books.add(book);
        }

        return books;
    }

}
