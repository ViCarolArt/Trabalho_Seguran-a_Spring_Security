package com.exemplo.seguranca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Valores fixos só para o teste: ele não depende de variáveis de ambiente
// nem do Atlas, e não cria índices (não precisa de um MongoDB no ar).
@SpringBootTest(properties = {
        "spring.mongodb.uri=mongodb://localhost:27017/seguranca_teste",
        "spring.data.mongodb.auto-index-creation=false",
        "app.jwt.secret=segredo-somente-para-testes-com-mais-de-32-caracteres",
        "app.jwt.expiration-ms=3600000"
})

@SuppressWarnings("all") // <---// <--- Diz ao VS Code para ignorar avisos amarelos (warnings) nesta classe.
class SegurancaApplicationTests {

    @Test
    void contextLoads() {
    }

}