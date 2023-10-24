package br.com.fiap.techchallenge.application.controllers.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QrCodeRequest {

    private String sku;
    private String titulo;
    private String categoria;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private String urlNotificacao;
}
