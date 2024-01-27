package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoGateway {
    Produto cria(Produto produto);

    List<Produto> buscaPorUuids(List<UUID> produtosId);
}
