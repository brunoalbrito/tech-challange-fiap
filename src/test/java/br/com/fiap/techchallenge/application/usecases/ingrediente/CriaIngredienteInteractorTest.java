package br.com.fiap.techchallenge.application.usecases.ingrediente;

import br.com.fiap.techchallenge.application.gateways.IngredienteGateway;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CriaIngredienteInteractorTest {

    private CriaIngredienteInteractor criaIngredienteInteractor;

    @Mock
    private IngredienteGateway ingredienteGateway;

    @BeforeEach
    void setUp() {
        ingredienteGateway = mock(IngredienteGateway.class);
        criaIngredienteInteractor = new CriaIngredienteInteractor(ingredienteGateway);
    }

    @Test
    void deveCriarIngrediente() {
        IngredienteRequest ingredienteRequest = IngredienteRequest.criaIngredienteRequest("Cola");

        when(ingredienteGateway.salva(any(Ingrediente.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Ingrediente result = criaIngredienteInteractor.execute(ingredienteRequest);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Cola", result.getDescricao());

        verify(ingredienteGateway, times(1)).salva(any(Ingrediente.class));
    }
}