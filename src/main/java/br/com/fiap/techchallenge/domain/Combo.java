package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.Tipo;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class Combo {
    private final UUID id;

    private final List<Produto> produtos;

    public Combo(UUID id, List<Produto> produtos) {

        validaId(id);
        validaProdutos(produtos);

        this.id = id;
        this.produtos = produtos;
    }

    private void validaId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
    }

    private static void validaProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Combo deve conter um lanche, uma bebida e uma sobremesa");
        }

        boolean temTodosOsProdutos = produtos.stream().map(Produto::getTipo)
                .collect(Collectors.toSet())
                .equals(Set.of(Tipo.LANCHE, Tipo.BEBIDA, Tipo.ACOMPANHAMENTO));

        if (!temTodosOsProdutos) {
            throw new IllegalArgumentException("Combo deve conter um lanche, uma bebida e uma sobremesa");
        }
    }

    public static Combo criaCombo(UUID id, List<Produto> produtos) {
        return new Combo(id, produtos);
    }

    public BigDecimal valorTotal() {
        return this.produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
