package ru.geekbrains.lesson5;

import java.util.concurrent.*;

public class MainClass {
/*
 1. Все участники должны стартовать одновременно, несмотря на разное время  подготовки.
 2. В тоннель не может одновременно заехать больше половины участников (условность).
    Попробуйте все это синхронизировать.
    Когда все завершат гонку, нужно выдать объявление об окончании.
    Можно корректировать классы (в том числе конструктор машин)
    и добавлять объекты классов из пакета util.concurrent.
*/

    public static final int CARS_COUNT = 4;
    public static boolean isWinner = false;
    public static ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);

    public static void main(String[] args) throws BrokenBarrierException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT + 1,
                ()->System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));
        Semaphore smp = new Semaphore(CARS_COUNT / 2);
        CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
        Race race = new Race(new Road(60), new Tunnel(smp), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, cdl);
        }

        for (int i = 0; i < cars.length; i++) {
//            new Thread(cars[i]).start();
            executorService.execute(cars[i]);
        }

        try {
            cb.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            executorService.shutdown();
        }
    }

}

