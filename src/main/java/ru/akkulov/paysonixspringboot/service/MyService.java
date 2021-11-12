package ru.akkulov.paysonixspringboot.service;

import org.springframework.stereotype.Component;
import ru.akkulov.paysonixspringboot.model.MyResponse;
import ru.akkulov.paysonixspringboot.model.Signature;

import java.util.TreeMap;

@Component
public interface MyService {

    /**
     * Возвращает объект с вычисленным значением Hmac внутри
     *
     * @param id     operationId из url запроса
     * @param params отсортированные по ключу параметры типа ключ-значение из тела запроса
     * @return объект типа {@link Signature}
     */
    Signature getSignature(int id, TreeMap<String, String> params);

    /**
     * Возвращает объект для HTTP ответа
     *
     * @param id     operationId из url запроса
     * @param params отсортированные по ключу параметры из тела запроса
     * @return объект типа {@link MyResponse}
     */
    MyResponse response(int id, TreeMap<String, String> params);

    /**
     * Возвращает Hmac по ключу от строки, поступающей в параметрах метода
     *
     * @param key           ключ шифрования
     * @param valueToDigest строка, которая будет шифроваться
     * @return {@link String}
     */
    String getHmacSHA256(String key, String valueToDigest);
}
