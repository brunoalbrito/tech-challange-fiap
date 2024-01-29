package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;

import java.util.List;
import java.util.UUID;

public class CriaComboInteractor {

    private final ComboGateway comboGateway;

    private final ProdutoGateway produtoGateway;

    public CriaComboInteractor(final ComboGateway comboGateway, ProdutoGateway produtoGateway) {
        this.comboGateway = comboGateway;
        this.produtoGateway = produtoGateway;
    }

    public Combo execute(ComboRequest comboRequest) {
        List<UUID> Uuids = comboRequest.getProdutos()
                .stream()
                .map(UUID::fromString)
                .toList();

        List<Produto> produtos = produtoGateway.buscaPorUuids(Uuids);
        Combo combo = Combo.criaCombo(UUID.randomUUID(), produtos);

        return comboGateway.salva(combo);
    }
}