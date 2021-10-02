import java.io.*;
import java.util.Arrays;

public class ReadFileLineByLine {
    private static final String PATH = "./journalEvents_ru.csv";
    private static boolean[] booleanArray = new boolean[0];
    private static int booleanElementCounter = 0;
    private static String[][] actionsArray = new String[0][];
    private static int actionsRowsCounter = 0;


    // read file line by line
    public static void reader() {
        try {
            File file = new File(PATH);
            //create object FileReader for object File
            FileReader fr = new FileReader(file);
            //create BufferedReader from existing FileReader for reading each line
            BufferedReader reader = new BufferedReader(fr);
            // read first string
            String line = reader.readLine();
            while (line != null) {
                // for tests   System.out.println(line);
                booleanArrayMaker(line);
                actionsArrayMaker(line);
                // read all other strings in the while cycle
                line = reader.readLine();
            }
            //close resources
            fr.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //put the last true/false in each line in the array
    public static void booleanArrayMaker(String line) {
        StringBuilder sb = new StringBuilder(line);
        if (sb.indexOf("false") != -1) { //find the index of first letter if false
            booleanArray = Arrays.copyOf(booleanArray, booleanElementCounter + 1);
            booleanArray[booleanElementCounter] = false;
            booleanElementCounter++;
        } else if (sb.indexOf("true") != -1) { //find the index of first letter if true
            booleanArray = Arrays.copyOf(booleanArray, booleanElementCounter + 1);
            booleanArray[booleanElementCounter] = true;
            booleanElementCounter++;
        }
        //for tests System.out.println(Arrays.toString(booleanArray));
    }

    //put the whole string except ",false/,true" in two-dimensional array
    public static void actionsArrayMaker(String line) {
        StringBuilder sb = new StringBuilder(line);
        if (sb.indexOf("false") != -1) {
            String formattedLine = line.substring(0, sb.indexOf("false") - 1); //cut ,false from out string
            actionsArray = Arrays.copyOf(actionsArray, actionsRowsCounter + 1);
            actionsArray[actionsRowsCounter] = new String[]{formattedLine};
            actionsRowsCounter++;
        } else if (sb.indexOf("true") != -1) {
            String formattedLine = line.substring(0, sb.indexOf("true") - 1); //cut ,true from out string
            actionsArray = Arrays.copyOf(actionsArray, actionsRowsCounter + 1);
            actionsArray[actionsRowsCounter] = new String[]{formattedLine};
            actionsRowsCounter++;
        }
        // for tests   System.out.println(Arrays.deepToString(actionsArray));
    }

    public static void main(String[] args) {
        reader();
        System.out.println(Arrays.deepToString(actionsArray));
        System.out.println(Arrays.toString(booleanArray));
        System.out.println(actionsArray.length);
        System.out.println(booleanArray.length);
    }


}
