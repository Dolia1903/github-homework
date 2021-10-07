import java.util.ArrayList;

public class OperationsSequence {
    private static final ArrayList<String> stringsContainer = new ArrayList<>();
    private static final ArrayList<Integer> resultsContainer = new ArrayList<>();
    private static int iterationCounter = 0;

    public static void main(String[] args) {
        firstIteration(87879);
    }

    /**
     * Method to initialize the first iteration from 1
     * @param expectedNumber the expected integer value
     */
    public static void firstIteration(int expectedNumber) {
        resultsContainer.add(1 + 5);      resultsContainer.add(1 * 3); // I don't want to change this
        stringsContainer.add("(1 + 5)");  stringsContainer.add("(1 * 3)");
        iterationCounter++;
        nextIteration(expectedNumber);
    }

    /**
     * Main method to determine each next iteration and exit conditions
     * @param expectedNumber the expected integer value
     * @return result String - sequence of arithmetical operations
     */
    public static String nextIteration(int expectedNumber) {
        // on every iteration we're adding 2 pow n elements
        int checkedStart = (int) (resultsContainer.size() - Math.pow(2, iterationCounter));
        int counter = 0;

        for (int i = 0; i < resultsContainer.size(); i++) {
            if (resultsContainer.get(i) == expectedNumber) {
                System.out.println(expectedNumber + " = " + stringsContainer.get(i));
                return stringsContainer.get(i);
            } else if (i >= checkedStart && resultsContainer.get(i) > expectedNumber) {
                counter++;
            }
        }

        // if on previous iteration each new element bigger than expected number - exit
        if (counter >= (int) Math.pow(2, iterationCounter)) {
            System.out.println(expectedNumber + ": " + "It's not possible to receive this number");
            return null;
        }

        stringsAdding(checkedStart);
        resultsAdding(checkedStart);

        iterationCounter++;
        return nextIteration(expectedNumber);
    }

    /**
     * Method to add new elements into our stringsContainer (sequence of all possible operations)
     * @param checkedStart our start index depending on the iteration
     */
    public static void stringsAdding(int checkedStart) {
        ArrayList<String> stringsContainer2 = new ArrayList<>();
        for (int i = checkedStart; i < stringsContainer.size(); i++) {
            stringsContainer2.add("(" + stringsContainer.get(i) + " + 5)");
            stringsContainer2.add("(" + stringsContainer.get(i) + " * 3)");
        }
        stringsContainer.addAll(stringsContainer2);
    }

    /**
     * Method to add new elements into our resultsContainer (list of all results on each operation)
     * @param checkedStart our start index depending on the iteration
     */
    public static void resultsAdding(int checkedStart) {
        ArrayList<Integer> resultsContainer2 = new ArrayList<>();
        for (int i = checkedStart; i < resultsContainer.size(); i++) {
            resultsContainer2.add(resultsContainer.get(i) + 5);
            resultsContainer2.add(resultsContainer.get(i) * 3);
        }
        resultsContainer.addAll(resultsContainer2);
    }
}
