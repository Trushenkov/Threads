package ru.tds.readandwritetwothreads;

import java.io.*;

/**
 * Класс, в котором реализовано многопоточное считывание данных из файлов и запись в результирующий файл.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class ReadAndWrite extends Thread {

    private String way;

    private static volatile BufferedWriter writer;

    ReadAndWrite(String way, BufferedWriter writer) {
        this.way = way;
        ReadAndWrite.writer = writer;
    }

    public void run() {

        long before = System.currentTimeMillis();
        String string;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(way))) {
            while ((string = bufferedReader.readLine()) != null) {
                writeString(string);
                yield();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();
        System.out.println((after - before) % 1000);
    }

    /**
     * Метод для записи строки в файл "Результат.txt"
     *
     * @param string строка, которую нужно записать в файл
     * @throws IOException исключение
     */
    private static synchronized void writeString(String string) throws IOException {

        writer.write(string + "\n");

    }

}
