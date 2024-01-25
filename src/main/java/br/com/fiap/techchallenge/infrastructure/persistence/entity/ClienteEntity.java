package br.com.fiap.techchallenge.infrastructure.persistence.entity;

import br.com.fiap.techchallenge.domain.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "Clientes")
public class ClienteEntity {
    @Id
    private String cpf;

    public static ClienteEntity criaEntity(Cliente cliente) {
        return new ClienteEntity(cliente.getCpf());
    }

    public Cliente toDomain() {
        return Cliente.criaCliente(this.cpf);
    }
}
