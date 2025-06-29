# Tasks Manager App

Proyecto de gestión de tareas con arquitectura de microservicios utilizando Spring Boot 3.5.0, Java 21 y Docker.

## Microservicios

- `config-server` – Configuración centralizada (Spring Cloud Config Server)
- `service-registry` – Registro de servicios (Eureka)
- `auth-service` – Registro, login y emisión de JWT
- `user-service` – Gestión de perfil de usuario
- `task-service` – CRUD de tareas, historial de estado
- `notification-service` – Envío de notificaciones por cambio de estado
- `api-gateway` – Entrada única al sistema

## Requisitos

- Java 21
- Maven
- Docker
- Git

## Cómo ejecutar

```bash
mvn clean install
```
Próximamente se agregará un docker-compose.yml para levantar toda la arquitectura.
