package ru.tds.catchingthreads;

/**
 * Класс, в котором созданы и запущены два потока.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class Demo {
    public static void main(String[] args) {
        CatchingThread thread1 = new CatchingThread(1,10);
        CatchingThread thread2 = new CatchingThread(10,1);

        thread1.start();
        thread2.start();
    }
}
