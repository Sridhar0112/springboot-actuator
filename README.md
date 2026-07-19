<div align="center">

# 📊 Student Management System
### Spring Boot Actuator · Observability Edition

![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![Prometheus](https://img.shields.io/badge/Metrics-Prometheus-E6522C?style=flat-square&logo=prometheus&logoColor=white)
![License](https://img.shields.io/badge/License-Unspecified-lightgrey?style=flat-square)

A Spring Boot application for student management, instrumented for production observability with Actuator, Micrometer, and Prometheus.

</div>

---

## 📑 Table of Contents

- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [Observability](#-observability)
- [Build Metadata](#-build-metadata)
- [License](#-license)
- [Author](#-author)

---

## 🧱 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.5.3 |
| Web | `spring-boot-starter-web` |
| Persistence | `spring-boot-starter-data-jpa` |
| Database | H2 (runtime/dev) · PostgreSQL (runtime) |
| Migrations | Flyway (`flyway-core`, `flyway-database-postgresql`) |
| Observability | Actuator + Micrometer + Prometheus registry |
| Logging | Logstash Logback Encoder (structured JSON) |
| Mail | `spring-boot-starter-mail` |
| Validation | `spring-boot-starter-validation` |
| AOP | `spring-boot-starter-aop` |
| Env Config | `spring-dotenv` |
| Build Metadata | `spring-boot-maven-plugin` (build-info) + `git-commit-id-maven-plugin` |
| Utilities | Lombok |
| Testing | `spring-boot-starter-test` |
| Build Tool | Maven (wrapper included) |

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven (or use the included `./mvnw` wrapper)
- PostgreSQL instance if running a non-H2 profile

### Clone

```bash
git clone https://github.com/Sridhar0112/springboot-actuator.git
cd springboot-actuator
```

### Run

```bash
./mvnw spring-boot:run
```

Run with a specific profile:

```bash
./mvnw spring-boot:run -Dspring.profiles.active=<profile-name>
```

> Replace `<profile-name>` with a profile actually defined under `src/main/resources`.

---

## ⚙️ Configuration

Environment variables are loaded via `spring-dotenv` from a local `.env` file (do **not** commit this file):

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

---

## 📈 Observability

Base management URL:

```
http://localhost:8080/actuator
```

| Endpoint | Purpose |
|---|---|
| `/actuator/health` | Application health |
| `/actuator/info` | Build & git metadata |
| `/actuator/metrics` | Available metrics |
| `/actuator/prometheus` | Prometheus scrape endpoint |

> Exact exposed endpoints depend on `management.endpoints.web.exposure.include` in `application.yml`.

---

## 🏗️ Build Metadata

`build-info` (Spring Boot Maven plugin) and `git-commit-id-maven-plugin` are configured, so `/actuator/info` surfaces build version and commit details after a build.

---

## 📄 License

Not currently specified in the repository.

---

## 👤 Author

**Sridhar** — [GitHub Profile](https://github.com/Sridhar0112)

<div align="center">

⭐ **Star this repo** if it helped you learn Spring Boot observability!

</div>
