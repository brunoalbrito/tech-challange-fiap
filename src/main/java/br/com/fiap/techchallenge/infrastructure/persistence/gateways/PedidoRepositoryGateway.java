package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.PedidoRepository;

import java.util.UUID;

public class PedidoRepositoryGateway implements PedidoGateway {

    private final PedidoRepository pedidoRepository;

    public PedidoRepositoryGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscaPorUUID(UUID pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."))
                .toDomain();
    }
}
