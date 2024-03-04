// -----------------------------------------------------
// Assignment 2
// Question: part 2
// Written by: Shehzar Aurangzeb Abbasi- 40291795
// -----------------------------------------------------

/**
 * Name: Shehzar Aurangzeb Abbasi ID:40291795
 * COMP6481
 * Assignment #2
 * Due Date Friday March 15, 2024
 */
package controllers;

import Exceptions.BadIsbn10Exception;
import Exceptions.BadIsbn13Exception;
import Exceptions.BadPriceException;
import Exceptions.BadYearException;
import models.Book;

import java.io.*;
import java.util.Scanner;

import static constants.Constants.*;
import static helpers.Helpers.*;

/**
 * The Part2Service class provides methods for reading files, checking semantics of records, and writing books to binary files.
 */
public class Part2Service {
    private static final int INITIAL_ARRAY_SIZE = 20;
    private static Book[] books = new Book[INITIAL_ARRAY_SIZE];
    private static int bookCount = 0;

    /**
     * Reads a file and checks the semantics of its records.
     *
     * @param p_filename The name of the file to read.
     */
    public static void readFileAndCheckSemantics(String p_filename) {
        try {
            Scanner l_fileScanner = new Scanner(new FileInputStream(BASE_PATH + "output_part_1/" + p_filename));
            while (l_fileScanner.hasNextLine()) {
                String l_record = l_fileScanner.nextLine();
                String[] parsedRecord = parseCSVRecord(l_record).split("\\|");
                try {
                    double l_price = Double.parseDouble(parsedRecord[2]);
                    String l_isbn = parsedRecord[3];
                    int l_year = Integer.parseInt(parsedRecord[5]);
                    if (!isValidPrice(l_price)) throw new BadPriceException();
                    else if (!isValidISBN(l_isbn)) {
                        if (l_isbn.length() == 10) throw new BadIsbn10Exception();
                        else if (l_isbn.length() == 13) throw new BadIsbn13Exception();
                    } else if (!isValidYear(l_year)) throw new BadYearException();
                        //record has no semantics error
                    else {
                        String l_title = parsedRecord[0];
                        String l_authors = parsedRecord[1];
                        String l_genre = parsedRecord[4];
                        Book book = new Book(l_title, l_authors, l_price, l_isbn, l_genre, l_year);
                        addBook(book);
                    }

                } catch (BadPriceException | BadIsbn10Exception | BadIsbn13Exception | BadYearException e) {
                    addErrorToBuffer(e.getMessage(), l_record);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

    /**
     * Writes books to binary files.
     *
     * @param p_filename The name of the file to write the books to.
     */
    public static void writeBooksToFiles(String p_filename) {
        try {
            ObjectOutputStream l_fileWriter = new ObjectOutputStream(new FileOutputStream(BASE_PATH + "output_part_2/" + p_filename + ".ser"));
            for (Book book : books) {
                if (book != null) {
//                    System.out.println("book => " + book);
                    l_fileWriter.writeObject(book);
                }
            }
            l_fileWriter.close();
            books = new Book[INITIAL_ARRAY_SIZE];
            bookCount = 0;
        } catch (IOException e) {
            System.out.println("Error: File not found");
        }
    }

    /**
     * Checks if the ISBN is valid.
     *
     * @param p_isbn The ISBN to validate.
     * @return True if the ISBN is valid, false otherwise.
     */
    private static boolean isValidISBN(String p_isbn) {
        int l_isbnLength = p_isbn.length();
        char[] l_isbnChars = p_isbn.toCharArray();
        int sum = 0;
        if (l_isbnLength == 10) {
            for (int i = 0; i < l_isbnLength; i++) {
                int digitValue = Character.getNumericValue(l_isbnChars[i]);
                sum += (10 - i) * digitValue;
            }
            return sum % 11 == 0;

        } else if (l_isbnLength == 13) {
            for (int i = 0; i < l_isbnLength; i++) {
                int digitValue = Character.getNumericValue(l_isbnChars[i]);
                sum += (i % 2 == 0 ? 1 : 3) * digitValue;

            }
            return sum % 10 == 0;
        }
        return false;
    }

    /**
     * Checks if the price is valid.
     *
     * @param p_price The price to validate.
     * @return True if the price is valid, false otherwise.
     */
    private static boolean isValidPrice(double p_price) {
        return p_price > 0;
    }

    /**
     * Checks if the year is valid.
     *
     * @param p_year The year to validate.
     * @return True if the year is valid, false otherwise.
     */
    private static boolean isValidYear(int p_year) {
        return p_year >= 1995 && p_year <= 2024;
    }

    /**
     * Adds a book to the array of books.
     *
     * @param book The book to add.
     */
    private static void addBook(Book book) {
        //if array is full
        if (bookCount == books.length) {
            Book[] new_books = new Book[books.length * 2];
            System.arraycopy(books, 0, new_books, 0, books.length);
            books = new_books;
        }
        //add the book to array
        books[bookCount++] = book;
    }

}
