# Threads #
Этот репозиторий содержит многопоточные приложения

## EggsOrChicken ##
Содержит программу, разрешающую спор о том, что возникло раньше : "Яйцо или Курица?"<br>
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/eggsorchiken">Яйцо или курица</a>
## CatchingThread ##
Содержит программу, реализующую динамическое изменение приоритетов двух потоков <br>
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/catchingthreads">Догонялки</a>
## Interferens ##
Содержит реализацию программы, демонстрирующей совместный инкремент переменной i двумя потоками
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/interferens">Совместный инкремент переменной i двумя потоками</a>
## Readandwritetwothreads ##
Содержит программу,  позволяющую двумя потоками читать данные из двух разных файлов, затем записать считанные данные в результирующий файл.<br> <a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/readandwritetwothreads">Многопоточное считывание данных из файлов</a>
## Copyfiles ## 
Содержит программу, в которой реализовано:
1. Организовано параллельное копирование двух файлов. Замерено примерное время выполнения кода.
2. Организовано последовательное копирование двух файлов. Замерено примерное время выполнения кода.<br>
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/copyfiles"> Копирование файлов </a>
## Downloadmusic ##
Содержит программу в которой реализовано: 
1. Cчитывание ссылки из файла inFile.txt, и формирование строки с HTML-кодом этой страницы.
2. Поиск в этой строке ссылок для скачивания музыки, в соответствии с шаблоном регулярного выражения.
3. Запись найденных ссылок в ArrayList<String> arraylist.
4. Скачивание музыки с сайта https://zvonko.me/, используя arrayList с готовыми ссылками на скачивание.
5. Загрузка файла происходит с помощью Downloader, который находится в отдельном пакете. <br>
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/downloadmusic"> Качаем музыку </a>

## Downloadimages ##
Содержит программу, в которой реализовано: 
1. Считывание URL картинок из текствого файла LinksForDownload.txt.
2. Создание потоков класса Downloader для скачивания картинки, используя DownloderBuider.
3. Запуск потоков для скачивания картинки по соответсвтвующей ссылке.
4. Вывод результата работы программы, содержащего информацию о времени загрузке картинки.
5. Загрузка файла происходит с помощью Downloader, который находится в отдельном пакете. <br>
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/downloadimages">Качаем картинки</a>

## Downloader ## 
Содержит: 
1. Класс Downloader для загрузки файла по ссылке на скачивание.
2. Класс DownloaderBuilder - Паттерн-Builder для создания объекта класса Downloader. <br>
<a href="https://github.com/Trushenkov/Threads/tree/master/src/ru/tds/downloader"> Загрузчик</a>
