import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MainHW13fjp {
    private final List<Integer> list = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(MainHW13fjp.class);

    public static void main(String[] args) {
        MainHW13fjp mainHW13fjp = new MainHW13fjp();
        mainHW13fjp.fillList(100);
        mainHW13fjp.printArray();
        mainHW13fjp.go();
        mainHW13fjp.printArray();

    }

    private void go() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

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
            if(start >= end) return;
            sort(list,start,end);
        }

        private void sort(List<Integer> list, int start, int end) {
            if (start < end) {
                sort(list, start + 1, end);
                if (list.get(start) <= list.get(end)) {
                    sort(list, start, end - 1);
                } else if (list.get(start) > list.get(end)) {
                    logger.info("swapping");
                    swap(start, end);
                    sort(list, start, end - 1);
                }
            } else if (start == end) {
                return;
            }
        }

        private void bubbleSort() {
            for (int i = 0; i < MainHW13fjp.this.list.size(); i++) {
                for (int j = 0; j < MainHW13fjp.this.list.size() - i - 1; j++) {
                    if (MainHW13fjp.this.list.get(j) > MainHW13fjp.this.list.get(j + 1)) {
                        logger.info("Swapping " + MainHW13fjp.this.list.get(j) + " with " + MainHW13fjp.this.list.get(j + 1));
                        swap(j, j + 1);
                    }
                }
            }
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

    private void swap(Integer index1, Integer index2) {
        Integer temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
