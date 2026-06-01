# 🛰️ OrbitalWatch API

> **Plataforma de Monitoramento Ambiental via Satélite**  
> Global Solution 2026 — FIAP | Microservice and Web Engineering & IT Services  
> Prof. Antonio Carlos de Lima Junior | Turma: 3SIR

---

## 🌍 Contexto do Projeto

A humanidade vive hoje uma nova corrida espacial. Satélites como o **Sentinel-2**, **Landsat-9**, **CBERS-4A**, **GOES-16** e **Jason-3** orbitam a Terra continuamente, coletando dados de altimetria oceânica, temperatura superficial, cobertura vegetal, índice pluviométrico e muito mais. Esses dados, quando processados de forma inteligente, se tornam uma ferramenta poderosa para salvar vidas, proteger ecossistemas e orientar decisões estratégicas em escala global.

É nesse cenário que nasce o **OrbitalWatch**: uma plataforma REST desenvolvida em **Java com Spring Boot** que centraliza, organiza e distribui dados sobre missões espaciais, regiões monitoradas, eventos ambientais críticos e notificações de alerta — conectando a tecnologia espacial aos desafios reais da sociedade.

---

## 🚀 O que é o OrbitalWatch?

O **OrbitalWatch** é uma API RESTful que simula o backend de um sistema de monitoramento ambiental baseado em dados satelitais. A plataforma permite:

- **Registrar missões espaciais** ativas e históricas (ex: Sentinel-2 da ESA, Jason-3 da NASA/CNES)
- **Cadastrar regiões monitoradas** ao redor do globo com suas coordenadas e características climáticas
- **Registrar eventos ambientais** detectados por satélites: queimadas, inundações, secas, tempestades e desmatamentos
- **Gerar e gerenciar notificações de alerta** para autoridades, pesquisadores e população em risco

Cada evento recebe uma **severidade de 1 a 10** e um **impacto estimado**, permitindo priorização automática de resposta. Eventos com severidade ≥ 9 (como a queimada do Pantanal de 2025 com 150.000 hectares afetados) são classificados como **CRÍTICO** e disparam alertas imediatos.

---

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Função |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.2.5 | Framework principal |
| Spring Data JPA | 3.2.5 | Persistência e mapeamento ORM |
| Spring Validation | 3.2.5 | Validação dos DTOs |
| MySQL | 8.0 | Banco de dados relacional |
| ModelMapper | 3.1.1 | Mapeamento Entity ↔ DTO |
| SpringDoc OpenAPI | 2.3.0 | Documentação Swagger UI |
| Docker | latest | Containerização do banco de dados |

---

## 📁 Estrutura do Projeto

```
br.com.orbitalwatch/
│
├── entity/                        ← Camada de domínio (JPA Entities)
│   ├── SatelliteMission.java      ← Missões espaciais (Sentinel, Landsat, GOES...)
│   ├── MonitoredRegion.java       ← Regiões monitoradas (continente, coords, clima)
│   ├── SatelliteEvent.java        ← Eventos ambientais detectados por satélite
│   └── AlertNotification.java     ← Notificações geradas para cada evento
│
├── dto/
│   ├── request/                   ← DTOs de entrada (dados recebidos pelo cliente)
│   │   ├── SatelliteMissionRequestDTO.java
│   │   ├── MonitoredRegionRequestDTO.java
│   │   ├── SatelliteEventRequestDTO.java
│   │   └── AlertNotificationRequestDTO.java
│   └── response/                  ← DTOs de saída (dados retornados ao cliente)
│       ├── SatelliteMissionResponseDTO.java
│       ├── MonitoredRegionResponseDTO.java
│       ├── SatelliteEventResponseDTO.java
│       └── AlertNotificationResponseDTO.java
│
├── repository/                    ← Camada de acesso a dados (Spring Data JPA)
│   ├── SatelliteMissionRepository.java
│   ├── MonitoredRegionRepository.java
│   ├── SatelliteEventRepository.java
│   └── AlertNotificationRepository.java
│
├── service/                       ← Camada de regras de negócio
│   ├── SatelliteMissionService.java
│   ├── MonitoredRegionService.java
│   ├── SatelliteEventService.java
│   └── AlertNotificationService.java
│
├── controller/                    ← Camada de exposição da API (REST Controllers)
│   ├── SatelliteMissionController.java  → /api/v2/missions
│   ├── MonitoredRegionController.java   → /api/v2/regions
│   ├── SatelliteEventController.java    → /api/v2/events
│   └── AlertNotificationController.java → /api/v2/alerts
│
├── config/                        ← Configurações da aplicação
│   ├── ModelMapperConfig.java     ← Bean do ModelMapper
│   └── SwaggerConfig.java         ← Configuração da documentação OpenAPI
│
└── OrbitalwatchApplication.java   ← Entry point da aplicação Spring Boot
```

