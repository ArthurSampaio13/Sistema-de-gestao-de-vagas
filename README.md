# Sistema de Gestão de Vagas de Emprego

Este projeto é uma aplicação para gestão de vagas de emprego, desenvolvida em **Java** utilizando o framework **Spring Boot**. O sistema é seguro, escalável e inclui monitoramento e análises com ferramentas como **Prometheus**, **Grafana** e **SonarQube**. A aplicação utiliza autenticação com **JWT**, gerenciamento de permissões com **Roles**, e está integrada com um banco de dados executado no **Docker**.

---

## 🎯 **Funcionalidades Principais**

- Cadastro e autenticação de usuários com **JWT**.
- Sistema de permissões baseado em **Roles** (ex.: Admin, Candidato, Recrutador).
- Gerenciamento completo de vagas:
  - Criação, edição, exclusão e listagem de vagas.
  - Aplicação a vagas por candidatos.
  - Revisão e aprovação por recrutadores.
- Banco de dados relacional hospedado no **Docker**.
- Monitoramento com **Prometheus** e visualização com **Grafana**.
- Análise de qualidade de código com **SonarQube**.
- Testes unitários e de integração para garantir a confiabilidade do sistema.

---

## 🛠 **Tecnologias Utilizadas**

- **Backend**: Java 17, Spring Boot
- **Autenticação**: JWT
- **Monitoramento**: Prometheus, Grafana
- **Análise de Código**: SonarQube
- **Banco de Dados**: PostgreSQL (Docker)
- **Containerização**: Docker
- **Testes**: JUnit, Mockito
- **Documentação da API**: Swagger (Springdoc OpenAPI)

---

## 🚀 **Como Executar o Projeto**

### **Pré-requisitos**
Certifique-se de ter instalado:
- **Docker** e **Docker Compose**
- **Java 17** ou superior
- **Maven**

### **Passo a Passo**

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/gestao-vagas.git
   cd gestao-vagas
   ```

2. **Inicie os containers do banco de dados e monitoramento**:
   ```bash
   docker-compose up -d
   ```

3. **Compile e inicie a aplicação**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acesse a aplicação**:
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - Grafana: `http://localhost:3000`
   - SonarQube: `http://localhost:9000`

---

## 🧪 **Testes**

O projeto inclui uma suíte de testes para validar as principais funcionalidades. Para executar os testes:

```bash
mvn test
```

---

## 📊 **Monitoramento**

- O **Prometheus** coleta métricas da aplicação e o **Grafana** exibe dashboards customizados.
- Métricas disponíveis incluem:
  - Tempo de resposta da API.
  - Uso de recursos (CPU, memória).
  - Taxa de sucesso/erro por endpoint.

---

## 📑 **Documentação da API**

A documentação detalhada dos endpoints está disponível via **Swagger** em `http://localhost:8080/swagger-ui.html`.

---
