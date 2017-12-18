package ru.tds.downloadmusic;

import java.util.ArrayList;

/**
 * Класс, в котором реализовано создание и запуск потока для скачивания музыки по соответствующей ссылке, взятой из ArrayList`a.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class DownloadFromArrayList extends Thread {

    private int countOfTracks;

    private ArrayList<String> arrayList;

    DownloadFromArrayList(ArrayList<String> arrayList, int countOfTracks) {
        this.arrayList = arrayList;
        this.countOfTracks = countOfTracks;
    }

    @Override
    public void run() {

        for (int i = 0; i < countOfTracks; i++) {

            DownloadThread download = new DownloadThread(arrayList.get(i),(i+1));
            download.start();

        }

    }
}
