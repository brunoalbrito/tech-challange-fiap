package br.com.fiap.techchallenge.infrastructure.network.client;

import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.network.data.PagamentoClient;
import br.com.fiap.techchallenge.infrastructure.network.data.PagamentoResponseClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MercadoLivreClientTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MercadoLivreClient mercadoLivreClient;


    @Test
    void deveReturnarTruePagaUmPedidoPago() {
        UUID pedidoId = UUID.randomUUID();
        assertTrue(mercadoLivreClient.verificaPagamento(pedidoId));
    }

    @Test
    void deveCriaUmPagamentoNoMeioDePagamentoRetornandoUmQrCodeValido() throws JsonProcessingException {
        UUID pedidoId = UUID.randomUUID();
        Cliente cliente = Cliente.criaCliente("48336661115");

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .descricao("Refrigerante de cola")
                .preco(BigDecimal.TEN)
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.LANCHE)
                .build();

        Pedido expectedPedido = Pedido.criaPedido(pedidoId, cliente, List.of(produto));

        PagamentoClient pagamentoClient = PagamentoClient.fromPedido(expectedPedido, "notificationUrl");
        String expectedQrData = "testQrData";

        PagamentoResponseClient pagamentoResponseClient = PagamentoResponseClient.of(expectedQrData);

        when(objectMapper.writeValueAsString(any())).thenReturn("jsonString");
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), any()))
                .thenReturn(ResponseEntity.ok(pagamentoResponseClient));

        String qrData = mercadoLivreClient.criaPagamento(pagamentoClient);

        assertEquals(expectedQrData, qrData);

        verify(objectMapper, times(1)).writeValueAsString(pagamentoClient);
        verify(restTemplate, times(1)).postForEntity(any(String.class), any(HttpEntity.class), any());
    }
}

