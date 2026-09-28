# 🎯 Valorant Scrim Tracker

> Aplicação web para gerenciamento, análise e acompanhamento de treinos (scrims) de Valorant. Desenvolvida para a disciplina de **Laboratório de Programação** (Curso de Ciência de Dados e Inteligência Artificial).

---

## 📌 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Requisitos Atendidos](#-requisitos-atendidos)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura do Sistema](#-arquitetura-do-sistema)
- [Como Executar o Projeto](#-como-executar-o-projeto)
- [Autores](#-autores)

---

## 📖 Sobre o Projeto
O **Valorant Scrim Tracker** permite que times táticos de Valorant registrem partidas de treino, acompanhem o desempenho individual de cada jogador (K/D/A, agente utilizado) e analisem o histórico round a round (economia, lado do mapa e resultado).

---

## ✅ Requisitos Atendidos

### Requisitos Funcionais
1. **Cadastro de Registros:** Inclusão de Partidas, Jogadores, Estatísticas Individuais e Rounds com campos obrigatórios e opcionais.
2. **Listagem:** Tabelas responsivas formatadas para exibição clara de dados.
3. **Edição/Atualização:** Permite alterar dados de partidas existentes via formulário preenchido automaticamente.
4. **Exclusão:** Remoção individual com caixa de confirmação nativa.
5. **Pesquisa/Busca:** Filtro em tempo real por campo-chave (busca por **Oponente** ou por **Mapa**).
6. **Ordenação Dinâmica:** Permite alterar a ordenação da listagem em ordem crescente/decrescente clicando nos cabeçalhos (Data, Mapa, Oponente).

### Requisitos Técnicos
- **Back-end:** Java com Spring Boot.
- **Front-end:** Thymeleaf + Bootstrap 5 (formulários, badges, tabelas, botões e alertas responsivos).
- **Persistência:** JPA / Hibernate + MySQL.
- **Tratamento de Erros:** Alertas de sucesso e erro via `RedirectAttributes` + Bootstrap Alerts (validação contra duplicidade de rounds e jogadores na mesma partida).
- **Arquitetura:** Organização estrita em 5 camadas (`Model`, `Repository`, `Service`, `Controller`, `View`).

---

## 🛠 Tecnologias Utilizadas
- **Linguagem:** Java 17+
- **Framework:** Spring Boot (Spring Web, Spring Data JPA)
- **Engine de Template:** Thymeleaf
- **Estilização:** Bootstrap 5.3 + CSS Personalizado
- **Banco de Dados:** MySQL 8.x
- **Gerenciador de Dependências:** Maven

---

## 🏗 Arquitetura do Sistema

O projeto segue o padrão de arquitetura em camadas para isolar responsabilidades e manter o código limpo:

```text
src/main/java/uel/br/valorantscrimtracker/
│
├── model/          # Entidades JPA (Partida, Jogador, Round, EstatisticaJogador)
├── repository/     # Interfaces Spring Data JPA para acesso ao banco MySQL
├── service/        # Regras de negócio, validações e comunicação com repositories
├── controller/     # Controllers Spring MVC para gestão de rotas e requisições
└── view/           # Templates Thymeleaf + HTML5/CSS/Bootstrap (src/main/resources/templates)
