package app;


import java.io.Closeable;

public interface MessageWorker extends Closeable {

    void send(Message msg);

    Message pool();

    Message take() throws InterruptedException;

    void close();
}
