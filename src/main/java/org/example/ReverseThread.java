package org.example;
import java.util.concurrent.CountDownLatch;
public class ReverseThread extends Thread {
    private final CountDownLatch latch;
    private final int threadNumber;

    public ReverseThread(CountDownLatch latch, int threadNumber) {
        this.latch = latch;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Hello from thread " + threadNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
