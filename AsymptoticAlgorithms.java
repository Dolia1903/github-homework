import java.util.Arrays;
import java.util.SplittableRandom;

public class AsymptoticAlgorithms {

    public static int simpleSearch(int[] array, int elementToSearch) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == elementToSearch) {
                return index;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] array, int elementToSearch) {
        int firstIndex = 0;
        int lastIndex = array.length - 1;

        // exit condition (element is not represented)
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // if middleIndex element is elementToSearch - return its index
            if (array[middleIndex] == elementToSearch) {
                return middleIndex;
            }
            // if middleIndex element smaller than elementToSearch
            // put our firstIndex in the middle+1, throwing away the first part of the array
            else if (array[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;
                // if middleIndex element bigger than elementToSearch
                // put our lastIndex in the middle-1, throwing away the second part of the array
            else if (array[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;
        }
        return -1;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0) {
            return; // exit, if array.length = 0
        }

        if (low >= high) {
            return; // exit if we have nothing to divide
        }

        // choose base element
        int middle = low + (high - low) / 2;
        int base = array[middle];

        // divide into subArray, which are bigger and smaller than base element
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < base) {
                i++;
            }

            while (array[j] > base) {
                j--;
            }

            if (i <= j) {  // switch positions
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // recursion call for sorting left and right part
        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    public static int[] makeRandomArray(int elementsQuantity) {
        int[] array = new int[elementsQuantity];
        SplittableRandom random = new SplittableRandom();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-100, 101);
        }

        return array;
    }

    public static void simpleSearchDuration(int elementsQuantity) {
        int[] array = makeRandomArray(elementsQuantity);
        long startTime = System.nanoTime();
        simpleSearch(array, 50);
        long endTime = System.nanoTime();
        System.out.println("simpleSearchDuration() for " + elementsQuantity + " elements took "
                + (endTime - startTime) + " nanoseconds");
    }

    public static void binarySearchDuration(int elementsQuantity) {
        int[] array = makeRandomArray(elementsQuantity);
        Arrays.sort(array);
        long startTime = System.nanoTime();
        binarySearch(array, 50);
        long endTime = System.nanoTime();
        System.out.println("binarySearch() for " + elementsQuantity + " elements took "
                + (endTime - startTime) + " nanoseconds");
    }

    public static void quickSortDuration(int elementsQuantity) {
        int[] array = makeRandomArray(elementsQuantity);
        long startTime = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();
        System.out.println("quickSort() for " + elementsQuantity + " elements took "
                + (endTime - startTime) + " nanoseconds");
    }

    public static void insertionSortDuration(int elementsQuantity) {
        int[] array = makeRandomArray(elementsQuantity);
        long startTime = System.nanoTime();
        insertionSort(array);
        long endTime = System.nanoTime();
        System.out.println("insertionSort() for " + elementsQuantity + " elements took "
                + (endTime - startTime) + " nanoseconds");
    }

    public static void main(String[] args) {
        // This block for testing methods without durations
       /* int[] array = {85, 3, 15, -150, 24, 36, 90, 2, 0, -3};
        System.out.println(Arrays.toString(array));
        System.out.println("2 index is: " + simpleSearch(array, 2));
        System.out.println("--------------------------------------");

        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("90 index is: " + binarySearch(array, 90));
        System.out.println("--------------------------------------");

        int[] array2 = {85, 3, 15, -150, 24, 36, 90, 2, 0, -3};
        System.out.println(Arrays.toString(array2));
        quickSort(array2, 0, array2.length - 1);
        System.out.println(Arrays.toString(array2));
        System.out.println("--------------------------------------");

        int[] array3 = {85, 3, 15, -150, 24, 36, 90, 2, 0, -3};
        System.out.println(Arrays.toString(array3));
        insertionSort(array3);
        System.out.println(Arrays.toString(array2));
        System.out.println("--------------------------------------");*/


        simpleSearchDuration(1000);
        simpleSearchDuration(1000000);
        System.out.println("--------------------------------------");

        binarySearchDuration(1000);
        binarySearchDuration(1000000);
        System.out.println("--------------------------------------");

        quickSortDuration(1000);
        quickSortDuration(1000000);
        System.out.println("--------------------------------------");

        insertionSortDuration(1000);
        insertionSortDuration(1000000);
        System.out.println("--------------------------------------");
    }
}
