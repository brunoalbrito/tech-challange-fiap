package mocks;

import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Item;
import br.com.fiap.techchallenge.domain.ItemPedido;
import br.com.fiap.techchallenge.domain.Pedido;

import java.util.Arrays;
import java.util.List;

public class PedidoMock {
    public static Pedido criaPedido(){
        Combo combo = ComboMock.createCombo();
        return  Pedido.criaPedido(combo);
    }
}
