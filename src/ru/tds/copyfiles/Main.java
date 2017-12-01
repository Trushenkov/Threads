package ru.tds.copyfiles;

/**
 * Класс, демонстрирующий работоспособнось класса CopyFiles.
 */
public class Main {

    public static final String ADDRESS_READER = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\istochnik.txt";
    public static final String ADDRESS_WRITER = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\result.txt";
    public static final String ADDRESS_WRITER_2 = "C:\\Users\\dmitry\\IdeaProjects\\Threads\\src\\ru\\tds\\copyfiles\\result2.txt";

    public static void main(String[] args) throws InterruptedException {
        long beforeStartProgramm = System.currentTimeMillis();
        CopyFiles file1 = new CopyFiles(ADDRESS_READER, ADDRESS_WRITER);
        CopyFiles file2 = new CopyFiles(ADDRESS_READER, ADDRESS_WRITER_2);
        file1.start();
        file2.start();
        if (file1.isAlive()) {
            file1.join();
        }

        if (file2.isAlive()) {
            file2.join();
        }
        System.out.println("Общее прошедшее время : " + (System.currentTimeMillis() - beforeStartProgramm));
    }
}
