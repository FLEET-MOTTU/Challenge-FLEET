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
- CRUD de motos, zonas e funcionários com validações
- Login por credenciais e link mágico (administradores e operadores)
- Upload e consulta de imagem de mapa (ZERADO / COM_ZONAS)
- Filtros, paginação e ordenação nos endpoints
- Integração com outra API para sincronização de zonas
- Uso de DTOs, tratamento global de erros e boas práticas REST
- Cache com Spring Cache
- Gerador automático de código de funcionário

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
├── controller       # Endpoints REST
├── dto              # Objetos de Transferência de Dados
├── exception        # Tratamento global de erros
├── model            # Entidades JPA
├── repository       # Interfaces JPA
├── service          # Regras de negócio
└── resources/
    └── application.properties  # Configurações do Oracle, cache e pastas
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

- **POST** `/api/motos`  
```json
{
  "placa": "DEF5678",
  "status": "APTAS",
  "tagBle": "BLE-0002",
  "zonaId": 2
}
```

- **GET** `/api/motos?status=APTAS&page=0&size=5`  
- **GET** `/api/motos/{id}`

---

### 🧭 ZONAS

- **POST** `/api/zonas`  
```json
{
  "id": 1,
  "nome": "Zona de Aprovadas",
  "tipo": "APTAS"
}
```

- **GET** `/api/zonas?page=0&size=5`  
- **GET** `/api/zonas/{id}`

---

### 👷 FUNCIONÁRIOS

- **POST** `/funcionarios`  
```json
{
  "nome": "Maria Oliveira",
  "telefone": "11988887777",
  "cargo": "Reboque",
  "adm": false,
  "login": "maria",
  "senha": "senha123"
}
```

- **GET** `/funcionarios`  
- **GET** `/funcionarios/{id}`  
- **PUT** `/funcionarios/{id}`  
- **DELETE** `/funcionarios/{id}`

---

### 🔐 AUTENTICAÇÃO

#### Link Mágico (Funcionários)

- **POST** `/auth/magic-link`  
```json
{
  "telefone": "11995574552"
}
```

- **POST** `/auth/validar-token`  
```json
{
  "token": "abc123",
  "dispositivo": "celular-joao.csilva"
}
```

#### Login por Credenciais (ADM ou Funcionário)

- **POST** `/auth/login`  
```json
{
  "login": "ADM-PATIO-ZL",
  "senha": "123456"
}
```

---

### 🗺️ MAPA

- **POST** `/mapa/upload`  
  - `form-data`:
    - `file`: arquivo `.png` ou `.jpg`
    - `tipo`: `ZERADO` ou `COM_ZONAS`

- **GET** `/mapa/recente/ZERADO`  
- **GET** `/mapa/recente/COM_ZONAS`

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
- [x] Login via senha (adm) e via token mágico (funcionário)
- [x] ID obrigatório na criação de zona + busca por ID
- [x] Seeder inicial com motos, zonas e funcionários
- [x] Upload e listagem de imagens do mapa
- [x] Endpoint de sincronização com API externa
- [x] Cache ativo com `@Cacheable`
- [x] Upload com caminho configurável e nomes únicos
- [x] Arquitetura em camadas com uso de DTOs e validações

---

## 👤 Desenvolvedores

- **Amanda Mesquita Cirino da Silva**  
- **Beatriz Ferreira Cruz**    
- **Journey Tiago Lopes Ferreira**

**Disciplina:** Java Advanced  
**Turma:** 2TDSPZ
