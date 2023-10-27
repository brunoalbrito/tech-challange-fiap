package br.com.fiap.techchallenge.infrastructure.entity;

import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.entity.enums.TipoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Produtos")
public class ProdutoEntity {
    @Id
    private UUID id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToMany
    private List<IngredienteEntity> ingredientes;

    @Enumerated(EnumType.STRING)
    private TipoEntity tipo;

    public static ProdutoEntity criaEntity(Produto produto) {
        List<IngredienteEntity> ingredientesEntity = produto.getIngredientes().stream().map(IngredienteEntity::criaEntity).toList();
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), ingredientesEntity, TipoEntity.valueOf(produto.getTipo().name()));
    }

    public Produto toDomain() {
        return Produto.criaProduto(id, nome, preco, descricao, ingredientes.stream().map(IngredienteEntity::toDomain).toList(), Tipo.fromValue(this.tipo.getValue()));
    }
}
