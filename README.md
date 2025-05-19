# 🛵 FLEET - Sistema de Rastreamento Interno de Motos

Projeto desenvolvido para a disciplina de Java Advanced, com foco em rastreabilidade e controle de motos no pátio da empresa **Mottu**, utilizando Spring Boot, Oracle e Bluetooth Low Energy (BLE).

---

## 📌 Objetivo

O sistema permite:
- Cadastro e controle de zonas do pátio.
- Registro e monitoramento de motos em tempo real.
- Mapeamento visual por zona.
- Alocação automática de motos com base em status.
- Integração com beacons BLE e QR codes para entrada/saída.

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

## 🧱 Estrutura do Projeto

```
src/
├── controller       # Endpoints REST
├── dto              # Transferência de dados
├── exception        # Tratamento de erros global
├── model            # Entidades JPA (Moto, Zona)
├── repository       # Repositórios JPA
├── service          # Regras de negócio
└── resources/
    └── application.yml
```

---

## 🛠️ Como Rodar o Projeto

### 1. Pré-requisitos
- Java JDK 17 ou superior
- Maven instalado
- Oracle Database (local ou da FIAP)
- IDE como IntelliJ ou VS Code

### 2. Configurar o `application.yml`
Edite o arquivo em `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    username: SEU_USUARIO
    password: SUA_SENHA
```

### 3. Executar a aplicação
No terminal ou pela IDE:

```bash
./mvnw spring-boot:run
```

---

## 📚 Endpoints Principais

### 🔧 Moto

- `POST /api/motos` — Cadastrar moto  
- `GET /api/motos?status=APTAS` — Listar motos por status  
- `GET /api/motos/{id}` — Buscar moto por ID  

### 🧭 Zona

- `POST /api/zonas` — Cadastrar zona  
- `GET /api/zonas` — Listar zonas  

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

Disciplina: Java Advanced - FIAP  

Turma: 2TDSPZ
