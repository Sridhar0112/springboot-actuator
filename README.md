# Student Management System

Spring Boot Actuator · Observability Edition

A Spring Boot application for managing student records, built with production-ready observability using Spring Boot Actuator, Micrometer, and Prometheus.

**Stack:** Java 17 · Spring Boot 3.5.3 · Maven

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Configuration](#configuration)
- [Observability](#observability)
- [Build Metadata](#build-metadata)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [API Reference](#api-reference)
- [Contributing](#contributing)
- [Author](#author)

---

## Features

- **Student Management** — CRUD operations for student records (name, course, email, phone).
- **Production-Ready Observability** — Spring Boot Actuator for health checks, metrics, and info endpoints.
- **Micrometer & Prometheus Integration** — Application metrics exposed in Prometheus scrape format.
- **Custom Health Indicators** — Dedicated health checks for the email service and student service.
- **Profile-Aware Configuration** — Separate configs for dev, QA, staging, and production.
- **Data Persistence** — Spring Data JPA with Flyway migrations, supporting H2 and PostgreSQL.
- **Structured Logging** — Logstash Logback Encoder with request/response logging and MDC support.
- **Input Validation** — Bean Validation plus custom validation rules.
- **Email Notifications** — Sent on successful student creation (mocked in dev/QA, real in staging/prod).
- **Build Metadata** — `spring-boot-maven-plugin` and `git-commit-id-maven-plugin` expose build/version info.

---

## Tech Stack

| Layer | Technology | Details |
|---|---|---|
| Language | Java | 17 |
| Framework | Spring Boot | 3.5.3 |
| Web | Spring Web | `spring-boot-starter-web` |
| Persistence | Spring Data JPA | `spring-boot-starter-data-jpa` |
| Database | H2 | dev/runtime |
| Database | PostgreSQL | runtime |
| Migrations | Flyway | `flyway-core`, `flyway-database-postgresql` |
| Observability | Spring Boot Actuator | `spring-boot-starter-actuator` |
| Metrics | Micrometer | `micrometer-registry-prometheus` |
| Logging | Logback + Logstash | `logstash-logback-encoder` (structured JSON) |
| Mail | Spring Mail | `spring-boot-starter-mail` |
| Validation | Bean Validation | `spring-boot-starter-validation` + custom validators |
| AOP | Spring AOP | `spring-boot-starter-aop` |
| Environment Config | spring-dotenv | `.env` file support |
| Build Metadata | Maven Plugins | `spring-boot-maven-plugin`, `git-commit-id-maven-plugin` |
| Utilities | Lombok | `@Data`, `@Slf4j`, etc. |
| Testing | Spring Boot Test | `spring-boot-starter-test` |
| Build Tool | Maven | Wrapper included (`./mvnw`) |

---

## Configuration

Sensitive configuration (database credentials, mail server details) is managed via `spring-dotenv`. Create a `.env` file in the project root.

**⚠️ Do not commit `.env` to your repository.**

```env
DB_HOST=
DB_PORT=
DB_NAME=
DB_USERNAME=
DB_PASSWORD=
SERVER_PORT=
MAIL_USERNAME=
MAIL_PASSWORD=
SPRING_PROFILES_ACTIVE=
```

Profile-specific settings live in `src/main/resources/application-{profile}.yml` (e.g. `application-dev.yml`, `application-prod.yml`).

---

## Observability

Actuator endpoints are exposed under `/actuator`:

```
http://localhost:8080/actuator
```

| Endpoint | Purpose |
|---|---|
| `/actuator/health` | Overall application health, including custom indicators for the email and student services |
| `/actuator/info` | Build information, including version and Git commit details |
| `/actuator/metrics` | Available metrics and details on specific ones |
| `/actuator/prometheus` | Metrics in a format scrapeable by Prometheus |

> The exact set of exposed endpoints is configurable via `management.endpoints.web.exposure.include`.

---

## Build Metadata

`spring-boot-maven-plugin` (`build-info` goal) and `git-commit-id-maven-plugin` capture build and Git commit information, surfaced through `/actuator/info` — useful context for deployed instances.

---

## Prerequisites

- **JDK** 17 or higher
- **Apache Maven** 3.x (or use the included `./mvnw` wrapper)
- **PostgreSQL** — required for profiles other than the default H2 setup (e.g. `prod`, `staging`)

---

## Installation

**1. Clone the repository**

```bash
git clone https://github.com/Sridhar0112/springboot-actuator.git
cd springboot-actuator
```

**2. Configure environment variables**

Create a `.env` file in the project root:

```env
DB_HOST=your_db_host
DB_PORT=your_db_port
DB_NAME=your_db_name
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
SERVER_PORT=8080
MAIL_USERNAME=your_email@example.com
MAIL_PASSWORD=your_email_password
```

> For local development you can skip database credentials entirely and rely on the default H2 setup.

---

## Usage

**Run the application:**

```bash
./mvnw spring-boot:run
```

**Run with a specific profile** (`dev`, `qa`, `staging`, `prod`):

```bash
./mvnw spring-boot:run -Dspring.profiles.active=<profile-name>
```

### Example Use Cases

- **Student Information System** — core backend for managing student records in educational institutions
- **Observability Demonstration** — practical example of Spring Boot Actuator + Prometheus monitoring
- **Microservice Backend** — student-data microservice within a larger distributed system

---

## Project Structure

```
springboot-actuator/
├── .mvn/wrapper/maven-wrapper.properties
├── logs/
├── mvnw, mvnw.cmd
├── pom.xml
├── .env                     # create this file for configuration
├── src/
│   ├── main/
│   │   ├── java/com/sridhar/springboot/
│   │   │   ├── Config/
│   │   │   ├── Controller/
│   │   │   ├── Dto/
│   │   │   ├── Exception/
│   │   │   ├── health/service/
│   │   │   ├── logging/
│   │   │   │   ├── aspect/ config/ constants/
│   │   │   │   ├── filter/ interceptor/
│   │   │   │   └── mdc/ util/
│   │   │   ├── models/
│   │   │   ├── Service/notification/
│   │   │   └── SpringbootApplication.java
│   │   ├── resources/
│   │   │   ├── application.yml
│   │   │   ├── application-{dev,qa,staging,uat,prod}.yml
│   │   │   ├── db/migration/V1__create_student_table.sql
│   │   │   └── logback-spring.xml
│   │   └── webapp/
│   └── test/java/com/sridhar/springboot/SpringbootApplicationTests.java
└── README.md
```

---

## API Reference

Base URL: `http://localhost:8080/api/v1/students`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/add` | Add a new student |
| `GET` | `/getstudent` | List all students |
| `GET` | `/getstudent/{id}` | Get a student by ID |
| `PUT` | `/update/{id}` | Update a student by ID |
| `DELETE` | `/delete/{id}` | Delete a student by ID |

### Add Student — `POST /add`

**Request:**
```json
{
  "name": "Jane Doe",
  "course": "Spring Boot",
  "email": "jane.doe@example.com",
  "phone": "9876543210"
}
```

**Response — 201 Created:**
```json
{
  "message": "Student added successfully",
  "name": "Jane Doe"
}
```

**Response — 400 Bad Request (validation error):**
```json
{
  "timestamp": "2023-10-27T10:00:00.000+00:00",
  "status": 400,
  "message": "Validation Failed - Invalid Request",
  "path": "/api/v1/students/add",
  "errors": {
    "email": ["Invalid email format"]
  }
}
```

### Get Student by ID — `GET /getstudent/{id}`

**Response — 200 OK:**
```json
{
  "Student": {
    "ID": 1,
    "name": "Jack Wilson",
    "course": "JAVA",
    "email": "student1@studentmanagement.com",
    "phone": "9876500001",
    "createdAt": "2023-10-27T10:00:00",
    "updatedAt": "2023-10-27T10:00:00"
  }
}
```

---

## Contributing

Contributions are welcome:

1. Fork the repository
2. Create a branch — `git checkout -b feature/your-feature-name`
3. Commit your changes — `git commit -m 'Add some feature'`
4. Push the branch — `git push origin feature/your-feature-name`
5. Open a Pull Request

Please follow the project's coding standards and include tests where relevant.

---

## Author

**Sridhar** — [GitHub](https://github.com/Sridhar0112)

---

⭐ If this project helped you learn Spring Boot observability, consider starring the repo.
