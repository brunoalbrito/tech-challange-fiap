package br.com.fiap.techchallenge.infrastructure.network.data;

import br.com.fiap.techchallenge.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class PagamentoClient {

    @JsonProperty("external_reference")
    private String externalReference;

    private String description;

    private List<ItemClient> items;

    @JsonProperty("notification_url")
    private String notificationUrl;

    private String title;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    public static PagamentoClient fromPedido(Pedido pedido, String notificationUrl) {
        return new PagamentoClient(
                pedido.getId().toString(),
                String.format("Tech Challenge - Pedido %s para Cliente com CPF: %s", pedido.getId(), pedido.getCliente().getCpf()),
                pedido.getProdutos().stream().map(ItemClient::fromProduto).toList(),
                notificationUrl,
                "Tech Challenge",
                pedido.valorTotalPedido());
    }
}
