package br.com.fiap.techchallenge.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Item {

    private Long id;
    private String nome;

    private String descricao;

    private List<Ingrediente> ingredientes;

    private BigDecimal valor;

    private TipoDeItem tipoDeItem;
}
