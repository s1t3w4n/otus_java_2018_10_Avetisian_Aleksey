import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractSort {
    private final Logger logger = LoggerFactory.getLogger(AbstractSort.class);

    public abstract void go();

    protected int divide(List<Integer> list, int start, int end) {
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
}