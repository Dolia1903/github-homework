import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class WereSquirrel {
    private static final String PATH = "./journalEvents_ru.csv";
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    private static boolean[] booleanArray = new boolean[0];
    private static int booleanElementCounter = 0;
    private static String[][] actionsArray = new String[0][];
    private static int actionsRowsCounter = 0;

    private static String[] uniqueActions = new String[0];
    private static int[][] statisticalVariables = new int[4][];
    private static double[] correlationArray = new double[0];


    /**
     * Read file line by line, call method checkSquirrel(line) to parse data
     */
    public static void reader() {
        File file = new File(PATH);
        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            int linesCounter = (int) Files.lines(Path.of(PATH)).count();
            booleanArray = Arrays.copyOf(booleanArray, linesCounter);
            actionsArray = Arrays.copyOf(actionsArray, linesCounter);

            while (line != null) {
                checkSquirrel(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determine if Jacques transformed this day and parse the values into actionsArray and booleanArray
     * @param line each string line from FileReader - the list of events from each day + squirrel boolean
     */
    public static void checkSquirrel(String line) {
        if (line.contains(FALSE)) {
            insertIntoActionsArray(line, false);
            insertIntoBooleanArray(false);
        } else if (line.contains(TRUE)) {
            insertIntoActionsArray(line, true);
            insertIntoBooleanArray(true);
        }
    }

    /**
     * Put the boolean value from line into booleanArray
     * @param value boolean true or false
     */
    public static void insertIntoBooleanArray(boolean value) {
        booleanArray[booleanElementCounter] = value;
        booleanElementCounter++;
    }

    /**
     * Put the actions from line into actionsArray, call method uniqueActionsArray(formattedLine) to add unique action
     * @param line  each string line from FileReader
     * @param value boolean value true or false
     */
    public static void insertIntoActionsArray(String line, boolean value) {
        // cut ,false/true from our string
        String formattedLine = line.substring(0, line.indexOf(String.valueOf(value)) - 1);
        uniqueActionsArray(formattedLine);
        actionsArray[actionsRowsCounter] = new String[]{formattedLine};
        actionsRowsCounter++;
    }

    /**
     * Our basic method to find index of the element from String[] array, using simple search algorithm
     * @param array - String[] array, we're going to use it for uniqueActions and actionsArray
     * @param elementToSearch - String element fo find
     * @return index of the element if success, -1 if the element is absent
     */
    public static int simpleSearch(String[] array, String elementToSearch) {
        for (int index = 0; index < array.length; index++) {
            if (array[index].contains(elementToSearch)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Check if the action is not in uniqueActions already, if no - add space and put the element into it
     * @param formattedLine - formatted line from insertIntoActionsArray method (without ,false/,true)
     */
    public static void uniqueActionsArray(String formattedLine) {
        for (String action : formattedLine.split(",")) {
            if (simpleSearch(uniqueActions, action) == -1) {
                uniqueActions = Arrays.copyOf(uniqueActions, uniqueActions.length + 1);
                uniqueActions[uniqueActions.length - 1] = action;
            }
        }
    }

    /**
     * Set statisticalVariables array size, create 4 int values for each unique action to calculate correlation later
     */
    public static void statisticalVariablesArray() {
        statisticalVariables = Arrays.copyOf(statisticalVariables, uniqueActions.length);
        int counter = 0;

        for (String action : uniqueActions) {
            int noSquirrelNoAction = 0;
            int noSquirrelAction = 0;
            int squirrelNoAction = 0;
            int squirrelAction = 0;
            for (int index = 0; index < booleanArray.length; index++) {
                if (booleanArray[index]) { // if squirrel (true) - transformation occurred
                    if (simpleSearch(actionsArray[index], action) == -1) {
                        squirrelNoAction++;
                    } else {
                        squirrelAction++;
                    }
                } else if (!booleanArray[index]) { // if not squirrel (false) - no transformation
                    if (simpleSearch(actionsArray[index], action) == -1) {
                        noSquirrelNoAction++;
                    } else {
                        noSquirrelAction++;
                    }
                }
            }
            statisticalVariables[counter] = new int[]{noSquirrelNoAction, noSquirrelAction,
                    squirrelNoAction, squirrelAction};
            counter++;
        }
    }

    /**
     * Method to calculate phi - correlation for each unique action according to the statisticalVariables
     */
    public static void calculateCorrelation() {
        correlationArray = Arrays.copyOf(correlationArray, uniqueActions.length);
        int counter = 0;
        for (int[] statVars : statisticalVariables) {
            double phi = (statVars[3] * statVars[0] - statVars[2] * statVars[1])
                    / Math.sqrt((statVars[2] + statVars[3])
                    * (statVars[0] + statVars[1])
                    * (statVars[1] + statVars[3])
                    * (statVars[0] + statVars[2]));
            correlationArray[counter] = phi;
            counter++;
        }
    }

    /**
     * Method to print the result for each correlation more than 0.1 and less than -0.1
     * round the double values to 9 digits after the coma as in the example in the HW description
     */
    public static void printSignificantCorrelation() {
        for (int index = 0; index < correlationArray.length; index++) {
            if (correlationArray[index] > 0.1 || correlationArray[index] < -0.1) {
                System.out.println(uniqueActions[index] + ": "
                        + BigDecimal.valueOf(correlationArray[index]).setScale(9, RoundingMode.HALF_UP));
            }
        }
    }

    /**
     * Method to add new event into actionsArray to check our correlation theory
     * @param include - first condition, check if the action included into line
     * @param exclude - second condition, check if the action is absent in the line
     * @param newEvent - the new action we're going to add to check our correlation theory
     */
    public static void changeEvents(String include, String exclude, String newEvent) {
        for (int i = 0; i < actionsArray.length; i++) {
            String line = Arrays.toString(actionsArray[i]);
            if (line.contains(include) && !line.contains(exclude)) {
                actionsArray[i] = new String[]{newEvent + "," + booleanArray[i]};
            }
        }
        uniqueActions = Arrays.copyOf(uniqueActions, uniqueActions.length + 1);
        uniqueActions[uniqueActions.length - 1] = newEvent;
    }


    /**
     * Method to print the correlation for 1 single event - "арахис-зубы"
     * @param event "арахис-зубы" string
     */
    public static void printSingleEvent(String event) {
        for (int index = 0; index < uniqueActions.length; index++) {
            if (uniqueActions[index].equals(event)) {
                System.out.println(uniqueActions[index] + ": "
                        + BigDecimal.valueOf(correlationArray[index]).setScale(9, RoundingMode.HALF_UP));
            }
        }
    }

    public static void main(String[] args) {
        reader();
        statisticalVariablesArray();
        calculateCorrelation();
        printSignificantCorrelation();

        System.out.println("------------------------");

        changeEvents("ел арахис", "чистил зубы", "арахис-зубы");
        statisticalVariablesArray();
        calculateCorrelation();
        printSingleEvent("арахис-зубы");
    }
}
