<table>
  <tr><th>Версия Java</th><th>Система сборки</th><th>База данных</th></tr>
  <tr><td>17</td><td>Gradle</td><td>H2</td>
</table>
<div>Проверка Junit тестов</div>
<pre>
  gradle test
</pre>
<div>Сборка и запуск</div>
<pre>
  gradle assemble
  java -jar .\build\libs\demo-0.0.1-SNAPSHOT.jar
</pre>

<div>Пример post запроса для букв kind=letters</div>
<img src=https://github.com/devochkazaika/Intervals/assets/96948110/8e177de1-5e3a-4307-8a82-202fefbd7f59 />
<div>Пример post запроса для чисел kind=digits</div>
<img src=https://github.com/devochkazaika/Intervals/assets/96948110/6c8ce9f5-43d9-44b3-b4a1-eb9627cd88e4 />
<div>Пример get запроса для букв kind=letter</div>
<img src=https://github.com/devochkazaika/Intervals/assets/96948110/51ce2cf0-9224-4a70-b75c-97b92cf3996d />
<div>Пример get запроса для чисел kind=digits</div>
<img src=https://github.com/devochkazaika/Intervals/assets/96948110/580a7f28-add6-4c75-9024-72b96f95e78a/>


<div>Особенности</div>
<div>В запросах для букв используются только буквы(как в условии задачи). Если в интервале будут другие символы тогда http.status = 400, а также вернется сообщение illegal letter. При хотя бы одном неправильном интервале операции с остальными интервалами не выполнятся. Также если в запросах для чисел будут использоваться не только числа</div>
<img src=https://github.com/devochkazaika/Intervals/assets/96948110/57ed78c2-d861-4eb7-89f6-fc3fbc0c545b/>

<p/>
<div>Также http.status=400 и ошибки будут выкидываться при</div>
<ul>
    <li>нуллевом массиве в json, например []</li>
    <li>Неправильном интервале, например, [[1, 2, 3], [1, 2]] или [["a", "b", "c"]]</li>
    <li>Неправильном интервале, где start > end, например, [[4, 1]] или [["z", "a"]]</li>
</ul>

<p/>
<div>Если в бд есть несколько интервалов с минимальным стартом или минимальным концом - ничего не выведется, при этом http.status=200</div>
<p/>

<div>Особенности в коде</div>
<ul>
    <li>Использовал дженерики для более чистого кода и более удобного расширения</li>
    <li>Интервал создается на основе базового конструктора, который вызывает сеттеры для start и end. Эти методы переопределяются в классах насследниках</li>
    <li>Для бд исползовал миграции из библиотеки flyway</li>
    <li>Для чисел использовал тип Long</li>
</ul>

