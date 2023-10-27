package br.com.fiap.techchallenge.application.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "criaComboRequestTest")
public class ComboRequestTest {
    private List<String> produtos;
}
