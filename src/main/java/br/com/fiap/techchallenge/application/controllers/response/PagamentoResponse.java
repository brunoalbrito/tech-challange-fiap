package br.com.fiap.techchallenge.application.controllers.response;

import br.com.fiap.techchallenge.domain.enums.TipoPagamento;
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
public class PagamentoResponse {

    private TipoPagamento tipo;
    private BigDecimal valorTotal;
    // Objeto transacional
}
