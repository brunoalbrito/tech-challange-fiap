package br.com.fiap.techchallenge.infrastructure.controllers.mappers;

import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.IngredienteResponse;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ProdutoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ComboDtoMapper {

    public ComboResponse toResponse(Combo combo) {
        return ComboResponse.builder()
                .id(combo.getId().toString())
                .precoTotal(combo.valorTotal())
                .produtos(toProdutoResponseList(combo.getProdutos()))
                .build();
    }

    public List<ComboResponse> toResponse(List<Combo> combos) {
        return combos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private List<ProdutoResponse> toProdutoResponseList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toProdutoResponse)
                .collect(Collectors.toList());
    }

    private ProdutoResponse toProdutoResponse(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId().toString())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .tipo(produto.getTipo().toString())
                .ingredientes(toIngredienteResponseList(produto.getIngredientes()))
                .build();
    }

    private List<IngredienteResponse> toIngredienteResponseList(List<Ingrediente> ingredientes) {
        return ingredientes.stream()
                .map(this::toIngredienteResponse)
                .collect(Collectors.toList());
    }

    private IngredienteResponse toIngredienteResponse(Ingrediente ingrediente) {
        return IngredienteResponse.builder()
                .id(ingrediente.getId())
                .descricao(ingrediente.getDescricao())
                .build();
    }
}
