public class NumberDigitsSum {

    //сумму цифр заданного числа (например, передали число 574 - метод должен вернуть 16);

    public static void main(String[] args) {
        int number = 574;
        char[] digitsArray = Integer.toString(number).toCharArray();
        System.out.println(digitsSum(digitsArray, 0, 0));
    }

    public static int digitsSum(char[] digitsArray, int element, int sum) {
        if (element == digitsArray.length) {
            return sum;
        }

        sum += Character.getNumericValue(digitsArray[element]);
        element++;

        return digitsSum(digitsArray, element, sum);
    }
}
