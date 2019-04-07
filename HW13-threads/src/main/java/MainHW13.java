import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainHW13 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainHW13.class);
    private boolean isPrinted = false;
    private boolean isReverseOrder = false;


    public static void main(String[] args) {
        MainHW13 main = new MainHW13();
        new Thread(main::action).start();
        new Thread(main::action).start();

    }

    private synchronized void action() {
        while (true) {
            if (isReverseOrder) {
                reverseCount();
            } else {
                normalCount();
            }
        }
    }

    private void normalCount() {
        for (int i = 1; i <= 10; i++) {
            print(i);
            if (i == 10) {
                isReverseOrder = true;
            }
        }
    }

    private void reverseCount() {
        for (int i = 9; i > 1; i--) {
            print(i);
            if (i == 2) {
                isReverseOrder = false;
            }
        }
    }


    private void print(int i) {
        if (isPrinted) {
            LOGGER.info(Integer.toString(i));
            sleep(1_000);
            isPrinted = false;
            notify();
        } else {
            LOGGER.info(Integer.toString(i));
            sleep(1_000);
            isPrinted = true;
            wait(this);
        }
    }

    private static void wait(Object object) {
        try {
            object.wait();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
