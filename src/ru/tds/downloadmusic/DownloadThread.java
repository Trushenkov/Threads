package ru.tds.downloadmusic;

import java.io.*;
import java.net.*;
import java.nio.channels.*;

/**
 * Класс, в котором реализована загрузка музыки по ссылке на скачивание.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class DownloadThread extends Thread {

    private int numberOfTrack;

    private String downloadLink;

    private String nameOfTrack;


    DownloadThread(String downloadLink, String nameOfTrack, int numberOfTrack) {
        this.downloadLink = downloadLink;
        this.nameOfTrack = nameOfTrack;
        this.numberOfTrack = numberOfTrack;
    }

    @Override
    public void run() {

        long beforeDownload = System.currentTimeMillis();

        try {

            URL url = new URL(downloadLink);

            try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                 FileOutputStream stream = new FileOutputStream(nameOfTrack)) {

                stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

            } catch (IOException e) {
                e.printStackTrace();
            }

            long timeDownload = System.currentTimeMillis() - beforeDownload;
            System.out.println("Загрузка " + numberOfTrack + " файла выполнена успешно! Время загрузки : " + (timeDownload / 1000) + " сек. Поток - " + getName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
