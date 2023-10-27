package br.com.fiap.techchallenge.application.controllers.request;


import br.com.fiap.techchallenge.domain.enums.TipoPagamento;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PagamentoRequest {

    private String idPedido;
    private TipoPagamento tipoPagamento;
    private BigDecimal valorTotal;
}
