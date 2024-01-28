package br.com.fiap.techchallenge.application.gateways;

import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface PagamentoGateway {
    Pagamento cria(Pedido pedido);

    boolean estaPago(UUID pedidoId);
}
