<<<<<<< HEAD
# OrbitalWatch API — Spring Boot + MySQL

## Objetivo
API REST para monitoramento de missões espaciais, regiões monitoradas, eventos ambientais e notificações de alerta. Global Solution 2026 — FIAP 3SIR.

## Stack
- Java 17 + Spring Boot 3.2.5
- MySQL 8.0
- ModelMapper 3.1.1
- SpringDoc OpenAPI (Swagger UI)
- Porta: **9000** | Versão API: **v2**

## Subir o MySQL com Docker
```bash
docker run -d --name orbitalwatch-db \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=orbitalwatch \
  -p 3306:3306 \
  mysql:8.0
```
Aguardar ~15s para o container inicializar.

## Executar a aplicação
```bash
./mvnw spring-boot:run
```

## Acessar Swagger UI
```
http://localhost:9000/
```

## Endpoints (versão v2)
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/v2/missions | Listar missões |
| POST | /api/v2/missions | Criar missão |
| GET | /api/v2/missions/{id} | Buscar missão |
| PUT | /api/v2/missions/{id} | Atualizar missão |
| DELETE | /api/v2/missions/{id} | Remover missão |
| GET | /api/v2/regions | Listar regiões |
| POST | /api/v2/regions | Criar região |
| GET | /api/v2/events | Listar eventos |
| POST | /api/v2/events | Criar evento |
| GET | /api/v2/alerts | Listar alertas |
| POST | /api/v2/alerts | Criar alerta |
=======
# GlobalSolution-PrimeiroSemestre
>>>>>>> 2eae6033102315760556dfbe868bf4f0ee2e229a
