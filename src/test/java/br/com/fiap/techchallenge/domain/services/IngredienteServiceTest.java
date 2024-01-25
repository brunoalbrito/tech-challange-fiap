package br.com.fiap.techchallenge.domain.services;


import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.infrastructure.persistence.entity.IngredienteEntity;
import br.com.fiap.techchallenge.infrastructure.persistence.repository.IngredienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class IngredienteServiceTest {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredienteService ingredienteService;

    @Test
    public void deveCriarIngrediente() {
        IngredienteRequest ingredienteRequest = IngredienteRequest.builder()
                .descricao("Alface")
                .build();

        when(ingredienteRepository.save(any(IngredienteEntity.class)))
                .thenAnswer(invocation -> invocation.<Ingrediente>getArgument(0));

        Ingrediente ingrediente = ingredienteService.criaIngrediente(ingredienteRequest);

        assertNotNull(ingrediente);
        assertNotNull(ingrediente.getId());
        assertEquals(ingrediente.getDescricao(), ingredienteRequest.getDescricao());
    }

    @Test
    public void deveBuscarIngredientePorId() {

        UUID id = UUID.randomUUID();

        IngredienteEntity ingredienteEntity = IngredienteEntity.criaEntity(Ingrediente.criaIngrediente(id, "Alface"));

        when(ingredienteRepository.findById(id))
                .thenReturn(Optional.of(ingredienteEntity));

        List<Ingrediente> ingredientes = ingredienteService.buscaIngredientesPorId(List.of(id));

        assertNotNull(ingredientes);
        assertEquals(ingredientes.size(), 1);
        assertEquals(ingredientes.get(0).getId(), id);
        assertEquals(ingredientes.get(0).getDescricao(), "Alface");
    }

    @Test
    public void deveRetornarErroQuandoIngredienteNaoEncontrado() {

        UUID id = UUID.randomUUID();

        when(ingredienteRepository.findById(id))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ingredienteService.buscaIngredientesPorId(List.of(id));
        });

        assertEquals(exception.getMessage(), "Ingrediente não encontrado.");
    }
}
