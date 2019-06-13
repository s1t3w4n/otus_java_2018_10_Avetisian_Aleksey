import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MainHW13fjp extends AbstractSort {
    private final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        MainHW13fjp mainHW13fjp = new MainHW13fjp();
        mainHW13fjp.fillList(100);
        mainHW13fjp.printArray();
        mainHW13fjp.go();
        mainHW13fjp.printArray();

    }

    public void go() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        forkJoinPool.invoke(new SortAction(list, 0, list.size() - 1));
    }

    public class SortAction extends RecursiveAction {

        List<Integer> list;
        int start;
        int end;

        SortAction(List<Integer> list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end <= start) return;

            int j = divide(list, start, end);

            invokeAll(new SortAction(list, start, j - 1), new SortAction(list, j + 1, end));
        }
    }

    private void fillList(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            list.add(random.nextInt(100));
        }
    }

    private void printArray() {
        list.forEach(integer -> System.out.printf("%3d", integer));
        System.out.println();
    }

}
