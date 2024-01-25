package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;

public class ProdutoRepositoryGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryGateway(final ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto cria(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoEntity.toEntity(produto);

        return produtoRepository.save(produtoEntity).toDomain();
    }
}
