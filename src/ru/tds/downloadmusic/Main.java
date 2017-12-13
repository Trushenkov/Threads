package ru.tds.downloadmusic;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 * Класс, в котором реализвано скачивание музыки.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class Main {

    private static final String LINK_SITE_TXT = "src\\ru\\tds\\downloadmusic\\inFile.txt";
    private static final String DOWNLOAD_LINK_TXT = "src\\ru\\tds\\downloadmusic\\outFile.txt";
    private static final String PATH_TO_MUSIC = "src\\ru\\tds\\downloadmusic\\music\\music";

    public static void main(String[] args) {

        BuildingDownloadLinks();

        ReadLinksAndDownload();

    }

    /**
     * Метод считывает исходный файл inFile.txt, содержащий ссылку на сайт,
     * откуда нужно скачать музыку.
     * И используя шаблон регулярных выражений, формирует файл outFile.txt,
     * куда записывает готовые ссылки на скачивание музыки.
     */
    private static void BuildingDownloadLinks() {
        try (BufferedReader inFile = new BufferedReader(new FileReader(LINK_SITE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(DOWNLOAD_LINK_TXT))) {
            String Url;
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);
                String result;
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                        result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*(?=\")");
                Matcher matcher = email_pattern.matcher(result);
                int i = 0;
                while (matcher.find() && i < 4) {
                    outFile.write(matcher.group() + "\n");
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, в котором читается файл, содержащий URL адреса для скачивания музыки,
     * вызывает метод downloadUsingNIO, который осуществляет скачивание музыки по ссылке для скачивания.
     */
    private static void ReadLinksAndDownload() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(DOWNLOAD_LINK_TXT))) {
            String music;
            int count = 0;
                while ((music = musicFile.readLine()) != null) {
                    downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count + 1) + ".mp3");
                    count++;
                    System.out.println("Скачалось файлов - " + count);
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