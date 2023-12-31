package br.com.fiap.techchallenge.application;

import br.com.fiap.techchallenge.application.controllers.ComboController;
import br.com.fiap.techchallenge.application.controllers.request.ComboRequest;
import br.com.fiap.techchallenge.application.request.ComboRequestTest;
import br.com.fiap.techchallenge.domain.Combo;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.domain.services.ComboService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Produto lanche = Produto.criaProduto(id, "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(id, "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.BEBIDA);
        Produto acompanhamento = Produto.criaProduto(id, "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.ACOMPANHAMENTO);

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
        Produto lanche = Produto.criaProduto(id, "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.LANCHE);
        Produto bebida = Produto.criaProduto(id, "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.BEBIDA);
        Produto acompanhamento = Produto.criaProduto(id, "nome", BigDecimal.TEN, "descricao", List.of(ingrediente), Tipo.ACOMPANHAMENTO);


        Combo combo = Combo.criaCombo(UUID.randomUUID(), List.of(lanche, bebida, acompanhamento));
        when(comboService.listaCombos()).thenReturn(List.of(combo));
        mockMvc.perform(get("/api/combos")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
