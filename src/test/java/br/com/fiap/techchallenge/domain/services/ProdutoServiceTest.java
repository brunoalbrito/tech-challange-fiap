package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.request.enums.TipoRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Deprecated
@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private IngredienteService ingredienteService;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void deveCriarProduto() {

        ProdutoRequest produtoRequest = ProdutoRequest.builder()
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Pão, hambúrguer, queijo, bacon e alface")
                .tipo(TipoRequest.LANCHE)
                .build();

        when(ingredienteService.buscaIngredientesPorId(any()))
                .thenReturn(List.of(Ingrediente.criaIngrediente(UUID.randomUUID(), "Alface")));

        when(produtoRepository.save(any(ProdutoEntity.class)))
                .thenAnswer(invocation -> invocation.<Produto>getArgument(0));

        Produto produto = produtoService.criaProduto(produtoRequest);

        assertNotNull(produto);
        assertNotNull(produto.getId());
        assertEquals(produto.getNome(), produtoRequest.getNome());
        assertEquals(produto.getPreco(), produtoRequest.getPreco());
        assertEquals(produto.getDescricao(), produtoRequest.getDescricao());
        assertEquals(produto.getTipo().getValue(), TipoRequest.LANCHE.getValue());
        assertNotNull(produto.getIngredientes());
    }

    @Test
    public void naoDeveCriarProdutoSemIngrediente() {

        ProdutoRequest produtoRequest = ProdutoRequest.builder()
                .nome("X-Bacon")
                .preco(BigDecimal.TEN)
                .descricao("Pão, hambúrguer, queijo, bacon e alface")
                .tipo(TipoRequest.LANCHE)
                .build();

        when(ingredienteService.buscaIngredientesPorId(any()))
                .thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> {
            produtoService.criaProduto(produtoRequest);
        });
    }
}
