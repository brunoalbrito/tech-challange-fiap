package br.com.fiap.techchallenge.application.usecases.produto;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.application.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.request.enums.TipoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CriaProdutoInteractorTest {
    private CriaProdutoInteractor criaProdutoInteractor;
    private ProdutoGateway produtoGateway;
    private IngredienteGateway ingredienteGateway;

    @BeforeEach
    public void setUp() {
        produtoGateway = mock(ProdutoGateway.class);
        ingredienteGateway = mock(IngredienteGateway.class);
        criaProdutoInteractor = new CriaProdutoInteractor(produtoGateway, ingredienteGateway);
    }

    @Test
    public void deveCriarProdutoDadoValoresValidos() {
        ProdutoRequest produtoRequest = ProdutoRequest.builder()
                .nome("Nome")
                .preco(BigDecimal.TEN)
                .descricao("Descrição")
                .ingredientes(Collections.singletonList(UUID.randomUUID()))
                .tipo(TipoRequest.LANCHE)
                .build();

        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente1");
        List<Ingrediente> ingredientes = Collections.singletonList(ingrediente);

        when(ingredienteGateway.buscaIngredientesPorId(anyList()))
                .thenReturn(ingredientes);

        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Nome")
                .preco(BigDecimal.TEN)
                .descricao("Descrição")
                .ingredientes(ingredientes)
                .tipo(TipoRequest.LANCHE.toDomain())
                .build();

        when(produtoGateway.cria(any())).thenReturn(produto);

        Produto result = criaProdutoInteractor.execute(produtoRequest);

        assertNotNull(result);
        assertEquals("Nome", result.getNome());
        assertEquals(BigDecimal.TEN, result.getPreco());
        assertEquals("Descrição", result.getDescricao());
        assertEquals(ingredientes, result.getIngredientes());
        assertEquals(Tipo.LANCHE, result.getTipo());

        verify(ingredienteGateway, times(1)).buscaIngredientesPorId(anyList());
        verify(produtoGateway, times(1)).cria(any());
    }
}