package ru.tds.readandwritetwothreads;

import java.io.*;

/**
 * Класс, в котором создаются и запускаютя два потока, которые параллельно читают
 * данные из двух разных текстовных файлов и записывают построчно в реультирующий файл.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\ru\\tds\\readandwritetwothreads\\Результат.txt", false));

        ReadAndWrite thread1 = new ReadAndWrite("src\\ru\\tds\\readandwritetwothreads\\1 файл.txt", writer);
        ReadAndWrite thread2 = new ReadAndWrite("src\\ru\\tds\\readandwritetwothreads\\2 файл.txt", writer);

        thread1.start();
        thread2.start();

        if (thread1.isAlive()) {
            thread1.join();
        }

        if (thread2.isAlive()) {
            thread2.join();
        }

        writer.close();

    }
}