---

## 🗄️ Modelagem de Dados

### Diagrama de Entidades e Relacionamentos

```
┌─────────────────────────┐         ┌─────────────────────────────┐
│     satellite_missions  │         │       monitored_regions     │
│─────────────────────────│         │─────────────────────────────│
│ id (PK)                 │         │ id (PK)                     │
│ mission_name  (NOT NULL)│         │ region_name  (NOT NULL)     │
│ responsible_agency (NN) │         │ continent    (NOT NULL)     │
│ origin_country          │         │ area_type                   │
│ launch_year             │         │ latitude                    │
│ mission_status          │         │ longitude                   │
│ technology              │         │ country                     │
│ description             │         │ climate_zone                │
└────────────┬────────────┘         └──────────────┬──────────────┘
             │  1                                   │  1
             │                                      │
             │  N                                   │  N
             └──────────┬───────────────────────────┘
                        │
             ┌──────────▼──────────────┐
             │     satellite_events    │
             │─────────────────────────│
             │ id (PK)                 │
             │ event_type   (NOT NULL) │  ← QUEIMADA, INUNDACAO, SECA,
             │ severity     (NOT NULL) │     TEMPESTADE, DESMATAMENTO
             │ estimated_impact        │
             │ observation_date        │
             │ status                  │  ← ATIVO, MONITORANDO, RESOLVIDO
             │ description             │
             │ satellite_source        │
             │ mission_id (FK)         │
             │ region_id  (FK)         │
             └──────────┬──────────────┘
                        │  1
                        │
                        │  N
             ┌──────────▼──────────────┐
             │   alert_notifications   │
             │─────────────────────────│
             │ id (PK)                 │
             │ notification_type (NN)  │  ← EMAIL, PUSH, SMS
             │ sent_at          (NN)   │
             │ recipient_email         │
             │ message                 │
             │ status                  │
             │ priority                │  ← CRITICO, ALTO, MEDIO
             │ was_read                │
             │ event_id (FK)           │
             └─────────────────────────┘
```

### Relacionamentos

| Relação | Cardinalidade | Descrição |
|---|---|---|
| SatelliteMission → SatelliteEvent | 1 para N | Uma missão pode detectar múltiplos eventos |
| MonitoredRegion → SatelliteEvent | 1 para N | Uma região pode ter múltiplos eventos registrados |
| SatelliteEvent → AlertNotification | 1 para N | Um evento pode gerar múltiplos alertas |

---

## 🌐 Endpoints da API (versão v2)

### 🛸 Missões Espaciais — `/api/v2/missions`

| Método | Rota | Descrição | Status de retorno |
|---|---|---|---|
| `GET` | `/api/v2/missions` | Lista todas as missões cadastradas | 200 OK |
| `GET` | `/api/v2/missions/{id}` | Busca uma missão pelo ID | 200 OK / 404 |
| `POST` | `/api/v2/missions` | Cadastra nova missão espacial | 201 Created |
| `PUT` | `/api/v2/missions/{id}` | Atualiza dados de uma missão | 200 OK / 404 |
| `DELETE` | `/api/v2/missions/{id}` | Remove uma missão | 204 No Content |

**Exemplo de body para POST `/api/v2/missions`:**
```json
{
  "missionName": "Sentinel-2A",
  "responsibleAgency": "ESA",
  "originCountry": "Europa",
  "launchYear": 2015,
  "missionStatus": "ATIVA",
  "technology": "Imageamento Multiespectral",
  "description": "Satélite de observação da Terra focado em monitoramento de vegetação, solo e água"
}
```

---

### 🗺️ Regiões Monitoradas — `/api/v2/regions`

