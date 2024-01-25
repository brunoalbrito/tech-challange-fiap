package br.com.fiap.techchallenge.infrastructure.controllers.mappers;

import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProdutoRequestMapper {

    public Produto toProduto(ProdutoRequest request, List<Ingrediente> ingredientes) {
        return Produto.criaProduto(UUID.randomUUID(), request.getNome(), request.getPreco(), request.getDescricao(), ingredientes, Tipo.fromValue(request.getTipo().getValue()));
    }

    public ProdutoResponse toResponse(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId().toString())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .tipo(produto.getTipo().getValue())
                .ingredientes(produto.getIngredientes().stream().map(
                        ingrediente -> IngredienteResponse.builder()
                                .id(ingrediente.getId().toString())
                                .descricao(ingrediente.getDescricao())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }
}
