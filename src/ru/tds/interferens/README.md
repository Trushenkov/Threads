# Совместное использование ресурсов #
В этой программе реализовано:
<ol>
<li>Создан класс, расширяющий класс Thread, для организации инкремента переменной i</li>
<li>Созданы два потока, выполняющих инкремент переменной i</li>
<li>Каждый поток увеличивает значение i 1000 раз</li>
<li>Выводится значение, полученное при совместном инкременте переменной i двумя
потоками</li>
<li>Модифицирован код с использованием модификаторов volatile, static, synchronized, для того чтобы
добиться корректного инкремента переменной i двумя потоками</li>
</ol>