| Método | Rota | Descrição | Status de retorno |
|---|---|---|---|
| `GET` | `/api/v2/regions` | Lista todas as regiões monitoradas | 200 OK |
| `GET` | `/api/v2/regions/{id}` | Busca uma região pelo ID | 200 OK / 404 |
| `POST` | `/api/v2/regions` | Cadastra nova região monitorada | 201 Created |
| `PUT` | `/api/v2/regions/{id}` | Atualiza dados de uma região | 200 OK / 404 |
| `DELETE` | `/api/v2/regions/{id}` | Remove uma região | 204 No Content |

**Exemplo de body para POST `/api/v2/regions`:**
```json
{
  "regionName": "Pantanal Mato-Grossense",
  "continent": "América do Sul",
  "areaType": "FLORESTA",
  "latitude": -17.7361,
  "longitude": -57.4706,
  "country": "Brasil",
  "climateZone": "Tropical"
}
```

---

### 🔥 Eventos Satelitais — `/api/v2/events`

| Método | Rota | Descrição | Status de retorno |
|---|---|---|---|
| `GET` | `/api/v2/events` | Lista todos os eventos detectados | 200 OK |
| `GET` | `/api/v2/events/{id}` | Busca um evento pelo ID | 200 OK / 404 |
| `POST` | `/api/v2/events` | Registra novo evento ambiental | 201 Created |
| `PUT` | `/api/v2/events/{id}` | Atualiza dados de um evento | 200 OK / 404 |
| `DELETE` | `/api/v2/events/{id}` | Remove um evento | 204 No Content |

**Exemplo de body para POST `/api/v2/events`:**
```json
{
  "eventType": "QUEIMADA",
  "severity": 9,
  "estimatedImpact": 8.5,
  "observationDate": "2025-08-15",
  "status": "ATIVO",
  "description": "Incêndio de grande proporção detectado no Pantanal. Área afetada: 150.000 hectares.",
  "satelliteSource": "Sentinel-2A",
  "missionId": 1,
  "regionId": 1
}
```

**Exemplo de response:**
```json
{
  "id": 1,
  "eventType": "QUEIMADA",
  "severity": 9,
  "estimatedImpact": 8.5,
  "observationDate": "2025-08-15",
  "status": "ATIVO",
  "description": "Incêndio de grande proporção detectado no Pantanal. Área afetada: 150.000 hectares.",
  "satelliteSource": "Sentinel-2A",
  "missionId": 1,
  "missionName": "Sentinel-2A",
  "regionId": 1,
  "regionName": "Pantanal Mato-Grossense"
}
```

---

### 🔔 Notificações de Alerta — `/api/v2/alerts`

| Método | Rota | Descrição | Status de retorno |
|---|---|---|---|
| `GET` | `/api/v2/alerts` | Lista todas as notificações | 200 OK |
| `GET` | `/api/v2/alerts/{id}` | Busca uma notificação pelo ID | 200 OK / 404 |
| `POST` | `/api/v2/alerts` | Cria nova notificação de alerta | 201 Created |
| `PUT` | `/api/v2/alerts/{id}` | Atualiza uma notificação | 200 OK / 404 |
| `DELETE` | `/api/v2/alerts/{id}` | Remove uma notificação | 204 No Content |

**Exemplo de body para POST `/api/v2/alerts`:**
```json
{
  "notificationType": "EMAIL",
  "sentAt": "2025-08-15T14:30:00",
  "recipientEmail": "defesacivil@pantanal.gov.br",
  "message": "ALERTA CRÍTICO: Queimada de severidade 9 detectada no Pantanal Mato-Grossense. Ação imediata necessária.",
  "status": "ENVIADO",
  "priority": "CRITICO",
  "wasRead": false,
  "eventId": 1
}
```

---

## ⚙️ Configuração da Aplicação

### `application.properties`

```properties
# Porta da aplicação
server.port=9000

# Conexão com MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/orbitalwatch
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Swagger UI na rota raiz
springdoc.swagger-ui.path=/
springdoc.api-docs.path=/api-docs
```

---

## 🐳 Como Executar

### Pré-requisitos
- Java 17 instalado
- Docker instalado
- Maven (ou usar o `./mvnw` incluído no projeto)

### Passo 1 — Subir o banco MySQL com Docker

```bash
docker run -d --name orbitalwatch-db \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=orbitalwatch \
  -p 3306:3306 \
  mysql:8.0
```

Aguardar aproximadamente 15 segundos para o MySQL inicializar completamente.

### Passo 2 — Executar a aplicação Spring Boot

```bash
./mvnw spring-boot:run
```

