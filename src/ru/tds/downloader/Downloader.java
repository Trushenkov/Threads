package ru.tds.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс для загрузки картинки по URL ссылке.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Downloader extends Thread {

    private String downloadLink;

    private String pathToFile;

    private String format;

    private int number;

    Downloader(DownloaderBuilder downloaderBuilder) {
        this.downloadLink = downloaderBuilder.getDownloadLink();
        this.pathToFile = downloaderBuilder.getPathToFile();
        this.format = downloaderBuilder.getFormat();
        this.number = downloaderBuilder.getNumber();
    }

    @Override
    public void run() {

        try {

            long beforeDownload = System.currentTimeMillis();

            URL url = new URL(downloadLink);
            System.out.println("Загрузка " + number + " файла началась...");
            try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                 FileOutputStream stream = new FileOutputStream(pathToFile + String.valueOf(number) + format)) {

                stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

            } catch (IOException e) {
                e.printStackTrace();
            }

            long timeDownload = System.currentTimeMillis() - beforeDownload;
            System.out.println("Загрузка " + number + " файла выполнена успешно! " +
                               "Время загрузки : " + (timeDownload / 1000) + " сек. ");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
