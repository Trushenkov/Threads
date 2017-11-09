package ru.tds.catchingthreads;

/**
 * Класс, в котором выполяняется первый поток и меняется его приоритет.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class FirstThread extends Thread {

    public static final int COUNT_OF_STEPS = 2000;
    public static final int TIME_OF_DELAY = 5;
    public static final int MIN_PRIORITY = 1;
    public static final int MAX_PRIORITY = 10;
    public static final int STEPS_TO_CHANGE_PRIORITY = 500;

    public void run() {
        setPriority(MIN_PRIORITY);
        for (int i = 0; i < COUNT_OF_STEPS; i++) {
            try {
               sleep(TIME_OF_DELAY);
            } catch (InterruptedException e) {
            }
            System.out.println("Первый поток " + i );
            if (i == STEPS_TO_CHANGE_PRIORITY) {
                setPriority(MAX_PRIORITY);
            }
        }
    }
}
