package ru.akkulov.paysonixspringboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ru.akkulov.paysonixspringboot.model.exchange_rate.YandexMoney;
import ru.akkulov.paysonixspringboot.service.YandexMoneyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class YandexMoneyController {

  private final YandexMoneyService yandexMoneyService;

  @GetMapping("/usd_and_eur_values")
  public DeferredResult<ResponseEntity<YandexMoney>> getUSDAndEurValues() {
    DeferredResult<ResponseEntity<YandexMoney>> deferredResult = new DeferredResult<>();
    deferredResult.setResult(new ResponseEntity<>(yandexMoneyService.usdAndEurValuesYandex(), HttpStatus.OK));

    log.info("success response from thread:'{}'", Thread.currentThread().getName());

    return deferredResult;
  }
}
