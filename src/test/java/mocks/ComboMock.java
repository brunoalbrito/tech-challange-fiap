package mocks;

import br.com.fiap.techchallenge.domain.Bebida;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Lanche;
import br.com.fiap.techchallenge.domain.Sobremesa;

import java.util.Optional;

public class ComboMock {
    public static Combo createCombo(){
        Lanche lanche = ItemMock.createLanche();
        Bebida bebida = ItemMock.createBebida();
        Sobremesa sobremesa = ItemMock.createSobremesa();
        
        return Combo.criaCombo(Optional.of(lanche), Optional.of(bebida), Optional.of(sobremesa));
    }
}
