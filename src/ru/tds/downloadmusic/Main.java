package ru.tds.downloadmusic;

import ru.tds.downloader.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 * Класс, в котором реализовано выполнение задачи "Качаем музыку".
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Main {

    private static final String SOURCE_FILE = "src\\ru\\tds\\downloadmusic\\inFile.txt";

    private static final String PATH_TO_FILE = "src\\ru\\tds\\downloadmusic\\music\\track";

    public static void main(String[] args) {

        StartDownload(BuildingArrayList(), 10);

    }

    /**
     * Метод для создания и запуска потоков для загрузки музыки по соответствующей ссылке из ArrayList'a.
     *
     * @param arrayList ArrayList, заполненный ссылками для скачивания музыки
     * @param countOfDownloads количество треков, которое нужно загрузить
     */
    private static void StartDownload(ArrayList<String> arrayList, int countOfDownloads) {

        String link;

        for (int i = 0; (link = arrayList.get(i)) != null && i < countOfDownloads; i++) {

            Downloader downloadThread = new DownloaderBuilder()
                                        .downloadLink(link)
                                        .pathToFile(PATH_TO_FILE)
                                        .format(".mp3")
                                        .number(i+1)
                                        .build();
            downloadThread.start();

        }

    }

    /**
     * Метод, который в строке с HTML-кодом страницы, используя шаблон регулярного выражения,
     * находит готовые ссылки на скачивание музыки и записывает их в ArrayList.
     *
     * @return ArrayList с готовыми ссылками на скачивание музыки
     */
    private static ArrayList<String> BuildingArrayList() {

        ArrayList<String> arrayList = new ArrayList<>();

        Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
        Matcher matcher = email_pattern.matcher(parseLink());

        while (matcher.find()) {
            arrayList.add(matcher.group());
        }

        return arrayList;
    }

    /**
     * Метод, который считывает ссылку из файла inFile.txt, и формирует строку с HTML-кодом этой страницы.
     *
     * @return result строка с HTML-кодом страницы.
     */
    private static String parseLink() {

        String urlString, result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(SOURCE_FILE))) {
            while ((urlString = reader.readLine()) != null) {
                URL url = new URL(urlString);
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
