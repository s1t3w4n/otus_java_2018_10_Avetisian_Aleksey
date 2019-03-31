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
        List<Integer> list = new ArrayList<>();

        for (int j = 0; j < 5_000_000; j++) {
            list.add(j);
            //out of memory
            /*if (j % 2 == 0) {
                list.set(j, new Integer(j + 1));
            }*/
        }
        showInformation(gcBean);
    }

    private static void showInformation(List<GarbageCollectorMXBean> gcBean) {

        LOGGER.info("Garbage collector monitoring");

        for (GarbageCollectorMXBean manager : gcBean) {
            LOGGER.info("Memory manager: " + manager.getName());
            LOGGER.info("Time used: " + (manager.getCollectionTime() / 1000) + "seconds");
            LOGGER.info("Count: " + manager.getCollectionCount());
        }
    }
}
