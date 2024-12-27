# ⚙️ **Spring Microservices: Arquitetura Hexagonal**

Bem-vindo ao projeto **Arquitetura Hexagonal em Microsserviços**, uma aplicação que demonstra conceitos modernos de arquitetura e boas práticas no desenvolvimento de microsserviços usando Java e Spring Boot.

---

## 🔧 **Tecnologias Utilizadas**
- **Java 17** ☕
- **Spring Boot 2.7.4** 🚀
- **MongoDB** (Banco de Dados NoSQL) 🌐
- **Apache Kafka** (Mensageria) 📢
- **Spring Cloud OpenFeign** (Comunicação entre microsserviços) ⚖️
- **MapStruct** (Mapeamento de objetos) 🖐️
- **Lombok** (Boilerplate simplificado) 🪟

---

## 🔀 **Objetivo**
Demonstrar a implementação prática da **Arquitetura Hexagonal**, separando responsabilidades de forma clara e permitindo uma aplicação flexível, testável e escalável.

---

## 🔮 **Funcionalidades**
1. **CRUD Completo**:
    - Criação, leitura, atualização e exclusão de dados no MongoDB.
2. **Mensageria**:
    - Integração com Apache Kafka para consumidores e produtores.
3. **Comunicação Entre Microsserviços**:
    - Uso do OpenFeign para integração REST.
4. **Validações Avançadas**:
    - Validação de dados utilizando Spring Validation.
5. **Mapeamento de Objetos**:
    - Conversão simples entre DTOs e entidades com MapStruct.

---

## 🗋 **Estrutura do Projeto**

A aplicação segue os princípios da Arquitetura Hexagonal:

- **Domain**: Contém as regras de negócio principais.
- **Usecase**: Define as operações que utilizam o domain.
- **Adapters**:
    - **Entrada**: Controladores REST.
    - **Saída**: Conexões com MongoDB e Kafka.

## 📊 **Dependências Importantes**

### Implementação:
- `spring-boot-starter-data-mongodb`: Integração com MongoDB.
- `spring-boot-starter-web`: Para APIs REST.
- `spring-kafka`: Integração com Apache Kafka.
- `spring-cloud-starter-openfeign`: Comunicação entre microsserviços.
- `mapstruct`: Mapeamento de objetos DTO/Entity.

## 🛠️ **Contribuições**
Contribuições são bem-vindas! Envie um pull request com sua sugestão ou melhoria.


