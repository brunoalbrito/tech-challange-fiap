package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListaComboInteractorTest {

    private ListaComboInteractor listaComboInteractor;

    private ComboGateway comboGateway;

    @BeforeEach
    public void setUp() {
        comboGateway = mock(ComboGateway.class);
        listaComboInteractor = new ListaComboInteractor(comboGateway);
    }


    @Test
    public void deveListarCombo() {

        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .descricao("Refrigerante de cola")
                .preco(BigDecimal.TEN)
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.LANCHE)
                .build();

        Produto acompanhamento = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Batata Frita")
                .descricao("Batata frita com cheddar e bacon")
                .preco(BigDecimal.TEN)
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Batata")))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Coca-Cola")
                .descricao("Refrigerante de cola")
                .preco(BigDecimal.TEN)
                .ingredientes(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Cola")))
                .tipo(Tipo.BEBIDA)
                .build();


        List<Produto> produtos = List.of(lanche, acompanhamento, bebida);

        when(comboGateway.listaCombos())
                .thenReturn(List.of(Combo.criaCombo(UUID.randomUUID(), produtos)));

        List<Combo> combos = listaComboInteractor.execute();

        assertNotNull(combos);
        assertEquals(1, combos.size());
        assertEquals(produtos, combos.get(0).getProdutos());

        verify(comboGateway, times(1)).listaCombos();
    }
}
