package br.com.fiap.techchallenge.infrastructure.entity;

import br.com.fiap.techchallenge.domain.Lanche;
import br.com.fiap.techchallenge.infrastructure.entity.enums.TipoItem;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Items")
public class ItemEntity {
    @Id
    private UUID id;
    private String nome;
    private String descricao;
    @OneToMany
    private List<IngredienteEntity> ingredientes;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private TipoItem tipoItem;

    public static ItemEntity criaLanche(Lanche lanche) {
        List<IngredienteEntity> ingredienteEntities = lanche.getIngredientes().stream()
                .map(IngredienteEntity::criaEntity)
                .toList();
        return new ItemEntity(lanche.getId(), lanche.getNome(), lanche.getDescricao(), ingredienteEntities, lanche.getValor(), TipoItem.LANCHE);
    }


}
