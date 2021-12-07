package ru.akkulov.paysonixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akkulov.paysonixspringboot.model.Response;
import ru.akkulov.paysonixspringboot.service.HmacService;

import java.util.TreeMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HmacController {
    private final HmacService service;

    @PostMapping("hmac/{operationId}")
    public ResponseEntity<Response> response(@PathVariable int operationId,
                                             @RequestBody TreeMap<String, String> params) {

        return new ResponseEntity<>(service.response(operationId, params), HttpStatus.OK);
    }
}
