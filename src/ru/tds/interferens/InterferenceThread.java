package ru.tds.interferens;

/**
 * Класс потоков.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class InterferenceThread extends Thread {
    private final InterferenceExample checker;
    private static volatile int i;

    public InterferenceThread(String name, InterferenceExample checker) {
        super(name);
        this.checker = checker;
    }

    public void run() {
        System.out.println(this.getName() + " запущен");
        while (!checker.stop()) {
            increment();
        }
        System.out.println(this.getName() + " завершен");
    }

    /**
     * Метод для инкремента переменной i.
     */
    private static synchronized void increment() {
        i++;
    }

    public int getI() {
        return i;
    }
}
