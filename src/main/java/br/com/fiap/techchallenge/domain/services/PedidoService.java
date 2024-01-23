package br.com.fiap.techchallenge.domain.services;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.PedidoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.PedidoRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final PagamentoService pagamentoService;

    private final ProdutoRepository produtoRepository;

    private final ClienteRepository clienteRepository;

    public Pedido cria(PedidoRequest pedidoRequest) {
        List<Produto> produtos = ProdutoEntity.toDomain(produtoRepository.findAllById(pedidoRequest.getProdutosId()));

        List<UUID> invalidProducts = pedidoRequest.getProdutosId().stream()
                .filter(
                        uuid -> !produtos.stream().map(Produto::getId).toList().contains(uuid)
                ).toList();

        if (!invalidProducts.isEmpty()) {
            throw new IllegalArgumentException("Produtos não encontrado.");
        }
        Cliente cliente = clienteRepository.findById(pedidoRequest.getClienteId()).orElseThrow().toDomain();

        UUID pedidoId = UUID.randomUUID();
        Pagamento pagamento = pagamentoService.cria(pedidoId);
        Pedido pedido = Pedido.criaPedido(pedidoId, cliente, produtos, pagamento);
        return pedidoRepository.save(PedidoEntity.toEntity(pedido)).toDomain();
    }

    public Pedido pedidoPago(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow().toDomain();
        if (pagamentoService.estaPago(pedidoId)) {
            pedido.pagamentoRecebido();
            return pedidoRepository.save(PedidoEntity.toEntity(pedido)).toDomain();
        }

        throw new RuntimeException("De acordo com o provedor de pagamento, esse pedido não está pago");
    }

    public Pedido buscar(UUID pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."))
                .toDomain();
    }

    public Pedido entregue(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow().toDomain();
        pedido.entregue();

        return pedidoRepository.save(PedidoEntity.toEntity(pedido)).toDomain();
    }

    public Pedido preparoFinalizado(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow().toDomain();
        pedido.preparoFinalizado();

        return pedidoRepository.save(PedidoEntity.toEntity(pedido)).toDomain();
    }
}
