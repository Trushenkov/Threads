package ru.tds.eggsorchiken;

/**
 * Класс, в котором реализовано решение спора: "Что возникло раньше яйцо или курица?"
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class EggOrChicken extends Thread{
    public void run() {
        for (int i = 1; i < 10; i++) {
            try {
                setPriority(1);
                sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("яйцо");
        }
    }

    public static void main(String[] args) {
        System.out.println("Спор начат...");
        Thread thread = new Thread(new EggOrChicken());
        thread.start();
        for (int i = 1; i < 10; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("курица");
        }

        if (thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println("Первым появилось яйцо!");
        } else {
            System.out.println("Первой появилась курица!");
        }
        System.out.println("Спор закончен!");
    }
}
