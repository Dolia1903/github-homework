package animal_factory;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return "Я кот. Зовут меня - " + super.getName();
    }
}
