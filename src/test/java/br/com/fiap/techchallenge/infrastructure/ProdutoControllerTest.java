package br.com.fiap.techchallenge.infrastructure;


import br.com.fiap.techchallenge.application.usecases.produto.CriaProdutoInteractor;
import br.com.fiap.techchallenge.domain.Ingrediente;
import br.com.fiap.techchallenge.domain.Produto;
import br.com.fiap.techchallenge.domain.enums.Tipo;
import br.com.fiap.techchallenge.infrastructure.controllers.ProdutoController;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ProdutoRequest;
import br.com.fiap.techchallenge.infrastructure.controllers.request.enums.TipoRequest;
import br.com.fiap.techchallenge.infrastructure.request.ProdutoRequestTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriaProdutoInteractor criaProdutoInteractor;

    @Test
    public void deveCriarProdutoValido() throws Exception {
        ProdutoRequestTest produtoRequestTest = ProdutoRequestTest
                .criaComboRequestTest(UUID.randomUUID().toString(), "Combo Teste", BigDecimal.TEN, "Combo Teste", List.of(UUID.randomUUID().toString()), TipoRequest.LANCHE);

        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), "Ingrediente Teste");
        
        Produto produto = Produto.builder()
                .id(UUID.randomUUID())
                .nome("Produto")
                .preco(BigDecimal.TEN)
                .descricao("Descricao")
                .ingredientes(List.of(ingrediente))
                .tipo(Tipo.LANCHE)
                .build();

        when(criaProdutoInteractor.execute(any(ProdutoRequest.class)))
                .thenReturn(produto);


        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoRequestTest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        verify(criaProdutoInteractor).execute(any(ProdutoRequest.class));
    }
}
