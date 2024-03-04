// -----------------------------------------------------
// Assignment #2
// Question: part 2
// Written by: Shehzar Aurangzeb Abbasi- 40291795
// -----------------------------------------------------

/**
 * Name: Shehzar Aurangzeb Abbasi ID:40291795
 * COMP6481
 * Assignment #2
 * Due Date Friday March 15, 2024
 */

import views.BookNavigator;

import static constants.Constants.FILE_NAMES;
import static controllers.Part1Service.*;
import static controllers.Part2Service.*;
import static controllers.Part3Service.*;
import static helpers.Helpers.*;

public class Main {
    public static void main(String[] args) {
        displayMyWelcomeMessage();
//        cleanup();
        do_part1();
        do_part2();
        do_part3();
    }

    public static void do_part1(){
        //read all the filenames from the input_file_names.txt and return it.
        String[] l_filenames=readFileNames();
        //for every file read its contents, check for syntax error and save it in buffer if record is syntactically valid.
        for(String l_filename:l_filenames){
            readFileAndCheckSyntax(l_filename);
            //write the whole buffer to a file
            writeRecordsToCSVFiles();
            //if there is any syntax error in this file. log them in syntax error file.
            if(errorBufferCount>0) writeErrorsToFile(l_filename,"output_part_1/syntax_error_file.txt");
        }
    }
    public static void do_part2(){
        //for every csv file created in part1
        //read their content and check for semantics error.
        //write semantically valid records to the binary file.
        //write semantically invalid records to semantic error file.
        for(String l_filename:FILE_NAMES){
            readFileAndCheckSemantics(l_filename);
            writeBooksToFiles(l_filename);
            if(errorBufferCount>0) writeErrorsToFile(l_filename,"output_part_2/semantic_error_file.txt");
        }
    }
    public static void do_part3(){
        //for every binary file created in part 2 read objects and save it in 2d array.
        //each nested array contains all records from a file.
        for(String l_filename:FILE_NAMES){
            readFileAndStoreObjects(l_filename);
        }
        //pass the stored books array to the view
        BookNavigator bookNavigator= new BookNavigator(getBooks());
        bookNavigator.startNavigation();

    }
}