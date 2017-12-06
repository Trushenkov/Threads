package ru.tds.copyfiles;

import java.io.*;

/**
 * Класс для копирования одного файла в другой файл по указанному пути.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class CopyFiles extends Thread {
    private String source;
    private String result;
    private long time;

    CopyFiles(String source, String result) {
        this.source = source;
        this.result = result;
    }

    long getTime() {
        return time;
    }

    public void run() {
        String string;
        long beforeStart = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(result))) {
            while ((string = reader.readLine()) != null) {
                writer.write(string + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - beforeStart;
        System.out.println("Время выполнения "  + getName() + " потока = " + time + " мс.");
    }
}
