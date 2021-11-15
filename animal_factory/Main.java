package animal_factory;

import java.util.Scanner;

public class Main {

    public static void validateInput() {
        System.out.println("Please type animal's code \"da\"/\"db\"/\"dc\"/\"ca\"/\"cb\"/\"cc\"");
        Scanner scanner = new Scanner(System.in);
        String scanned = scanner.nextLine();

        if (AnimalFactory.getAnimalByKey(scanned) == null) {
            System.out.println("Не могу создать Animal");
            validateInput();
        } else {
            Animal animal = AnimalFactory.getAnimalByKey(scanned);
            System.out.println(animal.getName());
        }
    }

    public static void main(String[] args) {
        validateInput();
    }
}

