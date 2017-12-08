package ru.tds.catchingthreads;

/**
 * Класс для создания и выполнения потоков.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class CatchingThread extends Thread {

    private int firstPriority;
    private int secondPriority;

    private static final int COUNT_OF_STEPS = 2000;
    private static final int TIME_OF_DELAY = 5;
    private static final int STEPS_FOR_CHANGE_PRIORITY = 500;

    CatchingThread(int firstPriority, int secondPriority) {
        this.firstPriority = firstPriority;
        this.secondPriority = secondPriority;
    }

    public void run() {
        setPriority(firstPriority);
        for (int i = 0; i < COUNT_OF_STEPS; i++) {
            try {
               sleep(TIME_OF_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() +" шаг "+ i );
            if (i == STEPS_FOR_CHANGE_PRIORITY) {
                setPriority(secondPriority);
            }
        }
    }
}
