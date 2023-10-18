package br.com.fiap.techchallenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Combo {
    private UUID id;
    private Optional<Lanche> lanche;

    private Optional<Bebida> bebida;

    private Optional<Sobremesa> sobremesa;

    public static Combo criaCombo(Lanche lanche, Bebida bebida, Sobremesa sobremesa) {
        UUID id = UUID.randomUUID();
        return new Combo(id, Optional.ofNullable(lanche), Optional.ofNullable(bebida), Optional.ofNullable(sobremesa));
    }

    public BigDecimal valorTotal() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        if (lanche.isPresent()) {
            valorTotal = valorTotal.add(lanche.get().getValor());
        }
        if (bebida.isPresent()) {
            valorTotal = valorTotal.add(bebida.get().getValor());
        }
        if (sobremesa.isPresent()) {
            valorTotal = valorTotal.add(sobremesa.get().getValor());
        }
        return valorTotal;
    }
}
