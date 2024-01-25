package br.com.fiap.techchallenge.infrastructure.persistence.entity;

import br.com.fiap.techchallenge.domain.Ingrediente;
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
@Table(name = "Ingredientes")
public class IngredienteEntity {

    @Id
    private UUID id;
    private String descricao;

    public static IngredienteEntity criaEntity(Ingrediente ingrediente) {
        return new IngredienteEntity(ingrediente.getId(), ingrediente.getDescricao());
    }

    public Ingrediente toDomain() {
        return Ingrediente.criaIngrediente(this.id, this.descricao);
    }
}
