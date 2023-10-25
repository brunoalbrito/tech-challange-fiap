package br.com.fiap.techchallenge.infrastructure.entity;

import br.com.fiap.techchallenge.domain.Pedido;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Pedidos")
public class PedidoEntity {
    @Id
    private UUID id;

    public static PedidoEntity criaPedidoEntity(Pedido pedido) {
        return new PedidoEntity(pedido.getId());
    }
}
