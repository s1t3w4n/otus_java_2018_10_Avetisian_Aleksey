package app;

import app.messages.Message;

import java.io.Closeable;

public interface MessageWorker extends Closeable {

    void send(Message msg);

    Message pool();

    Message take() throws InterruptedException;

    void close();
}
