package animal_factory;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return "Я собака. Зовут меня - " + super.getName();
    }
}
