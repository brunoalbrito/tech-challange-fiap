package br.com.fiap.techchallenge.infrastructure.controllers.response;

import br.com.fiap.techchallenge.domain.Produto;
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

    private List<IngredienteResponse> ingredientes;

    public static ProdutoResponse fromDomain(final Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId().toString())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .tipo(produto.getTipo().getValue())
                .ingredientes(getIngredientesResponse(produto))
                .build();
    }

    private static List<IngredienteResponse> getIngredientesResponse(Produto produto) {
        return produto.getIngredientes()
                .stream()
                .map(IngredienteResponse::fromDomain)
                .toList();
    }
}
