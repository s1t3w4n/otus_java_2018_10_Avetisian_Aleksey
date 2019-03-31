import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class MainHW05 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainHW05.class);
    public static void main(String[] args) throws InterruptedException {
        List<GarbageCollectorMXBean> gcBean = ManagementFactory.getGarbageCollectorMXBeans();
        LOGGER.info("Starting the program...");
        showInformation(gcBean);
        List<Integer> list = new ArrayList<>();

        for (int j = 0; j < 10_000_000; j++) {
            list.add(j);
            if (j % 1_000_000 == 0) {
                LOGGER.info("Elements: " + j);
                Thread.sleep(4_000);
                showInformation(gcBean);
            }
            //out of memory
            /*if (j % 2 == 0) {
                list.set(j, new Integer(j + 1));
            }*/
        }
    }

    private static void showInformation(List<GarbageCollectorMXBean> gcBean) {

        LOGGER.info("Garbage collector monitoring");

        for (GarbageCollectorMXBean manager : gcBean) {
            LOGGER.info("Memory manager:" + manager.getName());
            LOGGER.info("Time used:" + (manager.getCollectionTime()/1000) + "seconds");
            LOGGER.info("Count:" + manager.getCollectionCount());
        }
    }
}
