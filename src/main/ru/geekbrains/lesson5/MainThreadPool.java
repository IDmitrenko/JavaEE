package ru.geekbrains.lesson5;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThreadPool {

    public static void main(String[] args) throws InterruptedException {

        final RejectedExecutionHandler rejectHandler = (r, executor) -> {
            System.out.println("Reject " + r);
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                2,
                1L,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                rejectHandler);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
//          long timeStart;
//          timeStart = System.currentTimeMillis();
                    TimeUnit.SECONDS.sleep(10L);
//          System.out.println("submit " + i + " ended.", System.currentTimeMillis() - timeStart);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("submit " + i);
        }

        executor.shutdown();
    }
}
