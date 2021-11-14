package ru.akkulov.paysonixspringboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.akkulov.paysonixspringboot.model.Response;
import ru.akkulov.paysonixspringboot.model.Signature;

import java.util.TreeMap;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MyServiceTest {
    private StringBuilder sb;
    private MyServiceImpl service;

    @BeforeEach
    public void beforeEach() {
        sb = new StringBuilder();
        service = new MyServiceImpl(sb);
    }

    @Test
    void getSignature() {
        Signature signature;
        service.setKey("hmacKey");

        int id = 1;

        TreeMap<String, String> map = new TreeMap<>();
        map.put("FGH", "five");
        map.put("ABC", "one");
        map.put("BCA", "two");

        //////////
        signature = service.getSignature(id, map);
        assertThat(signature.getStrFromParameters()).isEqualTo("ABC=one&BCA=two&FGH=five");

        map.put("LMN", "six");
        map.put("DBA", "three");
        map.put("EFG", "four");
        map.put("OPQ", "seven");

        //////////
        signature = service.getSignature(id, map);
        assertThat(signature.getStrFromParameters())
                .isEqualTo("ABC=one&BCA=two&DBA=three&EFG=four&FGH=five&LMN=six&OPQ=seven");
    }

    @Test
    void response() {
        int id = 1;
        service.setKey("hmacKey");

        TreeMap<String, String> map = new TreeMap<>();
        map.put("FGH", "five");
        map.put("ABC", "one");
        map.put("BCA", "two");
        map.put("LMN", "six");
        map.put("DBA", "three");
        map.put("EFG", "four");
        map.put("OPQ", "seven");

        Response response = service.response(id, map);
        assertThat(response.getSignatures().size()).isEqualTo(1);
    }

    @Test
    void getHmacSHA256() {
        String key1 = "hmacKey1";
        String key2 = "hmacKey2";
        String key3 = "hmacKey3";
        String key4 = "hmacKey4";
        String key5 = "hmacKey5";

        String valueToDigest = "a=animal&b=black&c=cool&g=green&y=yellow";
        String hmac;

        hmac = service.getHmacSHA256(key1, valueToDigest);
        assertThat(hmac).isEqualTo("db84544a606f824f3c9092053e2408ab41c35fcaf1dbfca69d64cf6ec86a0513");

        hmac = service.getHmacSHA256(key2, valueToDigest);
        assertThat(hmac).isEqualTo("2b6401fcd15ecaa8bfd9ef4b33f09f47a01346923c1123cf5d54dc9c8fff2527");

        hmac = service.getHmacSHA256(key3, valueToDigest);
        assertThat(hmac).isEqualTo("fafeeb6466b5f80c8264950d0ce07d845e8b895b1d90f5ba1a6bc6489bfbac90");

        hmac = service.getHmacSHA256(key4, valueToDigest);
        assertThat(hmac).isEqualTo("7d945f084395553db911d70806d14e7c9bbcfbaad9d7cf4ed1a9b356cc8565bb");

        hmac = service.getHmacSHA256(key5, valueToDigest);
        assertThat(hmac).isEqualTo("cc2bd11aee7ffbc5b1c9c97c1e9a115d60af21e1c04aa03d69a95ca303505202");
    }
}