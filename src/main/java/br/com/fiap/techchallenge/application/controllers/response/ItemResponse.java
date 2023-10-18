package br.com.fiap.techchallenge.application.controllers.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ItemResponse {
    private String id;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
}
