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
        firstStep(thread1);
        long firstStepTime = thread1.getTime();
        System.out.println("1 ЭТАП.\nВремя выполнения копирования файла = " + firstStepTime);

        thread1 = new CopyFiles(READER, WRITER);
        CopyFiles thread2 = new CopyFiles(READER, WRITER_2);
        secondStep(thread1, thread2);
        long secondStepTime = thread1.getTime() + thread2.getTime();
        System.out.println("2 ЭТАП.\nВремя выполнения параллельного копирования двух файлов = " + secondStepTime);

        thread1 = new CopyFiles(READER, WRITER);
        thread2 = new CopyFiles(READER, WRITER_2);
        thirdStep(thread1, thread2);
        long thirdStepTime = thread1.getTime() + thread2.getTime();
        System.out.println("3 ЭТАП.\nВремя выполнения последовательного копирования двух файлов = " + (thread1.getTime() + thread2.getTime()));

        System.out.println("Общее прошедшее время : " + (firstStepTime + secondStepTime + thirdStepTime));
    }

    /**
     * Метод для запуска потока и ожидания, пока он завершится.
     *
     * @param thread1 поток
     * @throws InterruptedException исключение
     */
    private static void firstStep(CopyFiles thread1) throws InterruptedException {
        thread1.start();
        if (thread1.isAlive()) {
            thread1.join();
        }
    }

    /**
     * Метод для параллельного запуска двух потоков и ожидания, пока они завершатся.
     *
     * @param thread1 поток
     * @param thread2 поток
     * @throws InterruptedException исключение
     */
    private static void secondStep(CopyFiles thread1, CopyFiles thread2) throws InterruptedException {
        thread1.start();
        thread2.start();
        if (thread1.isAlive()) {
            thread1.join();
        }
        if (thread2.isAlive()) {
            thread2.join();
        }
    }

    /**
     * Метод для последовательного запуска двух потоков и ожидания, пока они завершатся.
     *
     * @param thread1 поток
     * @param thread2 поток
     * @throws InterruptedException исключение
     */
    private static void thirdStep(CopyFiles thread1, CopyFiles thread2) throws InterruptedException {
        thread1.start();
        if (thread1.isAlive()) {
            thread1.join();
        }
        thread2.start();
        if (thread2.isAlive()) {
            thread2.join();
        }
    }
}

