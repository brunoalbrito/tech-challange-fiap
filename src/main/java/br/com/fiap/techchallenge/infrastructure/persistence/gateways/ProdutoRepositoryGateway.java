package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;

import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Produto> buscaPorUuids(List<UUID> produtosId) {
        List<Produto> produtos = ProdutoEntity.toDomain(produtoRepository.findAllById(produtosId));

        List<UUID> invalidProducts = produtosId.stream()
                .filter(
                        uuid -> !produtos.stream().map(Produto::getId).toList().contains(uuid)
                ).toList();

        if (!invalidProducts.isEmpty()) {
            throw new IllegalArgumentException("Produtos não encontrado:" + invalidProducts);
        }

        return produtos;
    }
}
