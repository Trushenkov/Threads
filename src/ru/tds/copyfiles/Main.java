package ru.tds.copyfiles;

/**
 * Класс, демонстрирующий работоспособнось класса CopyFiles.
 */
public class Main {

    private static final String ADDRESS_READER = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\istochnik.txt";
    private static final String ADDRESS_WRITER = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\result.txt";
    private static final String ADDRESS_WRITER_2 = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\result2.txt";

    public static void main(String[] args) throws InterruptedException {

        CopyFiles file1 = new CopyFiles(ADDRESS_READER, ADDRESS_WRITER);
        CopyFiles file2 = new CopyFiles(ADDRESS_READER, ADDRESS_WRITER_2);
        System.out.println("Параллельное копирование двух файлов : \n " );
        startTwoThreads(file1, file2);
        joinForTwoThreads(file1, file2);
        long timeOfFirst = file1.getTime() + file2.getTime();
        System.out.println("Время выполнения копирования двух файлов = " + timeOfFirst + " мс.");
        System.out.println("");
        file1 = new CopyFiles(ADDRESS_READER, ADDRESS_WRITER);
        file2 = new CopyFiles(ADDRESS_READER, ADDRESS_WRITER_2);
        System.out.println("Последовательное копирование двух файлов : \n " );
        startOneThread(file1);
        joinForOneThread(file1);
        System.out.println("Время выполнения копирования первого файла = " + file1.getTime() + " мс.");

        startOneThread(file2);
        joinForOneThread(file2);
        System.out.println("Время выполнения копирования второго файла = " + file2.getTime() + " мс.");
        long timeOfSecond = file1.getTime() + file2.getTime();

        System.out.println("Общее прошедшее время : " + (timeOfFirst + timeOfSecond) + " мс.");
    }

    private static void startOneThread(CopyFiles file) throws InterruptedException {
        file.start();
    }

    private static void startTwoThreads(CopyFiles file1, CopyFiles file2) throws InterruptedException {
        file1.start();
        file2.start();
    }

    private static void joinForTwoThreads(CopyFiles file1, CopyFiles file2) throws InterruptedException {
        if (file1.isAlive()) {
            file1.join();
        }
        if (file2.isAlive()) {
            file2.join();
        }
    }

    private static void joinForOneThread(CopyFiles file) throws InterruptedException {
        if (file.isAlive()) {
            file.join();
        }
    }
}
