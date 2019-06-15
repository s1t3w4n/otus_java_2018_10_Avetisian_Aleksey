import java.util.List;
import java.util.Random;

public class ListHelper {
    public static void fillList(int count, List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            list.add(random.nextInt(100));
        }
    }

    public static void printArray(List<Integer> list) {
        list.forEach(integer -> System.out.printf("%3d", integer));
        System.out.println();
    }
}
