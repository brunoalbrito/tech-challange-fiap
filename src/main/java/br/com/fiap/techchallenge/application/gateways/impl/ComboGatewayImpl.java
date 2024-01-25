package br.com.fiap.techchallenge.application.gateways.impl;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ComboEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ComboGatewayImpl implements ComboGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ComboRepository comboRepository;
    @Override
    public Combo criaCombo(ComboRequest comboRequest) {
        List<Produto> produtos = comboRequest
                .getProdutos()
                .stream()
                .map(UUID::fromString)
                .map(produtoId ->
                        produtoRepository.findById(produtoId)
                                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado.")))
                .map(ProdutoEntity::toDomain)
                .toList();

        Combo combo = Combo.criaCombo(UUID.randomUUID(), produtos);
        comboRepository.save(ComboEntity.criaComboEntity(combo));

        return combo;
    }

    @Override
    public List<Combo> listaCombos() {
        return comboRepository.findAll().stream()
                .map(ComboEntity::toDomain)
                .toList();
    }
}
