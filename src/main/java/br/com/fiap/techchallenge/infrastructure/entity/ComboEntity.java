package br.com.fiap.techchallenge.infrastructure.entity;

import br.com.fiap.techchallenge.domain.Combo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Combos")
public class ComboEntity {

    @Id
    private UUID id;

    @ManyToMany
    private List<ProdutoEntity> produtos;

    public static ComboEntity criaComboEntity(Combo combo) {

        List<ProdutoEntity> produtos = combo.getProdutos().stream()
                .map(ProdutoEntity::toEntity)
                .toList();

        return new ComboEntity(combo.getId(), produtos);
    }

    public Combo toDomain() {
        return Combo.criaCombo(id, produtos.stream().map(ProdutoEntity::toDomain).toList());
    }
}
