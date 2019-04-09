import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainHW13 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainHW13.class);
    private volatile boolean isPrinted = false;
    private volatile boolean isReverseOrder = false;


    public static void main(String[] args) {
        MainHW13 main = new MainHW13();
        new Thread(main::action).start();
        new Thread(main::action).start();

    }

    private synchronized void action() {
        while (true) {
            if (isReverseOrder) {
                counter(Order.REVERSE);
            } else {
                counter(Order.NORMAL);
            }
        }
    }

    private void counter(Order order) {
        if (order.equals(Order.NORMAL)) {
            for (int i = 1; i <= 10; i++) {
                print(i);
                if (i == 10) {
                    isReverseOrder = true;
                }
            }
        } else if(order.equals(Order.REVERSE)) {
            for (int i = 9; i > 1; i--) {
                print(i);
                if (i == 2) {
                    isReverseOrder = false;
                }
            }
        }
    }

    private synchronized void print(int i) {
        if (isPrinted) {
            LOGGER.info(Integer.toString(i));
            sleep(1_000);
            isPrinted = false;
            notify();
            wait(this);
        } else {
            LOGGER.info(Integer.toString(i));
            sleep(1_000);
            isPrinted = true;
            notify();
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

    private enum Order {
        NORMAL(1),
        REVERSE(-1);
        private final int value;

        Order(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
