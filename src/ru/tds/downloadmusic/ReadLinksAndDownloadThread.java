package ru.tds.downloadmusic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс, в котором реализовано чтение текстового файла outFIle.txt, содержаещего ссылки
 * для скачивания музыки, а также создание и запуск потока для скачивания музыки по соответствующей ссылке.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ReadLinksAndDownloadThread extends Thread {

    private String addressOutputFile;

    private static final String PATH_TO_MUSIC = "src\\ru\\tds\\downloadmusic\\music\\music";

    ReadLinksAndDownloadThread(String addressOutputFile) {
        this.addressOutputFile = addressOutputFile;
    }

    @Override
    public void run() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(addressOutputFile))) {

            String linkForDownload;

            for (int i = 1; (linkForDownload = musicFile.readLine()) != null; i++) {
                System.out.println("Загрузка " + i + " файла началась...");
                DownloadThread download = new DownloadThread(linkForDownload, PATH_TO_MUSIC + String.valueOf(i) + ".mp3", i);
                download.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
