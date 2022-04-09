package com.fiap.challengefiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultError {

    @JsonProperty("Status:")
    private int Code;

    @JsonProperty("Mensagem:")
    private String mensagem;
}
