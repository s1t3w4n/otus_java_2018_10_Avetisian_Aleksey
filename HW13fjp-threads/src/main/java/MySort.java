import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MySort implements Sort {
    private final List<Integer> list;
    private final Logger logger = LoggerFactory.getLogger(MySort.class);

    public MySort(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void go() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        forkJoinPool.invoke(new SortAction(list, 0, list.size() - 1));
    }

    private int divide(List<Integer> list, int start, int end) {
        int i = start;
        int j = end + 1;
        while (true) {
            while (less(list.get(++i), list.get(start))) {
                if (i == end) {
                    break;
                }
            }
            while (less(list.get(start), list.get(--j))) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(list, i, j);
        }
        swap(list, start, j);
        return j;
    }

    private void swap(List<Integer> list, int i, int j) {
        logger.info("swapping:" + list.get(i) + " " + list.get(j));
        Integer temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private boolean less(Integer i, Integer v) {
        return i < v;
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
}
