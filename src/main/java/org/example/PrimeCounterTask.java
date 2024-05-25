package org.example;
import java.util.concurrent.Callable;
public class PrimeCounterTask implements Callable<Integer> {
    private final int[] numbers;
    private final int start;
    private final int end;

    public PrimeCounterTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (PrimeUtil.isPrime(numbers[i])) {
                count++;
            }
        }
        return count;
    }
}
