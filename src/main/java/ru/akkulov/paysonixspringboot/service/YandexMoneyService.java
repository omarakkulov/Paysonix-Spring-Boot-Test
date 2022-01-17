package ru.akkulov.paysonixspringboot.service;

import org.springframework.stereotype.Component;
import ru.akkulov.paysonixspringboot.model.exchange_rate.YandexMoney;

@Component
public interface YandexMoneyService {

  YandexMoney usdAndEurValuesYandex();
}
