package br.com.fiap.techchallenge.domain;

import br.com.fiap.techchallenge.domain.enums.TipoDeItem;

import java.math.BigDecimal;
import java.util.List;


public class Lanche extends Item {

    public Lanche(Long id, String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        super(id, nome, descricao, ingredientes, valor, TipoDeItem.LANCHE);
    }

    public static Lanche criaLanche(Long id, String nome, String descricao, List<Ingrediente> ingredientes, BigDecimal valor) {
        return new Lanche(id, nome, descricao, ingredientes, valor);
    }
}
