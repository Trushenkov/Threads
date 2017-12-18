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

    private String downloadLink;

    private int numberOfTrack;

    private static final String PATH_TO_MUSIC = "src\\ru\\tds\\downloadmusic\\music\\track";

    DownloadThread(String downloadLink, int numberOfTrack) {
        this.downloadLink = downloadLink;
        this.numberOfTrack = numberOfTrack;
    }

    @Override
    public void run() {

        try {

            long beforeDownload = System.currentTimeMillis();

            URL url = new URL(downloadLink);

            System.out.println("Загрузка " + numberOfTrack+ " файла началась...");

                try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                     FileOutputStream stream = new FileOutputStream(PATH_TO_MUSIC + String.valueOf(numberOfTrack) + ".mp3")) {

                     stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            long timeDownload = System.currentTimeMillis() - beforeDownload;
            System.out.println("Загрузка " + numberOfTrack + " файла выполнена успешно! " +
                               "Время загрузки : " + (timeDownload / 1000) + " сек. " +
                               "Поток - " + getName());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
