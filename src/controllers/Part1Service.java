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

import Exceptions.MissingFieldException;
import Exceptions.TooFewFieldsException;
import Exceptions.TooManyFieldsException;
import Exceptions.UnknownGenreException;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static constants.Constants.*;
import static helpers.Helpers.*;

/**
 * The Part1Service class provides methods for reading input files, checking syntax, and writing records to CSV files based on genre.
 */
public class Part1Service {
    private static PrintWriter d_FileWriter;
    private static final int EXPECTED_FIELDS = 6;
    private static final String[] FIELD_NAMES = {"title", "authors", "price", "ISBN", "genre", "year"};
    private static String[] recordsBuffer = new String[GENRES.length];


    /**
     * Reads file names from an input file.
     *
     * @return An array of file names.
     */
    public static String[] readFileNames() {
        try {
            Scanner l_fileNamesScanner = new Scanner(new FileInputStream(BASE_PATH + "input/part1_input_file_names.txt"));
            int noOfBooks = l_fileNamesScanner.nextInt();
            String[] l_fileNames=new String[noOfBooks];
            //junk
            l_fileNamesScanner.nextLine();
            for(int i=0; i<noOfBooks; i++){
                String l_filename= l_fileNamesScanner.nextLine();
                l_fileNames[i]= l_filename;
            }
            return l_fileNames;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }

    /**
     * Reads a file and checks for syntax errors.
     *
     * @param p_filename The name of the file to read.
     */

    public static void readFileAndCheckSyntax(String p_filename) {
        try {
            Scanner l_fileScanner = new Scanner(new FileInputStream(BASE_PATH  + "input/books/"+p_filename));
            while (l_fileScanner.hasNextLine()) {
                String l_record = l_fileScanner.nextLine();
                String[] parsedRecord = parseCSVRecord(l_record).split("\\|");
                int numOfTokens =parsedRecord.length;
//                System.out.println("no of tokens==> "+numOfTokens);
                try {
                    if (numOfTokens > EXPECTED_FIELDS) {
                        throw new TooManyFieldsException();
                    } else if (numOfTokens < EXPECTED_FIELDS) {
                        throw new TooFewFieldsException();
                    } else {
                        String missingFieldName = isMissingField(parsedRecord);
                        if (missingFieldName != null) {
                            throw new MissingFieldException(missingFieldName);
                        }
                        //check for genre
                        int genreIndex = getGenreIndex(parsedRecord[4]);
                        if (genreIndex == -1) {
                            throw new UnknownGenreException();
                        } else {
                            appendRecordToBuffer(l_record, genreIndex);
                        }
                    }
                } catch (TooManyFieldsException | TooFewFieldsException | MissingFieldException |
                         UnknownGenreException e) {
//                    System.out.println(parseRecord(l_record));
//                    System.out.println(numOfTokens);
//                    System.out.println(e.getMessage());
                    addErrorToBuffer(e.getMessage(),l_record);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found " + p_filename);
        }
    }

    /**
     * Checks if any field is missing in the record.
     *
     * @param p_fields The fields of the record.
     * @return The name of the missing field, or null if no field is missing.
     */
    private static String isMissingField(String[] p_fields) {
        //check for missing field
        for (int i = 0; i < EXPECTED_FIELDS; i++) {
            if (p_fields[i] == null || p_fields[i].isEmpty()||p_fields[i].equals("N/A")) {
                return FIELD_NAMES[i];
            }
        }
        return null;
    }

    /**
     * Gets the index of the genre in the GENRES array.
     *
     * @param p_genre The genre.
     * @return The index of the genre, or -1 if the genre is unknown.
     */
    private static int getGenreIndex(String p_genre) {
        return switch (p_genre) {
            case "CCB" -> 0;
            case "HCB" -> 1;
            case "MTV" -> 2;
            case "MRB" -> 3;
            case "NEB" -> 4;
            case "OTR" -> 5;
            case "SSM" -> 6;
            case "TPA" -> 7;
            default -> -1; // Return -1 for unknown genres
        };
    }

    /**
     * Appends a record to the buffer based on its genre.
     *
     * @param p_record     The record to append.
     * @param p_genreIndex The index of the genre.
     */
    private static void appendRecordToBuffer(String p_record, int p_genreIndex) {
        if (recordsBuffer[p_genreIndex] == null || recordsBuffer[p_genreIndex].isEmpty()) {
            recordsBuffer[p_genreIndex] = p_record;
        } else {
            recordsBuffer[p_genreIndex] += "\n" + p_record;
        }
    }

    /**
     * Writes records from the buffer to CSV files.
     */
    public static void writeRecordsToCSVFiles() {
        try {
            for (int i = 0; i < recordsBuffer.length; i++) {
                //get file name
                String l_fileName = FILE_NAMES[i];
                //if there is nothing to write continue to next file.
                if(recordsBuffer[i]==null||recordsBuffer[i].isEmpty()) continue;
                d_FileWriter = new PrintWriter(new FileOutputStream(BASE_PATH + "output_part_1/" + l_fileName,true));
                d_FileWriter.println(recordsBuffer[i]);
                //clear buffer after writing
                recordsBuffer[i] = "";
                d_FileWriter.close();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
