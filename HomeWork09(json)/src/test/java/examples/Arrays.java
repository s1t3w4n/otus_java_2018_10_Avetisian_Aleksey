package examples;

import java.util.Random;

public class Arrays {
    int[] ints;
    String[][] strings;
    //Primitives[] primitives;// to fix - make a loop

    public Arrays() {
        ints = generateInts();
        strings = new String[][]{{"asd", "dsa", "123"},
                {"asd", "dsa", "123", "123"},
                {"asd", "123"}};
        /*primitives = new Primitives[3];
        primitives[0] = new Primitives();
        primitives[1] = new Primitives();
        primitives[2] = new Primitives();*/
    }

    private static int[] generateInts() {
        Random random = new Random();
        int[] temp = new int[random.nextInt(123)];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = random.nextInt();
        }
        return temp;
    }
}