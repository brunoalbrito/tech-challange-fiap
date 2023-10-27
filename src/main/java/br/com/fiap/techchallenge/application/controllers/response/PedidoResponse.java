package br.com.fiap.techchallenge.application.controllers.response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoResponse {
    private UUID id;
    private String cpf;
    private List<UUID> produtosId;
    private StatusPedido status;
    private String pagamentoQrCode;


    public static PedidoResponse ofDomain(Pedido pedido) {
        return PedidoResponse.builder()
            .id(pedido.getId())
            .cpf(pedido.getCliente().getCpf())
            .produtosId(pedido.getProdutos().stream().map(Produto::getId).collect(Collectors.toList()))
            .status(pedido.getStatusPedido())
            .pagamentoQrCode(pedido.getPagamento().getQrCode())
            .build();
    }
}
