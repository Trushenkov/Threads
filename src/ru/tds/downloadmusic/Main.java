package ru.tds.downloadmusic;

import java.io.*;
import java.net.URL;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 * Класс, в котором реализовано выполнение задачи "Качаем музыку".
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Main {

    private static final String LINK_SITE_TXT = "src\\ru\\tds\\downloadmusic\\inFile.txt";

    private static final String OUTPUT_FILE_TXT = "src\\ru\\tds\\downloadmusic\\outFile.txt";

    public static void main(String[] args) throws InterruptedException {

        BuildingDownloadLinks();
        new ReadLinksAndDownloadThread(OUTPUT_FILE_TXT).start();
    }

    /**
     * Метод на основе файла inFile.txt, содержащего ссылку на сайт, откуда нужно скачать музыку,
     * используя шаблон регулярного выражения, формирует текстовый файл outFile.txt, содержащий готовые ссылки на скачивание.
     */
    private static void BuildingDownloadLinks() {
        String urlString,  result;

        try (BufferedReader inFile = new BufferedReader(new FileReader(LINK_SITE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(OUTPUT_FILE_TXT))) {

            while ((urlString = inFile.readLine()) != null) {
                URL url = new URL(urlString);

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }

                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
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
}
