package ru.tds.copyfiles;

/**
 * Класс, демонстрирующий работоспособнось класса CopyFiles.
 */
public class Main {

    private static final String READER = "src\\ru\\tds\\copyfiles\\source.txt";
    private static final String WRITER = "src\\ru\\tds\\copyfiles\\result.txt";
    private static final String WRITER_2 = "src\\ru\\tds\\copyfiles\\result2.txt";

    public static void main(String[] args) throws InterruptedException {

        CopyFiles thread1 = new CopyFiles(READER, WRITER);
        CopyFiles thread2 = new CopyFiles(READER, WRITER_2);
        System.out.println("Параллельное копирование двух файлов :\n");
        startThreads(thread1, thread2);
        joinThreads(thread1, thread2);
        long firstTime = thread1.getTime() + thread2.getTime();
        System.out.println("Время выполнения копирования двух файлов = " + firstTime + "\n");

        thread1 = new CopyFiles(READER, WRITER);
        thread2 = new CopyFiles(READER, WRITER_2);
        System.out.println("Последовательное копирование двух файлов :\n");
        startThread(thread1);
        joinThread(thread1);
        System.out.println("Время выполнения копирования первого файла = " + thread1.getTime());

        startThread(thread2);
        joinThread(thread2);
        System.out.println("Время выполнения копирования второго файла = " + thread2.getTime());
        long secondTime = thread1.getTime() + thread2.getTime();

        System.out.println("Общее прошедшее время : " + (firstTime + secondTime));
    }

    /**
     * Метод для запуска одного потока.
     *
     * @param thread поток
     */
    private static void startThread(CopyFiles thread) {
        thread.start();
    }

    /**
     * Метод для запуска двух потоков.
     *
     * @param thread1 первый поток
     * @param thread2 второй поток

     */
    private static void startThreads(CopyFiles thread1, CopyFiles thread2) {
        thread1.start();
        thread2.start();
    }

    /**
     * Метод, который ждет пока завершат выполнение два потока.
     *
     * @param thread1 перывый поток
     * @param thread2 второй поток
     * @throws InterruptedException исключение
     */
    private static void joinThreads(CopyFiles thread1, CopyFiles thread2) throws InterruptedException {
        if (thread1.isAlive()) {
            thread1.join();
        }
        if (thread2.isAlive()) {
            thread2.join();
        }
    }

    /**
     * Метод, который ждет пока завершит выполнение один поток.
     *
     * @param thread поток
     * @throws InterruptedException исключение
     */
    private static void joinThread(CopyFiles thread) throws InterruptedException {
        if (thread.isAlive()) {
            thread.join();
        }
    }
}

