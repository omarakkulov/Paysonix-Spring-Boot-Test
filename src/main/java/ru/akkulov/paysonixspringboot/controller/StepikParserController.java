package ru.akkulov.paysonixspringboot.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ru.akkulov.paysonixspringboot.common.StepikParser;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class StepikParserController {

  private final StepikParser stepikParser;

  @GetMapping("/stepik_parse")
  public DeferredResult<ResponseEntity<List<String>>> getUSDAndEurValues() throws InterruptedException {
    DeferredResult<ResponseEntity<List<String>>> deferredResult = new DeferredResult<>();
    deferredResult.setResult(new ResponseEntity<>(stepikParser.parse(), HttpStatus.OK));

    log.info("success response from thread:'{}'", Thread.currentThread().getName());

    return deferredResult;
  }
}
