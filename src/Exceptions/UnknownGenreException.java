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
package Exceptions;

/**
 * The UnknownGenreException class represents an exception that is thrown when an unknown genre is encountered.
 */
public class UnknownGenreException  extends Exception{
    public UnknownGenreException(){
        super("invalid genre");
    }
}
