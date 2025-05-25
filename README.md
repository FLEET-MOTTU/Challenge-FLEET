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
- Filtros por status, paginação e ordenação nas consultas
- Conversão entre entidades e DTOs
- Lógica de alocação automática de motos por zona
- Tratamento global de exceções
- Cache aplicado na listagem de motos por status com `@Cacheable`
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
    └── application.properties  # Configurações do Oracle e do Cache
```

---

## 🛠️ Como Rodar o Projeto

### 1. Pré-requisitos
- Java JDK 17+
- Maven
- Oracle Database
- IDE (IntelliJ, VS Code ou Eclipse)

### 2. Configurar o `application.properties`
```properties
spring.application.name=fleet
server.port=8080

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.show-sql=true

spring.cache.type=simple
integracao.zona.url=http://localhost:8081/api/zonas/sincronizar
upload.mapa.diretorio=src/main/resources/static/images
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
  "id": 1,
  "nome": "Zona de Aprovadas",
  "tipo": "APTAS"
}
```

#### 5. Listar Zonas com Paginação
- **GET** `/api/zonas?page=0&size=5`

#### 6. Buscar Zona por ID
- **GET** `/api/zonas/{id}`

---

### 👷 FUNCIONÁRIOS

#### 7. Cadastrar Funcionário
- **POST** `/funcionarios`
```json
{
  "nome": "Maria Oliveira",
  "telefone": "11988887777",
  "cargo": "Reboque",
  "adm": false
}
```

#### 8. Buscar Funcionário por ID
- **GET** `/funcionarios/{id}`

#### 9. Atualizar Funcionário
- **PUT** `/funcionarios/{id}`

#### 10. Deletar Funcionário
- **DELETE** `/funcionarios/{id}`

---

### 🔐 AUTENTICAÇÃO POR LINK MÁGICO

#### 11. Gerar Link Mágico
- **POST** `/auth/magic-link`
```json
{
  "telefone": "11995574552"
}
```

#### 12. Validar Token Mágico
- **POST** `/auth/validar-token`
```json
{
  "token": "abc123",
  "dispositivo": "celular-joao.csilva"
}
```

---

### 🗺️ MAPA

#### 13. Upload de Imagem de Mapa
- **POST** `/mapa/upload`
  - **form-data**:
    - `file`: arquivo PNG ou JPG
    - `tipo`: `ZERADO` ou `COM_ZONAS`

#### 14. Buscar Mapa Mais Recente por Tipo
- **GET** `/mapa/recente/{tipo}`

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

- [x] CRUD completo de funcionário com código mockado
- [x] Requisito de ID na criação de zona + GET por ID
- [x] Seeder populando banco com dados via Service
- [x] Geração de código mockado ao registrar funcionário
- [x] Envio de zona para outra API Java com RestTemplate
- [x] Upload e atualização de imagem do mapa (ZERADO e COM_ZONAS)
- [x] Upload controlado, com diretório configurável e nomes únicos
- [x] Retorno da URL da imagem mais recente via endpoint

---

## 👤 Desenvolvedores

- **Amanda Mesquita Cirino da Silva**
- **Beatriz Ferreira Cruz**    
- **Journey Tiago Lopes Ferreira**

**Disciplina:** Java Advanced  
**Turma:** 2TDSPZ
