package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ComboEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.ProdutoEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ComboRepository;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.ProdutoRepository;
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

@Deprecated
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
        Produto lanche = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.LANCHE).build();
        Produto bebida = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.BEBIDA).build();
        Produto acompanhamento = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.ACOMPANHAMENTO).build();

        ProdutoEntity lancheEntity = ProdutoEntity.toEntity(lanche);
        ProdutoEntity bebidaEntity = ProdutoEntity.toEntity(bebida);
        ProdutoEntity acompanhamentoEntity = ProdutoEntity.toEntity(acompanhamento);

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
        Produto lanche = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.LANCHE).build();
        Produto bebida = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.BEBIDA).build();
        Produto acompanhamento = Produto.builder().id(UUID.randomUUID()).nome("nome").preco(BigDecimal.TEN).descricao("descricao").ingredientes(List.of(ingrediente)).tipo(Tipo.ACOMPANHAMENTO).build();


        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, acompanhamento));
        ComboEntity comboEntity = ComboEntity.criaComboEntity(combo);


        List<ComboEntity> comboEntities = new ArrayList<>();
        comboEntities.add(comboEntity);

        when(comboRepository.findAll()).thenReturn(comboEntities);


        List<Combo> comboList = comboService.listaCombos();


        assertEquals(comboEntities.size(), comboList.size());

    }
}