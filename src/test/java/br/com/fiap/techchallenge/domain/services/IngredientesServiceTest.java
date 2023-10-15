package br.com.fiap.techchallenge.domain.services;

import br.com.fiap.techchallenge.application.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.repository.IngredienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientesServiceTest {

    @InjectMocks
    private IngredienteService ingredienteService;

    @Mock
    private IngredienteRepository ingredienteRepository;

    @Test
    public void deveCriarIngrediente() {
        IngredienteRequest ingredienteRequest = IngredienteRequest.criaIngredienteRequest("Tomate");

        when(ingredienteRepository.save(any(IngredienteEntity.class)))
                .thenAnswer(invocation -> invocation.<IngredienteEntity>getArgument(0));

        Ingrediente ingrediente = ingredienteService.criaIngrediente(ingredienteRequest);

        assertNotNull(ingrediente.getId());
        assertEquals(ingrediente.getDescricao(), ingrediente.getDescricao());

    }

    @Test
    public void naoDeveCriarIngredienteQuandoDescricaoVazia() {
        IngredienteRequest ingredienteRequest = IngredienteRequest.criaIngredienteRequest("");

        assertThrows(IllegalArgumentException.class, () ->
                ingredienteService.criaIngrediente(ingredienteRequest)
        );
    }

    @Test
    public void naoDeveCriarIngredienteQuandoDescricaoNula() {
        IngredienteRequest ingredienteRequest = IngredienteRequest.criaIngredienteRequest(null);

        assertThrows(IllegalArgumentException.class, () ->
                ingredienteService.criaIngrediente(ingredienteRequest)
        );
    }

    @Test
    public void deveListarTodosIngredientes() {
        List<IngredienteEntity> ingredienteEntities = List.of(IngredienteEntity.criaEntity(Ingrediente.criaIngrediente("Tomate")));

        when(ingredienteRepository.findAll())
                .thenReturn(ingredienteEntities);

        ingredienteService.listaTodosIngredientes();

        verify(ingredienteRepository, atLeast(1)).findAll();
        assertEquals(ingredienteEntities.size(), 1);
        assertEquals(ingredienteEntities.get(0).getId(), ingredienteEntities.get(0).getId());
        assertEquals(ingredienteEntities.get(0).getDescricao(), "Tomate");
    }
}
