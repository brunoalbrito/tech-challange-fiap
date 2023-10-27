package br.com.fiap.techchallenge.infrastructure.entity;

import java.util.List;
import java.util.UUID;

import br.com.fiap.techchallenge.domain.Pedido;
import br.com.fiap.techchallenge.domain.enums.StatusPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Pedidos")
public class PedidoEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private ClienteEntity cliente;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<ProdutoEntity> produtos;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private PagamentoEmbedable pagamento;

    public static PedidoEntity toEntity(Pedido pedido) {
        return new PedidoEntity(
            pedido.getId(),
            ClienteEntity.criaEntity(pedido.getCliente()),
            ProdutoEntity.toEntity(pedido.getProdutos()),
            pedido.getStatusPedido(),
            PagamentoEmbedable.toEntity(pedido.getPagamento())
        );
    }

    public Pedido toDomain() {
        return Pedido.criaPedido(getId(), getCliente().toDomain(), ProdutoEntity.toDomain(getProdutos()),
            getStatus(), pagamento.toDomain());
    }
}
