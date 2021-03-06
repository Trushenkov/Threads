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

    @Override
    public void run() {
        long beforeStart = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(result))) {

            String string;
            while ((string = reader.readLine()) != null) {
                writer.write(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - beforeStart;
    }
}
