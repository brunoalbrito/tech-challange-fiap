package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.response.PedidoRequest;
import br.com.fiap.techchallenge.application.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.repository.ClienteRepository;
import br.com.fiap.techchallenge.infrastructure.repository.PedidoRepository;
import br.com.fiap.techchallenge.infrastructure.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoService;

    private final PagamentoService pagamentoService;

    private final ProdutoRepository produtoRepository;

    private final ClienteRepository clienteRepository;

    public Pedido cria(PedidoRequest pedidoRequest) {
        List<Produto> produtos = pedidoRequest.getProdutos().stream()
                .map(ProdutoRequest::getId)
                .map(UUID::fromString)
                .map(id -> produtoRepository.findById(id).orElse(null))
                .filter(Objects::nonNull).map(Produto::toDomain).toList();


//        Cliente cliente = clienteRepository.findById(pedidoRequest.getCliente().getCpf()).orElse(null).toDomain();
//
        Pagamento pagamento = pagamentoService.cria();

        UUID id = UUID.randomUUID();
        Pedido pedido = Pedido.criaPedido(id, null, produtos, pagamento);
        pedidoService.save(pedido.toEntity());
        return pedido;
    }
}
