package ru.tds.downloadmusic;

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

    private static final String LINK_SITE_TXT = "src\\ru\\tds\\downloadmusic\\inFile.txt";

    public static void main(String[] args) throws InterruptedException, IOException {

        new DownloadFromArrayList(BuildingArrayList(), 10).start();

    }

    /**
     * Метод, который в строке с HTML-кодом страницы, используя шаблон регулярного выражения,
     * находит готовые ссылки на скачивание музыки и записывает их в ArrayList.
     *
     * @return ArrayList с готовыми ссылками на скачивание музыки
     * @throws IOException исключение
     */
    private static ArrayList<String> BuildingArrayList() {

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
            Matcher matcher = email_pattern.matcher(parseLink());

            while (matcher.find()) {
                arrayList.add(matcher.group());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    /**
     * Метод, который считывает ссылку из файла inFile.txt, и формирует строку с HTML-кодом этой страницы.
     *
     * @return result строка с HTML-кодом страницы
     * @throws IOException исключение
     */
    private static String parseLink() throws IOException {

        String urlString, result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(LINK_SITE_TXT))) {
            while ((urlString = reader.readLine()) != null) {
                URL url = new URL(urlString);
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
            }

        }
        return result;
    }
}
