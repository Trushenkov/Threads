package ru.tds.copyfiles;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Класс, демонстрирующий работоспособнось класса CopyFiles.
 */
public class Main {

    private static final String READER = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\istochnik.txt";
    private static final String WRITER = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\result.txt";
    private static final String WRITER_2 = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\result2.txt";

    public static void main(String[] args) throws InterruptedException {

        CopyFiles thread1 = new CopyFiles(READER, WRITER);
        CopyFiles thread2 = new CopyFiles(READER, WRITER_2);
        System.out.println("Параллельное копирование двух файлов : \n " );
        startThreads(thread1, thread2);
        joinTwoThreads(thread1, thread2);
        long firstTime = thread1.getTime() + thread2.getTime();
        System.out.println("Время выполнения копирования двух файлов = " + firstTime + " мс. \n");

        thread1 = new CopyFiles(READER, WRITER);
        thread2 = new CopyFiles(READER, WRITER_2);
        System.out.println("Последовательное копирование двух файлов : \n " );
        startThread(thread1);
        joinOneThread(thread1);
        System.out.println("Время выполнения копирования первого файла = " + thread1.getTime() + " мс.");

        startThread(thread2);
        joinOneThread(thread2);
        System.out.println("Время выполнения копирования второго файла = " + thread2.getTime() + " мс.");
        AtomicLong secondTime = new AtomicLong(thread1.getTime() + thread2.getTime());

        System.out.println("Общее прошедшее время : " + (firstTime + secondTime.get()) + " мс.");
    }

    /**
     * Метод для старта одного потока, посылаемого в параметры метода
     *
     * @param thread поток
     * @throws InterruptedException исключение
     */
    private static void startThread(CopyFiles thread) throws InterruptedException {
        thread.start();
    }

    /**
     * Метод для старта двух потоков, посылаемых в параметры метода
     * @param thread1 первый поток
     * @param thread2 второй поток
     * @throws InterruptedException исключение
     */
    private static void startThreads(CopyFiles thread1, CopyFiles thread2) throws InterruptedException {
        thread1.start();
        thread2.start();
    }

    /**
     * Метод для ожидания завершения потока, пока он работает.
     *
     * @param thread1 перывый поток
     * @param thread2 второй поток
     * @throws InterruptedException исключение
     */
    private static void joinTwoThreads(CopyFiles thread1, CopyFiles thread2) throws InterruptedException {
        if (thread1.isAlive()) {
            thread1.join();
        }
        if (thread2.isAlive()) {
            thread2.join();
        }
    }

    /**
     * Метод для ожидания завершения потока, пока он работает.
     *
     * @param thread поток
     * @throws InterruptedException исключение
     */
    private static void joinOneThread(CopyFiles thread) throws InterruptedException {
        if (thread.isAlive()) {
            thread.join();
        }
    }
}
