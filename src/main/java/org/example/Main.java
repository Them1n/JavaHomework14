package org.example;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 50;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        ReverseThread[] threads = new ReverseThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new ReverseThread(latch, i);
            threads[i].start();
        }

        // Count down the latch in reverse order
        for (int i = numberOfThreads - 1; i >= 0; i--) {
            latch.countDown();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("*****************");
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        int mid = numbers.length / 2;

        Callable<Integer> task1 = new PrimeCounterTask(numbers, 0, mid);
        Callable<Integer> task2 = new PrimeCounterTask(numbers, mid, numbers.length);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> result1 = executor.submit(task1);
        Future<Integer> result2 = executor.submit(task2);

        try {

            int count1 = result1.get();
            int count2 = result2.get();

            System.out.println("Thread 1 found " + count1 + " prime numbers.");
            System.out.println("Thread 2 found " + count2 + " prime numbers.");

            int totalCount = count1 + count2;
            System.out.println("Total prime numbers: " + totalCount);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}