package org.jakubsmid22.db;

import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DbManager {

    private final Logger logger = getLogger(DbManager.class);

    private static final String GET_ALL_BOOKS = "select * from book";
    private static final String ADD_BOOK = "insert into book(title, author, year, genre) values(?, ?, ?, ?)";
    private static final String GET_ONE_BOOK = "select * from book where title like ?";
    private static final String DELETE_BOOK = "delete from book where id = ?";

    public List<Book> getAllBooks() {

        try (
                Connection conn = DataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_BOOKS);
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getString("genre")
                ));
            }

            return books;

        }
        catch (SQLException e) {
            logger.error("Error while getting books: ", e);
            return null;
        }

    }

    public int addBook(String title, String author, int year, String genre) {

        try (
                Connection conn = DataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(ADD_BOOK);
        ) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, genre);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error while adding book: ", e);
            return 0;
        }

    }

    public List<Book> findBooks(String title) {
        try (
                Connection conn = DataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(GET_ONE_BOOK);
        ) {

            preparedStatement.setString(1, "%" + title + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getString("genre")
                ));
            }

            return books;



        }
        catch (SQLException e) {
            logger.error("Error while finding book: ", e);
            return null;
        }
    }

    public int deleteBook(int id) {

        try (
                Connection conn = DataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(DELETE_BOOK);
        ) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error while deleting book: ", e);
            return 0;
        }

    }

}
