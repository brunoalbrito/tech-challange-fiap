package br.com.fiap.techchallenge.infrastructure;

import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.domain.services.ComboService;
import br.com.fiap.techchallenge.infrastructure.controllers.ComboController;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.infrastructure.request.ComboRequestTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ComboController.class)
public class ComboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ComboService comboService;

    @Test
    public void deveCriarComboValido() throws Exception {
        UUID id = UUID.randomUUID();
        ComboRequestTest comboRequestTest = ComboRequestTest.criaComboRequestTest(List.of(id.toString()));

        Ingrediente ingrediente = Ingrediente.criaIngrediente(id, "descricao");
        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.BEBIDA)
                .build();

        Produto acompanhamento = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        Combo combo = Combo.criaCombo(id, List.of(lanche, bebida, acompanhamento));

        when(comboService.criaCombo(any(ComboRequest.class)))
                .thenReturn(combo);

        mockMvc.perform(post("/api/combos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(comboRequestTest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.precoTotal").exists())
                .andExpect(jsonPath("$.produtos").isArray());
    }

    @Test
    public void deveRetornarBadRequestQuandoListaDeProdutosForNula() throws Exception {
        ComboRequestTest comboRequestTest = ComboRequestTest.criaComboRequestTest(null);

        when(comboService.criaCombo(any(ComboRequest.class)))
                .thenThrow(new IllegalArgumentException("Lista de produtos deve estar preenchida"));

        mockMvc.perform(post("/api/combos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(comboRequestTest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarBadRequestQuandoListaDeProdutosForVazia() throws Exception {
        ComboRequestTest comboRequestTest = ComboRequestTest.criaComboRequestTest(List.of());

        when(comboService.criaCombo(any(ComboRequest.class)))
                .thenThrow(new IllegalArgumentException("Lista de produtos deve estar preenchida"));

        mockMvc.perform(post("/api/combos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(comboRequestTest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarBadRequestQuandoListaDeProdutosForInvalida() throws Exception {
        ComboRequestTest comboRequestTest = ComboRequestTest.criaComboRequestTest(List.of("invalido"));

        when(comboService.criaCombo(any(ComboRequest.class)))
                .thenThrow(new IllegalArgumentException("Lista de produtos deve estar preenchida"));

        mockMvc.perform(post("/api/combos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(comboRequestTest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarUmaListaDeCombos() throws Exception {
        UUID id = UUID.randomUUID();

        Ingrediente ingrediente = Ingrediente.criaIngrediente(id, "descricao");

        Produto lanche = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();

        Produto bebida = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.BEBIDA)
                .build();

        Produto acompanhamento = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.ACOMPANHAMENTO)
                .build();

        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, acompanhamento));
        when(comboService.listaCombos()).thenReturn(List.of(combo));
        mockMvc.perform(get("/api/combos")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
