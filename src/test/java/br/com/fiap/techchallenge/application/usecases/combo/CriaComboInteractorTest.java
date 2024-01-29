package br.com.fiap.techchallenge.application.usecases.combo;

import br.com.fiap.techchallenge.application.gateways.ComboGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CriaComboInteractorTest {

    @Mock
    private ComboGateway comboGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private CriaComboInteractor criaComboInteractor;

    @BeforeEach
    void setUp() {
        comboGateway = mock(ComboGateway.class);
        produtoGateway = mock(ProdutoGateway.class);

        criaComboInteractor = new CriaComboInteractor(comboGateway, produtoGateway);
    }

    @Test
    void deveCriarCombo() {
        UUID produtoUuid1 = UUID.randomUUID();
        UUID produtoUuid2 = UUID.randomUUID();

        List<String> produtoUuids = Arrays.asList(produtoUuid1.toString(), produtoUuid2.toString());
        ComboRequest comboRequest = ComboRequest.builder()
                .produtos(produtoUuids)
                .build();

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


        List<Produto> produtos = Arrays.asList(lanche, acompanhamento, bebida);

        when(produtoGateway.buscaPorUuids(anyList())).thenReturn(produtos);

        when(comboGateway.salva(any(Combo.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Combo result = criaComboInteractor.execute(comboRequest);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getProdutos());
        assertNotNull(result.getProdutos().get(0).getId());
        assertNotNull(result.getProdutos().get(1).getId());
        assertNotNull(result.getProdutos().get(2).getId());

        verify(produtoGateway, times(1))
                .buscaPorUuids(comboRequest.getProdutos().stream().map(UUID::fromString).toList());

        verify(comboGateway, times(1)).salva(any(Combo.class));
    }
}
