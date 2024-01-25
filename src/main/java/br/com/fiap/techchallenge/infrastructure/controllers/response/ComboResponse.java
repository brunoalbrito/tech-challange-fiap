package br.com.fiap.techchallenge.infrastructure.controllers.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComboResponse {
    private String id;

    private BigDecimal precoTotal;

    private List<ProdutoResponse> produtos;
}
