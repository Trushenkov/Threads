package ru.tds.catchingthreads;

/**
 * Класс, в котором выполяняется второй поток и меняется его приоритет.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class SecondThread extends Thread{
    public void run() {
        Thread thread = Thread.currentThread();
        thread.setPriority(10);
        for (int i = 0; i < 2000; i++) {
            try {
                thread.sleep(5);
            } catch (InterruptedException e) {
            }
            System.out.println("Второй поток " + i + " км.");
            if (i == 700) {
                thread.setPriority(1);
            }
        }
    }
}
