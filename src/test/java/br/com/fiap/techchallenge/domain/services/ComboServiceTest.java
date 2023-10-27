package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.entity.ComboEntity;
import br.com.fiap.techchallenge.infrastructure.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComboServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ComboRepository comboRepository;

    @InjectMocks
    private ComboService comboService;

    @Test
    void deveCriarCombo() {

        ComboRequest comboRequest = ComboRequest.builder()
                .produtos(List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()))
                .build();

        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "descricao");
        Produto lanche = Produto.criaProduto(UUID.randomUUID(), "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(UUID.randomUUID(), "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.BEBIDA);
        Produto acompanhamento = Produto.criaProduto(UUID.randomUUID(), "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.ACOMPANHAMENTO);
        ProdutoEntity lancheEntity = ProdutoEntity.criaEntity(lanche);
        ProdutoEntity bebidaEntity = ProdutoEntity.criaEntity(bebida);
        ProdutoEntity acompanhamentoEntity = ProdutoEntity.criaEntity(acompanhamento);

        when(produtoRepository.findById(any()))
                .thenReturn(Optional.of(lancheEntity))
                .thenReturn(Optional.of(bebidaEntity))
                .thenReturn(Optional.of(acompanhamentoEntity));

        when(comboRepository.save(Mockito.any(ComboEntity.class))).thenAnswer(invocation -> invocation.<ComboEntity>getArgument(0));


        Combo createdCombo = comboService.criaCombo(comboRequest);

        assertEquals(3, createdCombo.getProdutos().size());
        assertEquals(lanche, createdCombo.getProdutos().get(0));
        assertEquals(bebida, createdCombo.getProdutos().get(1));
        assertEquals(acompanhamento, createdCombo.getProdutos().get(2));
    }

    @Test
    void deveListarCombos() {

        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "descricao");
        Produto lanche = Produto.criaProduto(UUID.randomUUID(), "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(UUID.randomUUID(), "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.BEBIDA);
        Produto acompanhamento = Produto.criaProduto(UUID.randomUUID(), "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.ACOMPANHAMENTO);

        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, acompanhamento));
        ComboEntity comboEntity = ComboEntity.criaComboEntity(combo);


        List<ComboEntity> comboEntities = new ArrayList<>();
        comboEntities.add(comboEntity);

        when(comboRepository.findAll()).thenReturn(comboEntities);


        List<Combo> comboList = comboService.listaCombos();


        assertEquals(comboEntities.size(), comboList.size());

    }
}