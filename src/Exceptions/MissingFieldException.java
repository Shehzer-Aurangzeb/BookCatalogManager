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
 * The MissingFieldException class represents an exception that is thrown when a missing field is encountered.
 */
public class MissingFieldException extends Exception{
    public MissingFieldException(String p_field){
        super("missing "+ p_field);
    }
}
