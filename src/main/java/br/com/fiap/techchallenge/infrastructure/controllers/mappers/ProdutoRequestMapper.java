package br.com.fiap.techchallenge.infrastructure.controllers.mappers;

import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;

import java.util.List;

public class ProdutoRequestMapper {

    public ProdutoResponse toResponse(Produto produto) {
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
