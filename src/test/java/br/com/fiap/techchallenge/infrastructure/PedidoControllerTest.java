package br.com.fiap.techchallenge.infrastructure;

import br.com.fiap.techchallenge.infrastructure.controllers.PedidoController;
import br.com.fiap.techchallenge.infrastructure.controllers.request.PedidoRequest;
import br.com.fiap.techchallenge.domain.Cliente;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Pagamento;
import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.domain.services.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PedidoService pedidoService;


    @Test
    public void deveCriarPedidoValido() throws Exception {
        PedidoRequest pedidoRequest = PedidoRequest.builder()
                .clienteId("64884281799")
                .produtosId(List.of(UUID.randomUUID(), UUID.randomUUID()))
                .build();


        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qrCode");
        Cliente cliente = Cliente.criaCliente("64884281799");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "Produto", BigDecimal.TEN, "Descricao", List.of(ingrediente), Tipo.LANCHE);
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto), pagamento);

        when(pedidoService.cria(any(PedidoRequest.class)))
                .thenReturn(pedido);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        verify(pedidoService).cria(any(PedidoRequest.class));
    }

    @Test
    public void deveBuscarPedidoPorId() throws Exception {

        when(pedidoService.buscar(any(UUID.class)))
                .thenReturn(Pedido.criaPedido(UUID.randomUUID(), Cliente.criaCliente("64884281799"), List.of(Produto.criaProduto(UUID.randomUUID(), "Produto", BigDecimal.TEN, "Descricao", List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente")), Tipo.LANCHE)), Pagamento.criaPagamento(UUID.randomUUID(), "qrCode")));

        mockMvc.perform(get("/api/pedidos/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.cpf").value("64884281799"))
                .andExpect(jsonPath("$.produtosId[0]").exists())
                .andExpect(jsonPath("$.pagamentoQrCode").value("qrCode"))
                .andExpect(jsonPath("$.status").value("AGUARDANDO_PAGAMENTO"));
    }

    @Test
    public void deveAtulizarStatusPedidoQuandoPago() throws Exception {

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qrCode");
        Cliente cliente = Cliente.criaCliente("64884281799");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "Produto", BigDecimal.TEN, "Descricao", List.of(ingrediente), Tipo.LANCHE);
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto), pagamento);

        pedido.pagamentoRecebido();

        when(pedidoService.pedidoPago(any(UUID.class)))
                .thenReturn(pedido);

        mockMvc.perform(patch("/api/pedidos/{id}/callback", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(pedidoService).pedidoPago(any(UUID.class));
    }

    @Test
    public void deveAtulizarStatusPedidoQuandoPreparoFinalizado() throws Exception {

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qrCode");
        Cliente cliente = Cliente.criaCliente("64884281799");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "Produto", BigDecimal.TEN, "Descricao", List.of(ingrediente), Tipo.LANCHE);
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto), pagamento);

        pedido.pagamentoRecebido();
        pedido.preparoFinalizado();

        when(pedidoService.preparoFinalizado(any(UUID.class)))
                .thenReturn(pedido);

        mockMvc.perform(patch("/api/pedidos/{id}/preparo-finalizado", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.cpf").value("64884281799"))
                .andExpect(jsonPath("$.produtosId[0]").exists())
                .andExpect(jsonPath("$.pagamentoQrCode").value("qrCode"))
                .andExpect(jsonPath("$.status").value("PREPARO_FINALIZADO"));

        verify(pedidoService).preparoFinalizado(any(UUID.class));
    }

    @Test
    public void deveAtulizarStatusPedidoQuandoEntregue() throws Exception {

        Pagamento pagamento = Pagamento.criaPagamento(UUID.randomUUID(), "qrCode");
        Cliente cliente = Cliente.criaCliente("64884281799");
        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente");
        Produto produto = Produto.criaProduto(UUID.randomUUID(), "Produto", BigDecimal.TEN, "Descricao", List.of(ingrediente), Tipo.LANCHE);
        Pedido pedido = Pedido.criaPedido(UUID.randomUUID(), cliente, List.of(produto), pagamento);

        pedido.pagamentoRecebido();
        pedido.preparoFinalizado();
        pedido.entregue();

        when(pedidoService.entregue(any(UUID.class)))
                .thenReturn(pedido);

        mockMvc.perform(patch("/api/pedidos/{id}/entregue", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.cpf").value("64884281799"))
                .andExpect(jsonPath("$.produtosId[0]").exists())
                .andExpect(jsonPath("$.pagamentoQrCode").value("qrCode"))
                .andExpect(jsonPath("$.status").value("ENTREGUE"));
    }
}
