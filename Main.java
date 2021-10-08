public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        print("This is our 2nd commit for HW-04");
        print(3, "This is our 3nd commit for HW-04");
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void print(int number, String text) {
        for (int i = 0; i < number; i++) {
            System.out.println(text);
        }
    }
}
