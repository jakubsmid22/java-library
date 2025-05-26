package org.jakubsmid22.db;

public class Book {

    private int id;
    private String title;
    private String author;
    private int year;
    private String genre;

    public Book(int id, String title, String author, int year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' ;
    }

    public int getId() {
        return id;
    }
}
