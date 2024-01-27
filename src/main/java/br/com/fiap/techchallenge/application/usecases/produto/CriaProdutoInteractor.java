package br.com.fiap.techchallenge.application.usecases.produto;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Produto;

public class CriaProdutoInteractor {

    private final ProdutoGateway produtoGateway;

    public CriaProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto execute(Produto produto) {
        return produtoGateway.cria(produto);
    }
}
