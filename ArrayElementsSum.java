public class ArrayElementsSum {

    //найти сумму элементов массива;

    public static void main(String[] args) {
        int[] array = new int[]{10, 20, 30, 40, 50, 200};
        System.out.println(sum(array, 0, 0));
    }

    public static int sum(int[] array, int element, int sum) {
        if (element == array.length) {
            return sum;
        }

        sum += array[element];
        element++;

        return sum(array, element, sum);
    }
}
