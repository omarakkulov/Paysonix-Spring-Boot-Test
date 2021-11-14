package ru.akkulov.paysonixspringboot.service;

import org.springframework.stereotype.Component;
import ru.akkulov.paysonixspringboot.model.Response;
import ru.akkulov.paysonixspringboot.model.Signature;

import java.util.TreeMap;

@Component
public interface MyService {

    /**
     * Returns an object with the calculated Hmac value inside
     *
     * @param id     operationId from url
     * @param params parameters sorted by key from request body
     * @return type {@link Signature}
     */
    Signature getSignature(int id, TreeMap<String, String> params);

    /**
     * Returns an object for the HTTP response
     *
     * @param id     operationId operationId from url
     * @param params parameters sorted by key from request body
     * @return type {@link Response}
     */
    Response response(int id, TreeMap<String, String> params);

    /**
     * Returns Hmac by key from a string
     *
     * @param key           algorithm key
     * @param valueToDigest the string to be hashed
     * @return {@link String}
     */
    String getHmacSHA256(String key, String valueToDigest);
}
