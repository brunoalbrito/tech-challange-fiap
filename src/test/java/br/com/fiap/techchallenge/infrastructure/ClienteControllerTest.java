package br.com.fiap.techchallenge.infrastructure;

import br.com.fiap.techchallenge.application.usecases.cliente.CriaClienteInteractor;
import br.com.fiap.techchallenge.infrastructure.controllers.ClienteController;
import br.com.fiap.techchallenge.infrastructure.controllers.request.ClienteRequest;
import br.com.fiap.techchallenge.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CriaClienteInteractor criaClienteInteractor;

    @Test
    public void deveCriarClienteValido() throws Exception {
        String cpf = "64884281799";
        ClienteRequest clienteRequest = ClienteRequest.criaClienteRequest(cpf);

        Cliente cliente = Cliente.criaCliente(cpf);

        when(criaClienteInteractor.execute(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").exists());
    }
}