# Paysonix-Spring-Boot-Test

## Используемый стек:
Maven, Lombok

## Подробнее:

1. В приложении для вычисления mac от строки я использовал зависимость от Google Guava, 
   так как она очень удобным способом делится методами для этого.

2. Для предобработки контроллера я воспользовался фильтрами из Apache Tomcat для того, 
   чтобы пробрасывать исключения на уровне фильтров, а не контроллеров

3. Сам метод контроллера принимает на вход operationId, который, по правде говоря, даже и не используется в приложении. 
   Возможно это просто был некоторый небольшой пункт для реализации. Параметры из тела запроса поступают в TreeMap, 
   которая сортирует данные по ключу, на мой взгляд эта структура данных идеально подходит для решения данного кейса.
   Далее берем строку от этих параметров и вычисляем hmac
   
4. Для сравнения значений токена из заголовков запроса с нашим значением, использовался .properties файл. 
   Теперь же данные берутся из переменных окружения. Соответственно, в переменные окружения нужно вписать:
   ###### TOKEN=some_token;HMAC_KEY=some_hmac_key;
   ,где some_token - значение токена (но клют от него будет x-token в самом запросе), 
   some_hmac_key - значение ключа для алгоритма.

5. Также были написаны тесты для сервисного слоя приложения в соответствующей для этого директории.

6. Приложение находится на порте 8081, измените при надобности:
      ###### server.port=${Свое значение}

### Спасибо за внимание!
