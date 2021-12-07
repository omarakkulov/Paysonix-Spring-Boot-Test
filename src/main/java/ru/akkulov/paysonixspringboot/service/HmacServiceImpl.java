package ru.akkulov.paysonixspringboot.service;

import com.google.common.hash.Hashing;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.akkulov.paysonixspringboot.exception.BadRequestException;
import ru.akkulov.paysonixspringboot.model.Response;
import ru.akkulov.paysonixspringboot.model.Signature;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Service
@Data
public class HmacServiceImpl implements HmacService {
    private final StringBuilder sb;

    @Value("${HMAC_KEY}")
    private String key;

    @Override
    public Signature getSignature(int id, TreeMap<String, String> params) {
        if (params.isEmpty()) {
            throw new BadRequestException("Data is empty!");
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(String.format("%s=%s&", entry.getKey(), entry.getValue()));
        }

        // remove the last value "&" from the end of the string like "name1=value1&...nameN=valueN"
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        String strFromParameters = sb.toString();
        String gHmac = getHmacSHA256(key, strFromParameters);

        // clear data from stringBuilder so that when the server is running,
        // the information is reset to zero, instead of overwriting the old one
        sb.setLength(0);

        return new Signature(strFromParameters, gHmac);
    }

    @Override
    public Response response(int id, TreeMap<String, String> params) {
        Signature signature = getSignature(id, params);

        return new Response("success", Collections.singletonList(signature));
    }

    @Override
    public String getHmacSHA256(String key, String valueToDigest) {
        return Hashing.hmacSha256(key.getBytes(StandardCharsets.UTF_8))
                .hashString(valueToDigest, StandardCharsets.UTF_8)
                .toString();
    }
}
