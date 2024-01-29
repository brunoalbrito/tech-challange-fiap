package br.com.fiap.techchallenge.application.usecases.produto;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;

import java.util.List;
import java.util.UUID;

public class CriaProdutoInteractor {

    private final ProdutoGateway produtoGateway;

    private final IngredienteGateway ingredienteGateway;

    public CriaProdutoInteractor(final ProdutoGateway produtoGateway, IngredienteGateway ingredienteGateway) {
        this.produtoGateway = produtoGateway;
        this.ingredienteGateway = ingredienteGateway;
    }

    public Produto execute(final ProdutoRequest produtoRequest) {

        List<Ingrediente> ingredientes = ingredienteGateway.buscaIngredientesPorId(produtoRequest.getIngredientes());

        UUID id = UUID.randomUUID();


        final Produto produto = Produto.builder()
                .id(id)
                .nome(produtoRequest.getNome())
                .preco(produtoRequest.getPreco())
                .descricao(produtoRequest.getDescricao())
                .ingredientes(ingredientes)
                .tipo(produtoRequest.getTipo().toDomain())
                .build();

        return produtoGateway.cria(produto);
    }
}
