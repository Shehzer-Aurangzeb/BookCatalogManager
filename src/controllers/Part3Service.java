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

import models.Book;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static constants.Constants.*;

/**
 * The Part3Service class provides methods for reading binary files and storing objects.
 */
public class Part3Service {

    private static ObjectInputStream d_ois=null;
    private static final int INITIAL_ARRAY_SIZE=10;
    private static int currentFileIndex=0;
    private static Book[][] books= new Book[FILE_NAMES.length][];
    private static int bookCount=0;

    /**
     * Reads a binary file and stores the objects in a 2D array.
     *
     * @param p_filename The name of the file to read.
     */
    public static void readFileAndStoreObjects(String p_filename){
        try{
            d_ois= new ObjectInputStream(new FileInputStream(BASE_PATH+"output_part_2/"+p_filename+".ser"));
            Book[] l_currentFileBooks= new Book[INITIAL_ARRAY_SIZE];
            try {
                while (true) {
                    Book book = (Book) d_ois.readObject();
//                    System.out.println("book "+book.toString());
                    if(bookCount== l_currentFileBooks.length){
                        Book[] new_books_arr= new Book[l_currentFileBooks.length*2];
                        System.arraycopy(l_currentFileBooks,0,new_books_arr,0,l_currentFileBooks.length);
                        l_currentFileBooks= new_books_arr;
                    }
                    l_currentFileBooks[bookCount++]= book;
                }
            }
            catch (EOFException ignored){
                d_ois.close();
                //trim the array to remove unused spaces
                Book[] l_trimmed_book_array= new Book[bookCount];
                System.arraycopy(l_currentFileBooks,0,l_trimmed_book_array,0,bookCount);
               //add them in 2d books array
                books[currentFileIndex]= new Book[l_trimmed_book_array.length];
                books[currentFileIndex++]=l_trimmed_book_array;
                bookCount=0;

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves the 2D array of books.
     *
     * @return The 2D array of books.
     */
    public static Book[][] getBooks(){
//        for(Book[] book:books){
//            System.out.println("Books==> "+book.length);
//        }
        return books;
    }
}
