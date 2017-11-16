package ru.tds.readandwritetwothreads;

import java.io.*;

/**
 * Класс, в котором реализовано многопоточное считывание данных из файлов из запись в результирующий файл.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class ReadAndWrite extends Thread {
    private String way;
    private static volatile BufferedWriter bufferedWriter;

    public ReadAndWrite(String way) {
        this.way = way;
    }

    public void run() {
        long firstly_time = System.currentTimeMillis();
        String string;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(way))) {
            while ((string = bufferedReader.readLine()) != null) {
                writeString(string);
            }
        } catch (IOException e) {

        }
        System.out.println((System.currentTimeMillis() - firstly_time) % 1000);
    }

    /**
     * Метод для записи строки в файл "Результат.txt"
     *
     * @param string строка, которую нужно записать в файл
     * @throws IOException исключение
     */
    public static synchronized void writeString(String string) throws IOException {
        bufferedWriter.write(string + "\n");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        bufferedWriter = new BufferedWriter(new FileWriter("src\\ru\\tds\\readandwritetwothreads\\Результат.txt"));

        ReadAndWrite thread1 = new ReadAndWrite("src\\ru\\tds\\readandwritetwothreads\\1 файл.txt");
        ReadAndWrite thread2 = new ReadAndWrite("src\\ru\\tds\\readandwritetwothreads\\2 файл.txt");

        thread1.start();
        thread2.start();

        if (thread1.isAlive()) {
            thread1.join();
        }
        if (thread2.isAlive()) {
            thread2.join();
        }
        bufferedWriter.close();
    }
}
