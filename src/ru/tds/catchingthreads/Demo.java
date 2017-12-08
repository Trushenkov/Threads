package ru.tds.catchingthreads;

/**
 * Класс, в котором созданы и запущены два потока, демонстрирующие динамическое изменение приоритетов двух потоков.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class Demo {
    public static void main(String[] args) {
        Threads thread1 = new Threads(1,10);
        Threads thread2 = new Threads(10,1);

        thread1.start();
        thread2.start();

    }
}
