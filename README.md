# 🛵 FLEET - Sistema de Rastreamento Interno de Motos

Projeto desenvolvido para a disciplina de Java Advanced na FIAP, com o objetivo de rastrear, organizar e gerenciar a movimentação de motos em pátios da empresa Mottu.

---

## 💡 Visão Geral da Solução

O sistema FLEET foi idealizado para resolver o problema real da empresa Mottu no controle de entrada, alocação e saída de motos de seus pátios. Ele utiliza como conceito base:

- Mapeamento do pátio com zonas delimitadas (ex: Manutenção, Aprovadas, Vistoria).
- Cadastro e rastreamento de motos com status atual.
- Alocação automática da moto em uma zona adequada.
- Utilização de beacons e tags Bluetooth Low Energy (BLE) para rastreamento interno em tempo real.
- Interface que permite controle visual da localização e status das motos.

---

## ☕ Participação do Java na Solução

A camada Java é responsável por toda a **regra de negócio, persistência e exposição da API REST** que conecta os apps ao banco de dados.

Funções principais implementadas com Java + Spring Boot:
- Cadastro de motos e zonas (endpoints REST com validação).
- Consulta de motos por status, com paginação e ordenação.
- Conversão de entidades para DTOs e vice-versa.
- Regras para vincular motos a zonas.
- Tratamento global de erros e validação de dados.
- Cache de zonas para otimização das consultas.

Toda a estrutura de backend foi feita com Java 17, usando as melhores práticas da arquitetura em camadas.

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- Spring Cache  
- Bean Validation  
- Oracle Database  
- Maven  

---

## 📦 Estrutura do Projeto

```
src/
├── controller       # Endpoints REST (MotoController, ZonaController)
├── dto              # Objetos de Transferência de Dados (input/output)
├── exception        # Tratamento global de erros
├── model            # Entidades JPA (Moto, Zona)
├── repository       # Interfaces JPA (MotoRepository, ZonaRepository)
├── service          # Regras de negócio (MotoService, ZonaService)
└── resources/
    └── application.yml  # Configurações de banco Oracle e cache
```

---

## 🛠️ Como Rodar o Projeto

### 1. Pré-requisitos
- Java JDK 17 ou superior
- Maven instalado
- Oracle Database (local ou da FIAP)
- IDE como IntelliJ ou VS Code

### 2. Configurar o `application.yml`
```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    username: SEU_USUARIO
    password: SUA_SENHA
```

### 3. Executar a aplicação
```bash
./mvnw spring-boot:run
```

---

## 📚 Endpoints Principais

### 🔧 Moto

- `POST /api/motos` — Cadastrar moto  
```json
{
  "placa": "DEF5678",
  "status": "APTAS",
  "tagBle": "BLE-0002",
  "zonaId": 2
}
```
- `GET /api/motos?status=APTAS&page=0&size=5` — Listar motos por status com paginação  
- `GET /api/motos/{id}` — Buscar moto por ID  

### 🧭 Zona

- `POST /api/zonas` — Cadastrar zona  
```json
{
  "nome": "Zona de Aprovadas",
  "tipo": "APTAS"
}
```
- `GET /api/zonas?page=0&size=5` — Listar zonas com paginação

---

## 🔄 Explicando os Parâmetros de Paginação

- `page`: número da página (0 é a primeira)
- `size`: quantidade de itens por página

🧪 Exemplo:
```
GET /api/motos?status=APTAS&page=1&size=5
```
→ Retorna a segunda página de motos com status APTAS, com 5 por página.

---

## ✅ Requisitos Atendidos

- [x] CRUD com validações  
- [x] Relacionamento entre entidades  
- [x] Busca com filtro + paginação  
- [x] Uso de DTOs  
- [x] Cache com Spring Cache  
- [x] Tratamento global de exceções  
- [x] Conexão com banco Oracle  

---

## 👤 Desenvolvido por

- Beatriz Ferreira Cruz  
- Amanda Mesquita Cirino da Silva  
- Journey Tiago Lopes Ferreira  

Disciplina: Java Advanced  
Turma: 2TDSPZ
