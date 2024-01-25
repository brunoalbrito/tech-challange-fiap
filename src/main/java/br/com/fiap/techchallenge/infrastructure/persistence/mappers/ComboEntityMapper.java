package br.com.fiap.techchallenge.infrastructure.persistence.mappers;

import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ComboEntityMapper {
    public ComboResponse toResponse(Combo combo) {
        var comboResponse = ComboResponse.builder();
        comboResponse.id(combo.getId().toString());
        comboResponse.precoTotal(combo.valorTotal());

        comboResponse.produtos(combo.getProdutos().stream().map(
                produto -> ProdutoResponse.builder()
                        .id(produto.getId().toString())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .tipo(produto.getTipo().toString())
                        .ingredientes(produto.getIngredientes().stream().map(
                                ingrediente -> IngredienteResponse.builder()
                                        .id(ingrediente.getId().toString())
                                        .descricao(ingrediente.getDescricao())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()));

        return comboResponse.build();
    }

    public List<ComboResponse> toResponse(List<Combo> combos) {
        return combos.stream().map(combo -> ComboResponse.builder()
                .id(combo.getId().toString())
                .precoTotal(combo.valorTotal())
                .produtos(combo.getProdutos().stream().map(
                        produto -> ProdutoResponse.builder()
                                .id(produto.getId().toString())
                                .nome(produto.getNome())
                                .descricao(produto.getDescricao())
                                .preco(produto.getPreco())
                                .tipo(produto.getTipo().toString())
                                .ingredientes(produto.getIngredientes().stream().map(
                                        ingrediente -> IngredienteResponse.builder()
                                                .id(ingrediente.getId().toString())
                                                .descricao(ingrediente.getDescricao())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()).collect(Collectors.toList())).build()).collect(Collectors.toList());
    }
}
