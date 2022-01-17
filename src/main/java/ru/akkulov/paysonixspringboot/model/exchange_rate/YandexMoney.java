package ru.akkulov.paysonixspringboot.model.exchange_rate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@Builder(setterPrefix = "with")
public class YandexMoney {

  private final String usdValue;

  private final String eurValue;
}
