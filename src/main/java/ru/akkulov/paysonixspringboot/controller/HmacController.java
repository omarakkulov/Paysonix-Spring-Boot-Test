package ru.akkulov.paysonixspringboot.controller;

import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ru.akkulov.paysonixspringboot.model.Response;
import ru.akkulov.paysonixspringboot.service.HmacService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class HmacController {

  private final HmacService hmacService;

  @PostMapping("hmac/{operationId}")
  public DeferredResult<ResponseEntity<Response>> response(@PathVariable int operationId, @RequestBody TreeMap<String, String> params) {
    return successResponse(operationId, params);
  }

  private DeferredResult<ResponseEntity<Response>> successResponse(int operationId, TreeMap<String, String> params) {
    DeferredResult<ResponseEntity<Response>> deferredResult = new DeferredResult<>();
    deferredResult.setResult(new ResponseEntity<>(hmacService.response(operationId, params), HttpStatus.OK));

    log.info("success response from thread:'{}'", Thread.currentThread().getName());

    return deferredResult;
  }
}
