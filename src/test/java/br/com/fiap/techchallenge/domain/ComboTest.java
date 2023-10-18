package br.com.fiap.techchallenge.domain;

import mocks.ItemMock;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ComboTest {

    @Test
    public void deveCriarUmCombo() {
        Combo combo = Combo.criaCombo(ItemMock.criaLanche(), ItemMock.criaBebida(), ItemMock.criaSobremesa());

        assertThat(combo.getId()).isNotNull();
        assertThat(combo.getLanche()).isNotNull();
        assertThat(combo.getBebida()).isNotNull();
        assertThat(combo.getSobremesa()).isNotNull();

    }

    @Test
    public void deveCriarComboApenasComLanche() {
        Combo combo = Combo.criaCombo(ItemMock.criaLanche(), null, null);

        assertThat(combo.getId()).isNotNull();
        assertThat(combo.getLanche()).isNotNull();
        assertThat(combo.getBebida()).isEqualTo(Optional.empty());
        assertThat(combo.getSobremesa()).isEqualTo(Optional.empty());
    }


    @Test
    public void deveCriarComboApenasComBebida() {
        Combo combo = Combo.criaCombo(null, ItemMock.criaBebida(), null);

        assertThat(combo.getId()).isNotNull();
        assertThat(combo.getLanche()).isEqualTo(Optional.empty());
        assertThat(combo.getBebida()).isNotNull();
        assertThat(combo.getSobremesa()).isEqualTo(Optional.empty());
    }

    @Test
    public void deveCriaComboApenasComSobremesa() {
        Combo combo = Combo.criaCombo(null, null, ItemMock.criaSobremesa());

        assertThat(combo.getId()).isNotNull();
        assertThat(combo.getLanche()).isEqualTo(Optional.empty());
        assertThat(combo.getBebida()).isEqualTo(Optional.empty());
        assertThat(combo.getSobremesa()).isNotNull();
    }

    @Test
    public void deveCalcularValorTotalDoCombo() {
        Combo combo = Combo.criaCombo(ItemMock.criaLanche(), ItemMock.criaBebida(), ItemMock.criaSobremesa());

        assertThat(combo.valorTotal()).isEqualTo(ItemMock.criaLanche().getValor()
                .add(ItemMock.criaBebida().getValor())
                .add(ItemMock.criaSobremesa().getValor()));
    }

    @Test
    public void deveCalcularValorTotalDoComboApenasComLanche() {
        Combo combo = Combo.criaCombo(ItemMock.criaLanche(), null, null);

        assertThat(combo.valorTotal()).isEqualTo(ItemMock.criaLanche().getValor());
    }

    @Test
    public void deveCalcularValorTotalDoComboApenasComBebida() {
        Combo combo = Combo.criaCombo(null, ItemMock.criaBebida(), null);

        assertThat(combo.valorTotal()).isEqualTo(ItemMock.criaBebida().getValor());
    }

    @Test
    public void deveCalcularValorTotalDoComboApenasComSobremesa() {
        Combo combo = Combo.criaCombo(null, null, ItemMock.criaSobremesa());

        assertThat(combo.valorTotal()).isEqualTo(ItemMock.criaSobremesa().getValor());
    }
}
