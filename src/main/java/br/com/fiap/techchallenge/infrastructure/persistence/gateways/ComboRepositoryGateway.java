package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.mappers.ComboDtoMapper;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.response.ComboResponse;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ComboEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;

import java.util.List;
import java.util.UUID;

public class ComboRepositoryGateway implements ComboGateway {

    private final ProdutoRepository produtoRepository;

    private final ComboRepository comboRepository;

    private final ComboDtoMapper comboDtoMapper;

    public ComboRepositoryGateway(ProdutoRepository produtoRepository, ComboRepository comboRepository,
                                  ComboDtoMapper comboDtoMapper) {
        this.produtoRepository = produtoRepository;
        this.comboRepository = comboRepository;
        this.comboDtoMapper = comboDtoMapper;
    }

    @Override
    public ComboResponse criaCombo(ComboRequest comboRequest) {
        List<Produto> produtos = comboRequest
                .getProdutos()
                .stream()
                .map(UUID::fromString)
                .map(this::findProdutoById)
                .map(ProdutoEntity::toDomain)
                .toList();

        Combo combo = Combo.criaCombo(UUID.randomUUID(), produtos);
        comboRepository.save(ComboEntity.criaComboEntity(combo));
        return comboDtoMapper.toResponse(combo);
    }

    @Override
    public List<ComboResponse> listaCombos() {
        List<Combo> combos = comboRepository.findAll().stream()
                .map(ComboEntity::toDomain)
                .toList();
        return comboDtoMapper.toResponse(combos);
    }

    private ProdutoEntity findProdutoById(UUID produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
    }
}