### Passo 3 — Acessar a documentação Swagger

Abrir no navegador:
```
http://localhost:9000/
```

A interface Swagger UI exibirá todos os endpoints documentados e permitirá testá-los diretamente.

---

## 📐 Arquitetura em Camadas

```
┌───────────────────────────────────────────────────────┐
│                    CLIENT (Postman / Swagger)          │
└──────────────────────────┬────────────────────────────┘
                           │ HTTP Request
                           ▼
┌───────────────────────────────────────────────────────┐
│              CONTROLLER  (@RestController)             │
│   /api/v2/missions | /api/v2/events | /api/v2/alerts  │
│   Recebe requisições, valida DTOs, retorna responses   │
└──────────────────────────┬────────────────────────────┘
                           │ Chama o Service
                           ▼
┌───────────────────────────────────────────────────────┐
│                SERVICE  (@Service)                     │
│   Contém as regras de negócio                          │
│   Usa ModelMapper para converter Entity ↔ DTO          │
│   Lança exceções quando entidade não é encontrada      │
└──────────────────────────┬────────────────────────────┘
                           │ Chama o Repository
                           ▼
┌───────────────────────────────────────────────────────┐
│           REPOSITORY  (JpaRepository<E, Long>)        │
│   Interface Spring Data JPA                            │
│   Métodos: findAll(), findById(), save(), deleteById() │
└──────────────────────────┬────────────────────────────┘
                           │ SQL automático
                           ▼
┌───────────────────────────────────────────────────────┐
│              BANCO DE DADOS  (MySQL 8.0)               │
│   Tabelas: satellite_missions, monitored_regions,      │
│            satellite_events, alert_notifications       │
└───────────────────────────────────────────────────────┘
```

---

## 🔄 Fluxo de uma Requisição

Exemplo: registrar um novo evento de queimada detectado pelo Sentinel-2.

```
1. Cliente envia POST /api/v2/events com o JSON do evento

2. SatelliteEventController recebe a requisição
   └── Valida o @RequestBody com @Valid
   └── Chama service.create(dto)

3. SatelliteEventService processa a regra de negócio
   └── Busca a SatelliteMission pelo missionId
   └── Busca a MonitoredRegion pelo regionId
   └── Usa ModelMapper para converter DTO → Entity
   └── Chama repository.save(entity)
   └── Usa ModelMapper para converter Entity → ResponseDTO
   └── Retorna o ResponseDTO

4. SatelliteEventController retorna ResponseEntity com status 201 Created
```

---

## 📊 Dados Reais Utilizados no Sistema

Os eventos e missões registrados no OrbitalWatch são inspirados em situações reais monitoradas por satélites:

| Evento | Satélite | Severidade | Status |
|---|---|---|---|
| Queimada Pantanal (150k ha) | Sentinel-2A | 9/10 | ATIVO |
| Inundação Amazônica | Landsat-9 | 7/10 | MONITORANDO |
| Seca Nordeste (pluviometria -80%) | CBERS-4A | 8/10 | ATIVO |
| Tempestade Costeira SP (95 km/h) | GOES-16 | 6/10 | RESOLVIDO |
| Elevação Nível Mar RJ (+0,4m) | Jason-3 | 8/10 | MONITORANDO |
| Desmatamento Pará (3.000 campos) | Sentinel-2A | 7/10 | ATIVO |
| Chuvas Serra Gaúcha (300mm/24h) | GOES-16 | 9/10 | ATIVO |

---

## 🎯 Alinhamento com os ODS da ONU

O OrbitalWatch contribui diretamente para os Objetivos de Desenvolvimento Sustentável:

- **ODS 13 — Ação Climática:** monitoramento e alerta de eventos climáticos extremos
- **ODS 15 — Vida Terrestre:** detecção de desmatamentos e queimadas em tempo real
- **ODS 11 — Cidades Sustentáveis:** alertas de inundações e riscos costeiros
- **ODS 9 — Inovação e Infraestrutura:** uso de tecnologia espacial para gestão de risco
- **ODS 2 — Fome Zero:** monitoramento de secas e impacto na produção agrícola

---

## 👥 Equipe

Projeto desenvolvido para a Global Solution 2026 — FIAP  
Curso: Sistemas de Informação — 3º ano | Turma: 3SIR  
Disciplina: Microservice and Web Engineering & IT Services
