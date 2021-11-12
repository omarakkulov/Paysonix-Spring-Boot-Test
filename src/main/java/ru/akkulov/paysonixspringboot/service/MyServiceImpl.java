package ru.akkulov.paysonixspringboot.service;

import com.google.common.hash.Hashing;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.akkulov.paysonixspringboot.model.MyResponse;
import ru.akkulov.paysonixspringboot.model.Signature;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Service
@Data
public class MyServiceImpl implements MyService {
    private final StringBuilder sb;

    @Value("${app.key}")
    private String key;

    @Override
    public Signature getSignature(int id, TreeMap<String, String> params) {

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        // удаляем последнее значение "&" из конца строки вида "name1=value1&...nameN=valueN"
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length() + 1);
        }

        String strFromParameters = sb.toString();
        String gHmac = getHmacSHA256(key, strFromParameters);

        // решение для пустого json объекта "{}"
        if (strFromParameters.equals("")) {
            strFromParameters = "Data is empty!";
            return new Signature(strFromParameters, strFromParameters);
        }

        // Очищаем данные из стрингбилдера, чтобы при запущенном сервере информация обнулялась,
        // а не записывалась поверх старой
        sb.setLength(0);

        return new Signature(strFromParameters, gHmac);
    }

    @Override
    public MyResponse response(int id, TreeMap<String, String> params) {
        Signature signature = getSignature(id, params);

        return new MyResponse("success", Collections.singletonList(signature));
    }

    @Override
    public String getHmacSHA256(String key, String valueToDigest) {
        return Hashing.hmacSha256(key.getBytes(StandardCharsets.UTF_8))
                .hashString(valueToDigest, StandardCharsets.UTF_8)
                .toString();
    }
}
