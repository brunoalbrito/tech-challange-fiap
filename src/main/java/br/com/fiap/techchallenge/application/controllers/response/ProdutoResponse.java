package br.com.fiap.techchallenge.application.controllers.response;

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
public class ProdutoResponse {
    private String id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private String tipo;

    private List<String> ingredientes;
}
