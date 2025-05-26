package org.jakubsmid22;

import org.jakubsmid22.db.Book;
import org.jakubsmid22.db.DbManager;

import java.util.List;
import java.util.Scanner;

public class LibraryManager {

    Scanner scanner = new Scanner(System.in);

    DbManager dbManager = new DbManager();

    public void printOptions() {

        System.out.println("Welcome to Library management.\n");

        while (true) {

            System.out.println("""
                    What you want to do?
                    0 - Exit
                    1 - Add a book
                    2 - Show all books
                    3 - Find book
                    4 - Delete a book""");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 0 -> {
                        return;
                    }

                    case 1 -> addBook();

                    case 2 -> printAllBooks();

                    case 3 -> findBook();

                    case 4 -> deleteBook();

                    default -> System.out.println("Please, enter a number between 0 and 4.");

                }

            }
            catch (Exception e) {
                System.out.println("Please, enter a number between  1 and 4.");
            }

        }

    }

    private void printAllBooks() {

        List<Book> books = dbManager.getAllBooks();

        for (Book book : books) {
            System.out.println((book.getId() + " - " + book));
        }

    }

    private void addBook() {

        scanner.nextLine();

        System.out.print("Enter a title: ");
        String title = scanner.nextLine();

        System.out.print("Enter a author: ");
        String author = scanner.nextLine();

        int year;

        while (true) {
            try {
                System.out.print("Enter a year: ");
                year = scanner.nextInt();
                break;
            }
            catch (Exception e) {
                System.out.println("Enter a number, please.");
            }
        }

        scanner.nextLine();

        System.out.print("Enter a genre: ");
        String genre = scanner.nextLine();

        if (dbManager.addBook(title, author, year, genre) > 0) {
            System.out.println("Book added.");
        }

    }

    public void findBook() {
        scanner.nextLine();
        System.out.print("Find book by title: ");
        String title = scanner.nextLine();

        List<Book> books = dbManager.findBooks(title);

        if (books.isEmpty()) {
            System.out.println("\nNo books found.\n");
        }
        else {
            books.forEach(System.out::println);
        }

    }

    public void deleteBook() {
        printAllBooks();


        int choice;

        while (true) {
            try {
                System.out.print("Enter an id of book you want to delete: ");
                choice = scanner.nextInt();
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong format.");
            }
        }

        scanner.nextLine();

        if (dbManager.deleteBook(choice) > 0) {
            System.out.println("\nBook deleted.\n");
        }
        else {
            System.out.println("\nBook not found.\n");
        }

    }


}
