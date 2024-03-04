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


package views;

import models.Book;

import java.util.InputMismatchException;
import java.util.Scanner;

import static constants.Constants.*;

/**
 * The BookNavigator class represents a user interface for navigating through a collection
 * of book records stored in multiple files.
 */
public class BookNavigator {
    static Scanner d_sc = new Scanner(System.in);
    private String d_selectedFile = FILE_NAMES[0];
    private Book[][] d_books;

    // Array of books from the selected file
    private Book[] d_selectedBooksArray;

    /**
     * Constructs a new BookNavigator object with the provided array of book arrays.
     *
     * @param new_books The array of book arrays containing book records from different files.
     */
    public BookNavigator(Book[][] new_books) {
        d_books = new_books;
        d_selectedBooksArray = new_books[0];
    }

    /**
     * Starts the navigation process, displaying the main menu and handling user input.
     */
    public void startNavigation() {
        while (true) {
            displayMainMenu();
            System.out.print("\nEnter Your Choice: ");
            String l_command = d_sc.next().toLowerCase();
            d_sc.nextLine();
            switch (l_command) {
                case "v":
                    viewSelectedFile();
                    break;
                case "s":
                    selectFileToView();
                    break;
                case "x":
                    System.out.println("\nExiting the application.");
                    System.exit(0);
                default:
                    //show invalid choice feedback when user enter anything other than v,s or x.
                    System.out.println("\nEnter a valid choice.\n");

            }
        }

    }

    /**
     * function to display the main menu.
     */
    private void displayMainMenu() {
        System.out.println("----------------------------");
        System.out.println("          Main Menu          ");
        System.out.println("----------------------------");
        System.out.println(" v  View the selected file: " + generateFileDescription(d_selectedFile, d_selectedBooksArray.length));
        System.out.println(" s  Select a file to view");
        System.out.println(" x  Exit");
        System.out.println("----------------------------");
    }

    /**
     * function to handle the viewing of selected file
     */
    private void viewSelectedFile() {
        System.out.println("\nviewing: " + generateFileDescription(d_selectedFile, d_selectedBooksArray.length));
        Scanner l_sc = new Scanner(System.in);
        int l_currObjectPointer = 0;
        while (true) {
            System.out.print("\nEnter Your Choice: ");
            try {
                int l_choice = l_sc.nextInt();
                l_sc.nextLine();
                if (l_choice == 0) break;
                else if (l_choice > 0) {
                    int l_endIndex = l_currObjectPointer + l_choice - 1;
                    if (l_endIndex >= d_selectedBooksArray.length) {
                        l_endIndex = d_selectedBooksArray.length - 1;
                    }
                    for (int i = l_currObjectPointer; i <= l_endIndex; i++) {
                        System.out.println(d_selectedBooksArray[i]);
                    }
                    // Update currentIndex to the last record in the displayed range
                    l_currObjectPointer = l_endIndex;
                    if (l_endIndex == d_selectedBooksArray.length-1) {
                        System.out.println("EOF has been reached.");
                    }
                } else {
                    int l_startIndex = l_currObjectPointer - Math.abs(l_choice) + 1;
                    int l_endIndex = l_currObjectPointer;
                    if (l_startIndex < 0) {
                        System.out.println("BOF has been reached.");
                        l_startIndex = 0;
                    }
                    for (int i = l_startIndex; i <= l_endIndex; i++) {
                        System.out.println(d_selectedBooksArray[i]);
                    }
                    // Update currentIndex to the last record in the displayed range
                    l_currObjectPointer = l_startIndex;

                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid choice. Please enter a number");
                l_sc.next();
            }

        }

    }

    /**
     * function to display the file sub-menu
     */
    private void selectFileToView() {
        System.out.println("\n-----------------------------");
        System.out.println("        File Sub-Menu        ");
        System.out.println("-----------------------------");
        for (int i = 0; i < FILE_NAMES.length; i++) {
            String l_filename = FILE_NAMES[i];
            int l_numOfRecords = d_books[i].length;
            System.out.println(i + 1 + " " + generateFileDescription(l_filename, l_numOfRecords));
        }
        System.out.println(FILE_NAMES.length + 1 + " Exit");
        System.out.println("-----------------------------");
        Scanner l_sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter Your Choice: ");
            try {
                int l_choice = l_sc.nextInt();
                l_sc.nextLine();
                if (l_choice < 1 || l_choice > FILE_NAMES.length + 1) {
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 9.");
                } else if (l_choice == FILE_NAMES.length + 1) {
                    System.out.println("\nExiting file sub-menu.");
                    break;
                } else {
                    d_selectedFile = FILE_NAMES[l_choice - 1];
                    d_selectedBooksArray = d_books[l_choice - 1];
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid choice. Please enter a number between 1 and 9.");
                l_sc.next();
            }
        }
    }

    /**
     * function to return filename and num of records in a way to show on console.
     */
    private String generateFileDescription(String p_filename, int p_numOfRecords) {
        return p_filename + ".ser" + " (" + p_numOfRecords + " records)";
    }
}
