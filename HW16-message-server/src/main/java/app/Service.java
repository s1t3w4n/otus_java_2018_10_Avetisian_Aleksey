package app;

import chanel.SocketMessageWorker;
import messageSystem.Address;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface Service {

    Address getAddress();

    default void init(SocketMessageWorker socketMessageWorker) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Message msg = socketMessageWorker.take();
                    System.out.println("Message received: " + msg.toString());
                    msg.exec(this);
                }
            } catch (InterruptedException e) {
            }
        });
    }
}
