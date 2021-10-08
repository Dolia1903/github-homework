public class MaxArrayElement {

    //максимальное значение в заданном массиве;

    public static void main(String[] args) {
        int[] array = new int[]{1000, 20, 30, 40, -50, 2000, 30, 0};
        System.out.println(maxElement(array, 0, 0));
    }

    public static int maxElement(int[] array, int element, int maxElement) {
        if (element == array.length) {
            return maxElement;
        }

        if (array[element] > maxElement) {
            maxElement = array[element];
        }

        element++;
        return maxElement(array, element, maxElement);
    }
}
