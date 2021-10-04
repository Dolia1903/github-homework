import java.io.*;
import java.util.Arrays;

public class ReadFileLineByLine {
    private static final String PATH = "./journalEvents_ru.csv";
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    private static boolean[] booleanArray = new boolean[0];
    private static int booleanElementCounter = 0;
    private static String[][] actionsArray = new String[0][];
    private static int actionsRowsCounter = 0;


    /**
     * Read file line by line
     */
    public static void reader() {
        File file = new File(PATH);
        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            while (line != null) {
                // for tests   System.out.println(line);
                booleanArrayMaker(line);
                actionsArrayMaker(line);
                // read all other strings in the while cycle
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put the last true/false in each line in the array
     * @param line each string line from FileReader
     */
    public static void booleanArrayMaker(String line) {
        StringBuilder sb = new StringBuilder(line);
        if (sb.indexOf(FALSE) != -1) { // find the index of first letter if false
            copyBooleanArray(false);
        } else if (sb.indexOf(TRUE) != -1) { // find the index of first letter if true
            copyBooleanArray(true);
        }
        // for tests System.out.println(Arrays.toString(booleanArray));
    }

    /**
     * Make a copy of our booleanArray and put the boolean value from line
     * @param value boolean true or false
     */
    public static void copyBooleanArray(boolean value) {
        booleanArray = Arrays.copyOf(booleanArray, booleanElementCounter + 1);
        booleanArray[booleanElementCounter] = value;
        booleanElementCounter++;
    }

    /**
     * Put the whole string except ",false/,true" in two-dimensional array
     * @param line each string line from FileReader
     */
    public static void actionsArrayMaker(String line) {
        StringBuilder sb = new StringBuilder(line);
        if (sb.indexOf(FALSE) != -1) {
            copyActionsArray(line, false);
        } else if (sb.indexOf(TRUE) != -1) {
            copyActionsArray(line, true);
        }
        // for tests   System.out.println(Arrays.deepToString(actionsArray));
    }

    /**
     * Make a copy of our actionsArray and put the boolean value from line
     * @param line each string line from FileReader
     * @param value boolean value true or false
     */
    public static void copyActionsArray(String line, boolean value) {
        String formattedLine = line.substring(0, line.indexOf(String.valueOf(value)) - 1); // cut ,false from out string
        actionsArray = Arrays.copyOf(actionsArray, actionsRowsCounter + 1);
        actionsArray[actionsRowsCounter] = new String[]{formattedLine};
        actionsRowsCounter++;
    }

    public static void main(String[] args) {
        reader();
        System.out.println(Arrays.deepToString(actionsArray));
        System.out.println(Arrays.toString(booleanArray));
        System.out.println(actionsArray.length);
        System.out.println(booleanArray.length);
    }
}
