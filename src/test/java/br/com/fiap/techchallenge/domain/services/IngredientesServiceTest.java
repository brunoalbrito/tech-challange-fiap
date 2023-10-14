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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
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
}
