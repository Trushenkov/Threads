package ru.tds.downloader;

/**
 * Паттерн-Builder для пошагового создания потока Downloader.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class DownloaderBuilder {

    private String downloadLink;

    private String pathToFile;

    private String format;

    private int number;

    public DownloaderBuilder downloadLink(String link){
        this.downloadLink = link;
        return this;
    }

    public DownloaderBuilder pathToFile(String path){
        this.pathToFile = path;
        return this;
    }

    public DownloaderBuilder format(String format){
        this.format = format;
        return this;
    }

    public DownloaderBuilder number(int number) {
        this.number = number;
        return this;
    }

    public Downloader build(){
        return new Downloader(this);
    }

    String getDownloadLink() {
        return downloadLink;
    }

    String getPathToFile() {
        return pathToFile;
    }

    String getFormat() {
        return format;
    }

    int getNumber() {
        return number;
    }
}
