package mocks;

import br.com.fiap.techchallenge.domain.Bebida;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Lanche;
import br.com.fiap.techchallenge.domain.Sobremesa;

public class ComboMock {
    public static Combo createCombo(){
        Lanche lanche = ItemMock.criaLanche();
        Bebida bebida = ItemMock.criaBebida();
        Sobremesa sobremesa = ItemMock.criaSobremesa();
        
        return Combo.criaCombo(lanche, bebida, sobremesa);
    }
}
