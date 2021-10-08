public class ReverseNumber {

    //получение числа, записанного теми же цифрами, но в противоположном порядке
    // (например, передали число 574 - метод должен вернуть 475).

    public static void main(String[] args) {
        int number = 574;
        char[] digitsArray = Integer.toString(number).toCharArray();
        StringBuilder sb = new StringBuilder();
        System.out.println(reverseNumber(digitsArray, digitsArray.length - 1, sb));
    }

    public static int reverseNumber(char[] digitsArray, int fromLastToFirst, StringBuilder sb) {
        if (fromLastToFirst < 0) {
            return Integer.parseInt(sb.toString());
        }

        sb.append(digitsArray[fromLastToFirst]);
        fromLastToFirst--;

        return reverseNumber(digitsArray, fromLastToFirst, sb);
    }
}
