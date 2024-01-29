package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ComboEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;

import java.util.List;
import java.util.UUID;

public class ComboRepositoryGateway implements ComboGateway {

    private final ProdutoRepository produtoRepository;

    private final ComboRepository comboRepository;

    public ComboRepositoryGateway(ProdutoRepository produtoRepository, ComboRepository comboRepository) {
        this.produtoRepository = produtoRepository;
        this.comboRepository = comboRepository;
    }

    @Override
    public Combo salva(Combo combo) {
        return comboRepository.save(ComboEntity.criaComboEntity(combo)).toDomain();
    }

    @Override
    public List<Combo> listaCombos() {
        return comboRepository.findAll().stream()
                .map(ComboEntity::toDomain)
                .toList();
    }

    private ProdutoEntity findProdutoById(UUID produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
    }
}
