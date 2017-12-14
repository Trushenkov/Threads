package ru.tds.downloadmusic;

import java.io.*;
import java.net.URL;
import java.nio.channels.*;

/**
 * Класс, в котором реализовано скачивание музыки, используя  URL адреса
 * для скачивания музыки из текствого файла outFIle.txt,
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ReadLinksAndDownloadThread extends Thread {

    private String addressOutputFile;

    private static final String PATH_TO_MUSIC = "src\\ru\\tds\\downloadmusic\\music\\music";

    ReadLinksAndDownloadThread(final String addressOutputFile) {
        this.addressOutputFile = addressOutputFile;
    }

    @Override
    public void run() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(addressOutputFile))) {

            long beforeDownload = System.currentTimeMillis();

            String music;
            int count = 0;

            while ((music = musicFile.readLine()) != null) {
                System.out.println("Загрузка " + (count + 1) + " файла началась...");
                downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count + 1) + ".mp3");
                count++;
                long timeDownload = System.currentTimeMillis() - beforeDownload;
                System.out.println("Загрузка выполнена успешно! Время загрузки : " + (timeDownload / 1000) + " сек.");
                beforeDownload = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, который осуществляет скачивание музыки по ссылке для скачивания.
     *
     * @param strUrl URL для скачивания музыки
     * @param file   имя для скачанного файла
     * @throws IOException исключение
     */
    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
             FileOutputStream stream = new FileOutputStream(file)) {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        }
    }
}
