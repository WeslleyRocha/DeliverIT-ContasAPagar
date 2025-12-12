# 💰 Sistema de Contas a Pagar - Deliver IT

> **Status:** Concluído (em 32h) 🚀

Olá! Seja bem-vindo ao projeto que desenvolvi para o desafio técnico da Deliver IT.

Criei uma solução **Fullstack** completa para gestão de contas a pagar. O foco foi entregar não apenas o código funcionando, mas uma **experiência de desenvolvimento e uso**: cálculo automático de juros/multas no Backend, interface moderna no Frontend e infraestrutura totalmente isolada com Docker.

---

## 📚 Documentação e Planejamento

Acredito que um bom código começa com um bom plano. Abaixo você encontra os documentos que guiaram esse desenvolvimento:

📄 **[PDF com a Solicitação do Desafio](https://drive.google.com/file/d/1syCYidmSv1270EkXbT0gyDtz92YQpJw8/view?usp=share_link)**
<small>*(O documento original com os requisitos técnicos)*</small>

📝 **[Planejamento de Desenvolvimento (7 Passos)](https://docs.google.com/document/d/15KRoDcZcL1oSAe0m2D8DZ95rnjlBH0Ysm4RUd4vOLlA/edit?usp=share_link)**
<small>*(O roteiro que criei antes de codar para organizar a arquitetura)*</small>

---

## 📱 Demonstração da Aplicação

Confira abaixo o sistema funcionando na prática, com o fluxo de cadastro e listagem de contas:

https://github.com/user-attachments/assets/e7cd3957-4a0b-4315-8cf7-d2ca11b98723

*(O vídeo mostra o cadastro de uma conta, o cálculo automático de juros e a persistência no banco de dados)*

---

## 🧪 Payload para Teste Rápido (POST)

- Para testar API via Postman sem abrir o Frontend!
Aqui está um JSON prontinho com todos os campos necessários.

**GET** `http://localhost:8080/contas`

**POST** `http://localhost:8080/contas`

```json
{
  "nome": "Internet Fibra - Teste API",
  "valorOriginal": 150.00,
  "dataVencimento": "2025-12-10",
  "dataPagamento": "2025-12-15"
}
```

## 🛠️ Tecnologias que escolhi

### **Backend (Java)**
* **Java 21**
* **Spring Boot 3**
* **Flyway** (Migrations)
* **MySQL 8**

### **Frontend (Angular)**
* **Angular 17+**
* **Bootstrap 5**
* **Nginx**

### **Infraestrutura**
* **Docker & Docker Compose** 

## 📋 Regras de Negócio

A lógica de juros e multas foi feita no Backend. Isso garante a integridade dos dados mesmo se a requisição vier de fora do Frontend.

| Dias de Atraso | Multa | Juros (ao dia) |
| :--- | :---: | :---: |
| Até 3 dias | 2% | 0.1% |
| Superior a 3 dias | 3% | 0.2% |
| Superior a 5 dias | 5% | 0.3% |
| Em dia | 0% | 0% |

## 🐳 Como rodar o projeto?

Você não precisa instalar Java, Node ou MySQL na sua máquina, apenas o Docker.

### 1. Gerar o executável (.jar)
Primeiro, vamos compilar o Backend. Na raiz do projeto, execute:

```bash
# Linux/Mac
./mvnw clean package -DskipTests

# Windows
mvnw clean package -DskipTests
```
*(Dica: Se preferir, pode rodar o `package` direto pelo painel Maven da sua IDE).*

### 2. Subir a Infraestrutura
Com o jar pronto, esse comando levanta o Banco, o Backend e o Frontend, conectando tudo automaticamente:

```bash
docker-compose up --build
```

### 3. Acessar
Quando os logs estabilizarem, o sistema estará disponível em:

- 💻 **Aplicação Web:** [http://localhost:4200](http://localhost:4200)
- 🔌 **API (Backend):** [http://localhost:8080/contas](http://localhost:8080/contas)

---

## 💡 Diferenciais da Implementação

Durante o desenvolvimento, apliquei algumas práticas de desenvolvimento para garantir qualidade:

1. **Frontend Otimizado (Nginx):** Não rodei o servidor de desenvolvimento do Angular no Docker. Fiz o build de produção e servi com Nginx, resultando em um container leve e rápido.
2. **UX/UI:** Feedback visual imediato (cores e badges) para contas em atraso ou em dia, além de layout responsivo.


## 👨‍💻 Sobre o Desenvolvedor

Projeto desenvolvido com dedicação por **Weslley Rocha**.

Qualquer dúvida sobre a implementação, arquitetura ou se quiser apenas trocar uma ideia sobre o projeto, estou à disposição!

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/weslleyrocha/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/WeslleyRocha)
---

<p align="center">
  © 2025 Weslley Rocha. Todos os direitos reservados.
</p>
