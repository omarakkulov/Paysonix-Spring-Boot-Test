package ru.akkulov.paysonixspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public class MyResponse {

    private String status;

    @JsonProperty("result")
    private List<Signature> signatures;
}


