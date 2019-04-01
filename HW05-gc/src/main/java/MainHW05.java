import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.LinkedList;
import java.util.List;

public class MainHW05 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainHW05.class);

    public static void main(String[] args) throws InterruptedException {
        List<GarbageCollectorMXBean> gcBean = ManagementFactory.getGarbageCollectorMXBeans();
        LOGGER.info("Starting the program...");
        List<Integer> list = new LinkedList<>();
        long beginTime = System.currentTimeMillis();
        long minute = System.currentTimeMillis();
        boolean selector = false;


        for (int j = 0; j < Integer.MAX_VALUE; j++) {
            if (j % 5_000_000 == 0) {
                if (selector) {
                    selector = false;
                } else {
                    selector = true;
                }
            }
            if (selector) {
                list.add(j);
            } else {
                list.remove(0);
            }
            if (System.currentTimeMillis() - minute > 60 * 1000) {
                minute = System.currentTimeMillis();
                LOGGER.info("Time of work:" + (minute - beginTime) / 1000 + " sec");
                showInformation(gcBean);
            }
            if ((System.currentTimeMillis() - beginTime) >= 300 * 1_000) {
                LOGGER.info("Time of work: 5 min");
                break;
            }
        }
        LOGGER.info("Last results: ");
        showInformation(gcBean);
        LOGGER.info("Ending the program...");
    }

    private static void showInformation(List<GarbageCollectorMXBean> gcBean) {

        LOGGER.info("===============Garbage collector monitoring===============");

        for (GarbageCollectorMXBean manager : gcBean) {
            LOGGER.info("Memory manager: " + manager.getName());
            LOGGER.info("Time used: " + (manager.getCollectionTime() / 1000) + "seconds");
            LOGGER.info("Count: " + manager.getCollectionCount());
        }
    }
}
