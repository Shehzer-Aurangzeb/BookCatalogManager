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


package models;

import java.io.Serializable;

/**
 * The Book class represents a book object with various properties such as title, authors, price, ISBN, genre, and year.
 * It provides methods to access and modify these properties, as well as overrides for toString() and equals() methods.
 * This class is Serializable, allowing objects of this class to be serialized and deserialized.
 */
public class Book implements Serializable {
    private String title;
    private String authors;
    private double price;
    private String isbn;
    private String genre;
    private int year;

    /**
     * Constructs a Book object with the specified properties.
     *
     * @param new_title   The title of the book.
     * @param new_authors The authors of the book.
     * @param new_price   The price of the book.
     * @param new_isbn    The ISBN (International Standard Book Number) of the book.
     * @param new_genre   The genre of the book.
     * @param new_year    The year of publication of the book.
     */
    public Book(String new_title,String new_authors,double new_price,String new_isbn,String new_genre,int new_year){
        this.title=new_title;
        this.authors=new_authors;
        this.price=new_price;
        this.isbn=new_isbn;
        this.genre=new_genre;
        this.year=new_year;

    }
    // Getter and setter methods for all properties
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a string representation of the Book object.
     *
     * @return A string containing the title, authors, price, ISBN, genre, and year of the book.
     */
    @Override
    public String toString() {
        return "Title: " + title + " Authors: " + authors + " Price: " + price + " ISBN: " + isbn +
                " Genre: " + genre + " Year: " + year;
    }
    /**
     * Compares this Book object to the specified object for equality.
     *
     * @param p_obj The object to compare to this Book.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object p_obj){
        if(p_obj==null || this.getClass()!=p_obj.getClass()) return false;
        Book book= (Book) p_obj;
        return book.title.equals(this.title)&&
                book.authors.equals(this.authors) &&
                Double.compare(book.price,this.price)==0 &&
                book.isbn.equals(this.isbn) &&
                book.year==this.year;
    }
}
