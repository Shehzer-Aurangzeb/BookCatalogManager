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
package constants;

/**
 * The Constants class contains various constant values used in the program.
 */
public class Constants {
    /**
     * The batch size for error logging.
     */
    public  static final int BATCH_SIZE=20;

    /**
     * The base path for file operations.
     */
    public static final String BASE_PATH = "src/resources/";
    /**
     * The array of available genres.
     */
    public static final String[] GENRES = { "CCB", "HCB", "MTV", "MRB", "NEB", "OTR","SSM","TPA" };
    /**
     * The array of file names for different categories.
     */
    public static final String[] FILE_NAMES = { "Cartoons_Comics_Books.csv", "Hobbies_Collectibles_Books.csv",
            "Movies_TV.csv", "Music_Radio_Books.csv", "Nostalgia_Eclectic_Books.csv", "Old_Time_Radio.csv",
            "Sports_Sports_Memorabilia.csv", "Trains_Planes_Automobiles.csv" };
}
