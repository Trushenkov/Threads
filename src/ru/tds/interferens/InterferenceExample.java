package ru.tds.interferens;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс, в котором создаются и запускаются два потока для инкремента переменной i 1000 раз каждым потоком.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class InterferenceExample {
    public static final int TWO_THOUSANDS = 2_000;
    private AtomicInteger counter = new AtomicInteger();

    public boolean stop() {
        return counter.incrementAndGet() > TWO_THOUSANDS;
    }

    public void example() throws InterruptedException {
        InterferenceThread thread1 = new InterferenceThread("Поток 1 - ", this);
        InterferenceThread thread2 = new InterferenceThread("Поток 2 - ", this);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Ожидаем: " + TWO_THOUSANDS);
        System.out.println("Получили: " + thread1.getI());
    }
}
