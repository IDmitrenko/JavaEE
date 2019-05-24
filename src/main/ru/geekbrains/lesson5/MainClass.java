package ru.geekbrains.lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
    public static void main(String[] args) throws BrokenBarrierException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT + 1,
                ()->System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            cb.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

}

