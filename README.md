# 🛰️ Subscription Sentinel

> **Projeto de Estudo - Foco em Backend Java/Spring**

## 📖 O que é?
O **Subscription Sentinel** é um gerenciador de assinaturas e serviços recorrentes. O foco do projeto foi aplicar padrões de mercado para resolver um problema comum: o controle de gastos mensais e prazos de renovação.

## 🛠️ Stack Tecnológica
- **Java 21** & **Spring Boot 3**
- **MySQL** (Persistência de dados)
- **Spring Data JPA** & **Hibernate**
- **Maven** (Gestão de dependências)
- **Lombok** (Produtividade no código)
- **Swagger UI** (Documentação e testes da API)

## ✅ Diferenciais Técnicos (O que o projeto aplica)
- **Arquitetura DTO:** Isolamento total das entidades do banco através de Java Records (`AssinaturaDTO`, `AssinaturaResponseDTO`, `ResumoFinanceiroDTO`).
- **Status Inteligente:** Lógica embutida no DTO que calcula em tempo real se a assinatura está `EM DIA`, `VENCIDA` ou se `VENCE EM BREVE`.
- **Cálculo Financeiro com Stream API:** Uso de processamento funcional (`filter`, `map`, `reduce`) para gerar resumos financeiros precisos com `BigDecimal`.
- **Segurança de Configuração:** Uso de templates `.properties.example` para evitar exposição de credenciais no GitHub.

## 📊 Endpoints Principais
- `GET /assinaturas`: Lista todas as assinaturas cadastradas.
- `GET /assinaturas/resumo`: Retorna o Dashboard (Gasto mensal total, quantidade de assinaturas ativas e o maior gasto).
- `POST /assinaturas`: Cadastra um novo serviço.
- `PUT /assinaturas/{id}`: Atualiza dados de uma assinatura existente.
- `DELETE /assinaturas/{id}`: Remove um registro (Status 204 No Content).

## 🏁 Como Rodar
1. Clone o repositório.
2. Em `src/main/resources`, renomeie o `application.properties.example` para `application.properties`.
3. Configure as credenciais do seu banco MySQL local.
4. Rode a aplicação e acesse: `http://localhost:8080/swagger-ui.html`

---
Desenvolvido por **Junior Maximiano** para consolidar conhecimentos de arquitetura REST e Spring Boot.