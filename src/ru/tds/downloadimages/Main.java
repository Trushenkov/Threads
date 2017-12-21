package ru.tds.downloadimages;

import ru.tds.downloader.*;
import java.io.*;

/**
 * Класс, в котором из текстового файла считываются ссылки для скачивания картинок,
 * и создаются и запускаются потоки для их загрузки.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Main {

    private static final String SOURCE_FILE = "src\\ru\\tds\\downloadimages\\LinksForDownload.txt";

    private static final String PATH_TO_IMAGE = "src\\ru\\tds\\downloadimages\\images\\image";

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(SOURCE_FILE))) {

            String link;

            for (int i = 1; (link = reader.readLine()) != null; i++) {

                Downloader downloader = new DownloaderBuilder()
                        .downloadLink(link)
                        .pathToFile(PATH_TO_IMAGE)
                        .format(".jpeg")
                        .number(i)
                        .build();

                downloader.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


