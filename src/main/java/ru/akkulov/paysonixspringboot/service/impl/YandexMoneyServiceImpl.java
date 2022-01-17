package ru.akkulov.paysonixspringboot.service.impl;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.akkulov.paysonixspringboot.model.exchange_rate.YandexMoney;
import ru.akkulov.paysonixspringboot.service.YandexMoneyService;

@Service
@Slf4j
public class YandexMoneyServiceImpl implements YandexMoneyService {

  @Override
  public YandexMoney usdAndEurValuesYandex() {
    String URL = "https://yandex.ru/";
    Document doc = null;
    try {
      doc = Jsoup.connect(URL).get();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Elements exchangeRates = doc.getElementsByClass("stocks-new__item-value");

    return YandexMoney.builder()
        .withUsdValue(exchangeRates.get(0).text())
        .withEurValue(exchangeRates.get(1).text())
        .build();
  }
}
