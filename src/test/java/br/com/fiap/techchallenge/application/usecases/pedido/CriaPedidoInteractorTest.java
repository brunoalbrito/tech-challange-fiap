package br.com.fiap.techchallenge.application.usecases.pedido;

import br.com.fiap.techchallenge.application.gateways.ClienteGateway;
import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.application.gateways.PedidoGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CriaPedidoInteractorTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private CriaPedidoInteractor criaPedidoInteractor;

    @Test
    public void deveCriarPedido() {

        PedidoRequest pedidoRequest = PedidoRequest.builder()
                .clienteId("03189909202")
                .produtosId(Collections.singletonList(UUID.randomUUID()))
                .build();

        Cliente cliente = Cliente.criaCliente("03189909202");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente 1");
        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto 1")
                .preco(BigDecimal.TEN)
                .descricao("Descrição")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();

        List<Produto> produtos = Collections.singletonList(produto);

        when(produtoGateway.buscaPorUuids(anyList())).thenReturn(produtos);
        when(clienteGateway.buscaPorCpf(anyString())).thenReturn(cliente);
        when(pagamentoGateway.cria(any(Pedido.class))).thenReturn(Pagamento.criaPagamento(UUID.randomUUID(), "qrCodeGenerated"));
        when(pedidoGateway.salva(any(Pedido.class))).thenAnswer(invocation -> invocation.<Pedido>getArgument(0));

        Pedido result = criaPedidoInteractor.execute(pedidoRequest);

        assertNotNull(result);
        assertEquals(cliente, result.getCliente());
        assertEquals(produtos, result.getProdutos());
        assertNotNull(result.getPagamento());

        verify(pedidoGateway, times(1)).salva(any(Pedido.class));
    }
}