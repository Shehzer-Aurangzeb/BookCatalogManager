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


package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static constants.Constants.*;

/**
 * The Helpers class provides various helper methods for parsing CSV records, handling errors, writing errors to files,
 * performing cleanup operations, and displaying welcome messages.
 */
public class Helpers {
    private static PrintWriter d_FileWriter;
    private static  String[] errorLogBuffer = new String[BATCH_SIZE];
    public static  int errorBufferCount = 0;

    /**
     * Parses a CSV record by replacing commas with pipes ('|') and appending a dummy value for the year field if necessary.
     *
     * @param p_record The CSV record to parse.
     * @return The parsed record.
     */
    public static String parseCSVRecord(String p_record) {
        String l_parsedBuffer = "";
        boolean l_withinQuotes = false;
        for (char c : p_record.toCharArray()) {
            if (c == '"') {
                l_withinQuotes = !l_withinQuotes;
            } else if (c == ',' && !l_withinQuotes) l_parsedBuffer = l_parsedBuffer + "|";
            else l_parsedBuffer += c;
        }
        // special case to check for year.
        if (p_record.endsWith(",")) {
            // Append a dummy value for the year field
            l_parsedBuffer += "N/A";
        }
        return l_parsedBuffer;
    }
    /**
     * Adds an error message along with the corresponding record to the error buffer.
     *
     * @param p_error  The error message.
     * @param p_record The record associated with the error.
     */
    public static void addErrorToBuffer(String p_error,String p_record) {
        String l_error= "Error: "+p_error+"\n"+"Record: "+p_record+"\n";
        if(errorBufferCount==errorLogBuffer.length) {
            //if buffer is full create a new array of size*2 and copy the errors to that array.
            String[] newErrorLogBuffer= new String[errorLogBuffer.length*2];
            System.arraycopy(errorLogBuffer, 0, newErrorLogBuffer, 0, errorLogBuffer.length);
            errorLogBuffer=newErrorLogBuffer;
        }
        errorLogBuffer[errorBufferCount++]=l_error;
    }

    /**
     * Writes errors from the error buffer to a file.
     *
     * @param p_errorFilename The filename in which error occurs.
     * @param p_outputFilename The filename of the output file.
     */
    public static void writeErrorsToFile(String p_errorFilename,String p_outputFilename){
        try {
            d_FileWriter = new PrintWriter(new FileOutputStream(BASE_PATH  +
                    p_outputFilename, true));
            d_FileWriter.println("syntax error in file: "+p_errorFilename+"\n====================");
            for(String error:errorLogBuffer){
                if(error==null|| error.isEmpty()) continue;
                d_FileWriter.println(error);
            }
            d_FileWriter.close();
            errorLogBuffer=new String[BATCH_SIZE];
            errorBufferCount=0;
        }
        catch (FileNotFoundException e){
            System.out.println("Error: error file not found");
        }
    }

    public static void cleanup(){
        File syntaxErrorFile = new File(BASE_PATH+"output_part_1/syntax_error_file.txt");
        File semanticErrorFile = new File(BASE_PATH+"output_part_2/semantic_error_file.txt");
        for(String l_filename:FILE_NAMES){
            File csvfile = new File(BASE_PATH+"output_part_1/"+l_filename);
            File binaryfile = new File(BASE_PATH+"output_part_2/"+l_filename+".ser");

            if(csvfile.exists()) csvfile.delete();
            if(binaryfile.exists()) binaryfile.delete();
        }
        if(syntaxErrorFile.exists()) syntaxErrorFile.delete();
        if(semanticErrorFile.exists()) semanticErrorFile.delete();
    }
    public static void displayMyWelcomeMessage(){
        String name = "Shehzar Aurangzeb Abbasi";
        int studentID = 40291795;

        System.out.println("\n----------------------------------------------------");
        System.out.println("      Welcome, My name is " + name + "!");
        System.out.println("      Student ID: " + studentID);
        System.out.println("----------------------------------------------------\n");
    }
}
