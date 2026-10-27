# 🔐 API REST de Segurança e Autenticação

Uma API REST completa para cadastro, autenticação de usuários e gerenciamento de acesso a rotas protegidas, desenvolvida com **Java**, **Spring Boot**, **Spring Security**, **JWT** e **MongoDB Atlas**.

---

## 📌 Visão Geral do Projeto

O objetivo deste projeto é fornecer um serviço robusto de autenticação e autorização. A aplicação permite cadastrar novos usuários, realizar login com validação de credenciais e emitir tokens **JWT (JSON Web Token)** para autorizar o acesso a rotas restritas.

### 🛠️ Tecnologias e Ferramentas

* **Linguagem & Framework:** Java, Spring Boot, Spring Data MongoDB, Spring Security
* **Segurança:** JWT (JSON Web Token), BCrypt (Hash de Senhas)
* **Banco de Dados:** MongoDB Atlas (Nuvem), MongoDB Compass (Interface Gráfica)
* **Ambiente & Ferramentas:** Visual Studio Code, PowerShell, Postman, Maven

---

## 🏗️ Estrutura e Configuração

### 📝 Configuração (`application.yml`)

As configurações da aplicação utilizam variáveis de ambiente para garantir que credenciais sensíveis não sejam expostas no código-fonte.

```yaml
spring:
  mongodb:
    uri: ${MONGODB_URI}
  data:
    mongodb:
      auto-index-creation: true

app:
  jwt:
    secret: ${JWT_SECRET}
    expiration-ms: 3600000 # 1 hora
```

### 🧩 Principais Componentes de Segurança

* **`SecurityConfig.java`**: Define as regras de autorização das rotas (públicas vs. protegidas).
* **`JwtService.java`**: Responsável pela geração, extração e validação dos tokens JWT.
* **`JwtAuthenticationFilter.java`**: Intercepta requisições HTTP, extrai o token do cabeçalho `Authorization` e valida a sessão do usuário.
* **`MongoUserDetailsService.java`**: Integra a busca de usuários no MongoDB com o ecossistema do Spring Security.
* **`UserService.java`**: Trata a regra de negócio e aplica o hash BCrypt nas senhas antes da persistência.

---

## 🚀 Guia Rápidode Execução

### 1. Clonar o Repositório e Acessar o Diretório
```powershell
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### 2. Configurar Variáveis de Ambiente (PowerShell)

**Gerar e definir a chave secreta para o JWT (Base64 de 32 bytes):**
```powershell
$bytes = New-Object byte[] 32
$rng = [System.Security.Cryptography.RandomNumberGenerator]::Create()
$rng.GetBytes($bytes)
$env:JWT_SECRET = [Convert]::ToBase64String($bytes)
$rng.Dispose(); [Array]::Clear($bytes, 0, $bytes.Length)
```

**Configurar a URI do MongoDB Atlas:**
```powershell
$env:MONGODB_URI = Read-Host "Cole a URI do MongoDB Atlas"
```

### 3. Executar a Aplicação
```powershell
.\mvnw.cmd spring-boot:run
```
> O servidor iniciará na porta padrão `8080` (`http://localhost:8080`).

---

## 📑 Endpoints da API

### 1. Cadastro de Usuário
* **Método:** `POST`
* **Rota:** `/api/auth/register`
* **Acesso:** Público
* **Corpo da Requisição (JSON):**
```json
{
  "name": "Ana Souza",
  "email": "ana@exemplo.com",
  "password": "SenhaForte@123"
}
```
* **Resposta Esperada:** `201 Created`

### 2. Autenticação (Login)
* **Método:** `POST`
* **Rota:** `/api/auth/login`
* **Acesso:** Público
* **Corpo da Requisição (JSON):**
```json
{
  "email": "ana@exemplo.com",
  "password": "SenhaForte@123"
}
```
* **Resposta Esperada:** `200 OK` (retorna o Token JWT Bearer)

### 3. Consulta ao Usuário Autenticado
* **Método:** `GET`
* **Rota:** `/api/me`
* **Acesso:** Protegido (Exige Token JWT)
* **Cabeçalho:** `Authorization: Bearer <seu_token_jwt>`
* **Resposta Esperada:** `200 OK` (retorna dados do usuário e perfil `ROLE_USER`)

---

## 🧪 Testes e Validações

Os testes do fluxo principal foram validados com requisições HTTP e executados via PowerShell e Postman:

| Teste | Status | Evidência |
| :--- | :---: | :--- |
| **Inicialização do Contexto** | ✅ Sucesso | `BUILD SUCCESS` (JUnit 5) |
| **Cadastro de Usuário** | ✅ Sucesso | `HTTP 201 Created` |
| **Login e Geração de JWT** | ✅ Sucesso | `HTTP 200 OK` + Token Bearer |
| **Acesso a Rota Protegida (`/api/me`)** | ✅ Sucesso | `HTTP 200 OK` + Dados do Perfil |
| **Persistência de Dados** | ✅ Validado | Registro confirmado via MongoDB Compass |

---

## 🔒 Boas Práticas de Segurança Aplicadas

1. **Separação de Segredos:** Nenhuma chave (`JWT_SECRET`) ou credencial de banco (`MONGODB_URI`) é gravada no código.
2. **Proteção de Senhas:** Utilização de criptografia adaptativa forte com BCrypt.
3. **Validação de Tokens:** Verificação estrita de assinatura e expiração temporizada (1 hora).
4. **Controle de Acesso:** Proteção centralizada via filtros de segurança do Spring Security.

---

## 🛡️ Licença

Este projeto foi desenvolvido para fins educacionais e de demonstração prática de arquitetura de segurança RESTful.