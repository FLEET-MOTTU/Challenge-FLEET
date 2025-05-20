# 🛵 FLEET (Fleet Location and Efficient Equipment Tracking)  
### Sistema de Rastreamento e Alocação Inteligente de Frotas

Projeto desenvolvido para a disciplina de **Java Advanced** na **FIAP**, com o objetivo de rastrear, organizar e gerenciar a movimentação de motos nos pátios da empresa **Mottu**.

---

## 💡 Visão Geral da Solução

O sistema **FLEET** foi idealizado para resolver um problema real da empresa Mottu no controle de entrada, alocação e saída de motos em seus pátios. Ele utiliza como conceito base:

- Mapeamento do pátio com zonas delimitadas (ex: *Manutenção*, *Aprovadas*, *Vistoria*)
- Cadastro e rastreamento de motos com status atualizado em tempo real
- Alocação automática da moto em uma zona adequada
- Utilização de beacons e tags Bluetooth Low Energy (BLE) para rastreamento interno
- Interface para controle visual da localização e status das motos

---

## ⚙️ Camada Backend com Java

A camada em Java é responsável por toda a **regra de negócio**, **persistência de dados** e **exposição da API REST** que conecta os aplicativos ao banco Oracle.

### Principais funcionalidades:
- CRUD de motos e zonas, com validação de dados
- Filtros por status e paginação nas consultas
- Conversão entre entidades e DTOs
- Lógica de alocação automática de motos por zona
- Tratamento global de exceções
- Cache para otimizar consultas de zonas
- Cadastro e autenticação de funcionários por link mágico

> Toda a estrutura segue arquitetura em camadas e foi desenvolvida com **Java 17 + Spring Boot 3**.

---

## 🚀 Tecnologias Utilizadas

### Backend
- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- Spring Cache  
- Bean Validation

### Banco de Dados
- Oracle Database

### Build
- Maven

---

## 📦 Estrutura do Projeto

```
src/
├── controller       # Endpoints REST
├── dto              # Objetos de Transferência de Dados
├── exception        # Tratamento global de erros
├── model            # Entidades JPA
├── repository       # Interfaces JPA
├── service          # Regras de negócio
└── resources/
    └── application.yml  # Configurações do Oracle e do Cache
```

---

## 🛠️ Como Rodar o Projeto

### 1. Pré-requisitos
- Java JDK 17+
- Maven
- Oracle Database
- IDE (IntelliJ, VS Code ou Eclipse)

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

## 📚 Endpoints da API

### 🔧 MOTOS

#### 1. Cadastrar Moto
- **POST** `/api/motos`
```json
{
  "placa": "DEF5678",
  "status": "APTAS",
  "tagBle": "BLE-0002",
  "zonaId": 2
}
```

#### 2. Listar Motos por Status (com paginação)
- **GET** `/api/motos?status=APTAS&page=0&size=5`

#### 3. Buscar Moto por ID
- **GET** `/api/motos/{id}`

---

### 🧭 ZONAS

#### 4. Cadastrar Zona
- **POST** `/api/zonas`
```json
{
  "nome": "Zona de Aprovadas",
  "tipo": "APTAS"
}
```

#### 5. Listar Zonas com Paginação
- **GET** `/api/zonas?page=0&size=5`

---

### 👷 FUNCIONÁRIOS

#### 6. Cadastrar Funcionário
- **POST** `/funcionarios`
```json
{
  "nome": "Maria Oliveira",
  "telefone": "11988887777",
  "cargo": "Reboque"
}
```

---

### 🔐 AUTENTICAÇÃO POR LINK MÁGICO

#### 7. Gerar Link Mágico
- **POST** `/auth/magic-link`
```json
{
  "telefone": "11995574552"
}
```

#### 8. Validar Token Mágico
- **POST** `/auth/validar-token`
```json
{
  "token": "abc123",
  "dispositivo": "celular-joao.csilva"
}
```

---

## 🔄 Parâmetros de Paginação

- `page`: número da página (0 = primeira)
- `size`: quantidade de itens por página

Exemplo:
```
GET /api/motos?status=APTAS&page=1&size=5
```

---

## ✅ Requisitos Atendidos

- [x] Cadastro e consulta de motos e zonas  
- [x] Validações com Bean Validation  
- [x] Relacionamento entre entidades  
- [x] Paginação com filtros  
- [x] Uso de DTOs  
- [x] Cache com Spring Cache  
- [x] Tratamento global de exceções  
- [x] Conexão com banco Oracle  
- [x] Cadastro de funcionário via API  
- [x] Geração de link mágico com token expira em 24h  
- [x] Validação de token com uso único  
- [x] Registro de dispositivo no login  

---

## 👤 Desenvolvedores

- **Beatriz Ferreira Cruz**  
- **Amanda Mesquita Cirino da Silva**  
- **Journey Tiago Lopes Ferreira**

**Disciplina:** Java Advanced  
**Turma:** 2TDSPZ
