//package br.com.fiap.techchallenge.infrastructure;
//
//import br.com.fiap.techchallenge.infrastructure.controllers.IngredienteController;
//import br.com.fiap.techchallenge.infrastructure.controllers.request.IngredienteRequest;
//import br.com.fiap.techchallenge.infrastructure.request.IngredienteRequestTest;
//import br.com.fiap.techchallenge.domain.Ingrediente;
//import br.com.fiap.techchallenge.domain.services.IngredienteService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = IngredienteController.class)
//public class IngredienteControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private IngredienteService ingredienteService;
//
//    @Test
//    public void deveCriarIngredienteValido() throws Exception {
//        String descricao = "Tomate";
//        IngredienteRequestTest ingredienteRequest = IngredienteRequestTest.criaIngredienteRequest(descricao);
//
//        Ingrediente ingrediente = Ingrediente.criaIngrediente(UUID.randomUUID(), descricao);
//        when(ingredienteService.criaIngrediente(any(IngredienteRequest.class)))
//                .thenReturn(ingrediente);
//
//        mockMvc.perform(post("/api/ingredientes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(ingredienteRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").exists());
//    }
//
//    @Test
//    public void deveRetornarBadRequestQuandoDescricaoForNula() throws Exception {
//        IngredienteRequestTest ingredienteRequest = IngredienteRequestTest.criaIngredienteRequest(null);
//
//        when(ingredienteService.criaIngrediente(any(IngredienteRequest.class)))
//                .thenThrow(new IllegalArgumentException("Descricao deve estar preenchida"));
//
//        mockMvc.perform(post("/api/ingredientes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(ingredienteRequest)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Descricao deve estar preenchida"));
//    }
//
//    @Test
//    public void deveRetornarBadRequestQuandoDescricaoForVazia() throws Exception {
//        IngredienteRequestTest ingredienteRequest = IngredienteRequestTest.criaIngredienteRequest("");
//
//        when(ingredienteService.criaIngrediente(any(IngredienteRequest.class)))
//                .thenThrow(new IllegalArgumentException("Descricao deve estar preenchida"));
//
//        mockMvc.perform(post("/api/ingredientes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(ingredienteRequest)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Descricao deve estar preenchida"));
//    }
//}