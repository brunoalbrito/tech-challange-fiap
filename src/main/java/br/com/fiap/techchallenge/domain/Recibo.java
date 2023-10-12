package br.com.fiap.techchallenge.domain;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Recibo {
    private BigDecimal total;

    public static Recibo criaRecibo(Pedido pedido){
        BigDecimal total = pedido.valorTotalPedido();
        return new Recibo(total);
    }
}
