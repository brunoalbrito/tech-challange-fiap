package br.com.fiap.techchallenge.infrastructure.entity;

import br.com.fiap.techchallenge.domain.Cliente;
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
@Table(name = "Clientes")
public class ClienteEntity {

    @Id
    private UUID id;

    private String cpf;

    public static ClienteEntity criaEntity(Cliente cliente) {
        return new ClienteEntity(cliente.getId(), cliente.getCpf());
    }
}
