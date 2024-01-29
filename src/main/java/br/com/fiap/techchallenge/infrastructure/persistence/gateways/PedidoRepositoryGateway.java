package br.com.fiap.techchallenge.infrastructure.persistence.gateways;

import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.PedidoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.PedidoRepository;

import java.util.List;
import java.util.UUID;

public class PedidoRepositoryGateway implements PedidoGateway {

    private final PedidoRepository pedidoRepository;

    public PedidoRepositoryGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscaPorUUID(UUID pedidoId) {
        return pedidoRepository.findById(pedidoId).map(PedidoEntity::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
    }

    public Pedido salva(Pedido pedido) {
        PedidoEntity pedidoEntity = PedidoEntity.toEntity(pedido);
        return pedidoRepository.save(pedidoEntity).toDomain();
    }

    @Override
    public List<Pedido> listaPedido() {
        return pedidoRepository.findAll().stream()
                .filter(pedido -> !pedido.getStatus().equals(StatusPedido.ENTREGUE))
                .map(PedidoEntity::toDomain)
                .toList();
    }
}
