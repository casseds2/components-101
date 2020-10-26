import java.util.ArrayList;
import java.util.Arrays;

interface Animal {
    public void makeNoise();
    public void eats();
}

class Lion implements Animal {
    @Override
    public void makeNoise() {
        System.out.println("The lion goes roooooar");
    }

    @Override
    public void eats() {
        System.out.println("The lion eats pig"); // Poor pig
    }
}

class Pig implements Animal {
    @Override
    public void makeNoise() {
        System.out.println("The pig goes oink");
    }

    @Override
    public void eats() {
        System.out.println("The pig eats slops");
    }
}

class Zoo {

    private static void whatDoYouSay(ArrayList<Animal> animals) {
        for(Animal animal : animals) {
            animal.makeNoise();
        }
    }

    private static void whatDoYouEat(ArrayList<Animal> animals) {
        for(Animal animal : animals) {
            animal.eats();
        }
    }

    public static void main(String[] args) {
        ArrayList<Animal> zoo = new ArrayList<Animal>(Arrays.asList(
                new Lion(),
                new Pig()
        ));
        whatDoYouSay(zoo);
        whatDoYouEat(zoo);
    }

}