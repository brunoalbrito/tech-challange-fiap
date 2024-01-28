package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;

import java.util.List;
import java.util.UUID;

public class CriaPedidoInteractor {

    private final PedidoGateway pedidoGateway;

    private final ProdutoGateway produtoGateway;

    private final ClienteGateway clienteGateway;

    private final PagamentoGateway pagamentoGateway;

    public CriaPedidoInteractor(PedidoGateway pedidoGateway, ProdutoGateway produtoGateway, ClienteGateway clienteGateway, PagamentoGateway pagamentoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.clienteGateway = clienteGateway;
        this.pagamentoGateway = pagamentoGateway;
    }

    public Pedido execute(PedidoRequest pedidoRequest) {
        List<Produto> produtos = produtoGateway.buscaPorUuids(pedidoRequest.getProdutosId());

        Cliente cliente = clienteGateway.buscaPorCpf(pedidoRequest.getClienteId());

        UUID pedidoId = UUID.randomUUID();
        Pedido pedido = Pedido.criaPedido(pedidoId, cliente, produtos);
        Pagamento pagamento = pagamentoGateway.cria(pedido);
        pedido.registaPagamento(pagamento);

        return pedidoGateway.salva(pedido);
    }
}
