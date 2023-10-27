package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.entity.ComboEntity;
import br.com.fiap.techchallenge.infrastructure.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ComboService {

    private ProdutoRepository produtoRepository;

    private ComboRepository comboRepository;

    public Combo criaCombo(ComboRequest comboRequest) {
        List<Produto> produtos = comboRequest.getProdutos().stream()
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

    public List<Combo> listaCombos() {
        return comboRepository.findAll().stream()
                .map(ComboEntity::toDomain)
                .toList();
    }
}
