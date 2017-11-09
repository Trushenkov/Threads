package ru.tds.catchingthreads;

/**
 * Класс, в котором выполяняется первый поток и меняется его приоритет.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class FirstThread extends Thread {
    public void run() {
        Thread thread = Thread.currentThread();
        thread.setPriority(1);
        for (int i = 0; i < 2000; i++) {
            try {
                sleep(5);
            } catch (InterruptedException e) {
            }
            System.out.println("Первый поток " + i + " км.");
            if (i == 500) {
                thread.setPriority(10);
            }
        }
    }
}
