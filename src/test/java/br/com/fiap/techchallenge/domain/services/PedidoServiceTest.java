package br.com.fiap.techchallenge.domain.services;


import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ClienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.PedidoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ClienteRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.PedidoRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Deprecated
@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void deveCriarPedido() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "descricao");

        Produto lanche = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.LANCHE).build();
        Produto bebida = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.BEBIDA).build();
        Produto acompanhamento = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.ACOMPANHAMENTO).build();

        Cliente cliente = Cliente.criaCliente("28603211795");

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qrCode");

        PedidoRequest pedidoRequest = PedidoRequest.builder()
                .clienteId(UUID.randomUUID().toString())
                .produtosId(List.of(lanche.getId(), bebida.getId(), acompanhamento.getId()))
                .build();


        when(produtoRepository.findAllById(pedidoRequest.getProdutosId())).thenReturn(List.of(
                ProdutoEntity.toEntity(lanche),
                ProdutoEntity.toEntity(bebida),
                ProdutoEntity.toEntity(acompanhamento)
        ));

        when(clienteRepository.findById(pedidoRequest.getClienteId())).thenReturn(Optional.of(
                ClienteEntity.criaEntity(cliente)
        ));

        when(pagamentoService.cria(any(UUID.class))).thenReturn(pagamento);

        when(pedidoRepository.save(any()))
                .thenAnswer(invocation -> invocation.<Pedido>getArgument(0));

        Pedido pedido = pedidoService.cria(pedidoRequest);

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertNotNull(pedido.getCliente());
        assertNotNull(pedido.getProdutos());
        assertNotNull(pedido.getPagamento());

        verify(pagamentoService).cria(any(UUID.class));
        verify(pedidoRepository).save(any());
        verify(produtoRepository).findAllById(any());
        verify(clienteRepository).findById(any());
    }

    @Test
    public void naoDeveCriarPedidoCasoProdutoNaoExista() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "descricao");
        Produto lanche = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.LANCHE).build();
        Produto bebida = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.BEBIDA).build();
        Produto acompanhamento = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.ACOMPANHAMENTO).build();

        PedidoRequest pedidoRequest = PedidoRequest.builder()
                .clienteId(UUID.randomUUID().toString())
                .produtosId(List.of(UUID.randomUUID(), bebida.getId(), acompanhamento.getId()))
                .build();

        when(produtoRepository.findAllById(pedidoRequest.getProdutosId())).thenReturn(List.of(
                ProdutoEntity.toEntity(lanche),
                ProdutoEntity.toEntity(bebida),
                ProdutoEntity.toEntity(acompanhamento)
        ));

        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.cria(pedidoRequest);
        });
    }

    @Test
    public void deveBuscarPedidoPorId() {
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "descricao");
        Produto lanche = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.LANCHE).build();
        Produto bebida = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.BEBIDA).build();
        Produto acompanhamento = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.ACOMPANHAMENTO).build();


        Cliente cliente = Cliente.criaCliente("28603211795");

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qrCode");

        UUID id = UUID.randomUUID();

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(
                PedidoEntity.toEntity(Pedido.criaPedido(id, cliente, List.of(lanche, bebida, acompanhamento), pagamento))
        ));


        Pedido pedido = pedidoService.buscar(id);

        assertEquals(id, pedido.getId());
    }

    @Test
    public void naoDeveRetornarPedidoCasoNaoExista() {
        UUID id = UUID.randomUUID();

        when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.buscar(id);
        });
    }
}
