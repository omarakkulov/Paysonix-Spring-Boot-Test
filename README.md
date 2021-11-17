# Paysonix-Spring-Boot-Test

![](https://sun9-62.userapi.com/impg/o5B0xbgYvkM_WMbDHTl0jwFK0zFNJK1tAwEi0Q/65WQ1rbzzCk.jpg?size=1280x736&quality=96&sign=a7fc9892fd04dafd6bc557c2b6b6047f&type=album)

## Подробнее про решение:

1. Для вычисления хэш-функции использовалась реалиазация из библиотеки Google Guava;

2. Для предобработки контроллера использовались фильтры из Apache Tomcat, 
   чтобы пробрасывать исключения на уровне фильтров, а не контроллеров

3. Сам метод контроллера принимает на вход operationId, который не используется в текущей версии приложения. 
   Возможно это будет использовано в будущем. Параметры из тела запроса поступают в TreeMap, 
   которая сортирует данные по ключу, на мой взгляд эта структура данных идеально подходит для решения данного кейса.
   Далее берем строку от этих параметров и вычисляем hmac
   
4. Данные для запуска и работы приложения (токен, ключ для хэш-функции и порт запуска приложения) берутся из переменных окружения
   TOKEN, HMAC_KEY и PORT.
   ![](https://sun9-14.userapi.com/impg/DkEADvsfv-26Xct4TqaCr4ptFIP0CKf9D3rZFw/dmXudvAP2Hw.jpg?size=914x64&quality=96&sign=4dc7371a6561f56598f81ac9ee653e13&type=album)
   ###### TOKEN=some_token;HMAC_KEY=some_hmac_key;PORT=8081;

5. Также были написаны тесты для сервисного слоя приложения в соответствующей для этого директории.

6. Метод правильно отрабатывается, когда приходит простой объект типа ключ-значение:
   
   ![](https://sun9-57.userapi.com/impg/1FXVohbJsr2If980n6QNf4pLNhYzkrvg2pf3cQ/NhtItzWvffU.jpg?size=257x187&quality=96&sign=27e24f2b6949b4410de8c5b7740ef38a&type=album)

   Нет релизации на такой сложный объект, показанный снизу, т.к. для этого надо согласовать все с фронтом.
   ![](https://sun9-56.userapi.com/impg/MDpzZvOajH6Zsf0n1-AL5eynm3KhenBguKfROQ/vdwIYqNhjNE.jpg?size=297x277&quality=96&sign=1939ec9ce731a7b37b033dd322f2a200&type=album)

### Спасибо за внимание!
