
<div align="center">

# 📊 Student Management System
### Spring Boot Actuator · Observability Edition

![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![Prometheus](https://img.shields.io/badge/Metrics-Prometheus-E6522C?style=flat-square&logo=prometheus&logoColor=white)
![License](https://img.shields.io/badge/License-Unspecified-lightgrey?style=flat-square)

</div>

A Spring Boot application designed for managing student records, enhanced with production-ready observability features using Spring Boot Actuator, Micrometer, and Prometheus metrics.

---

## 📑 Table of Contents

- [🚀 Features](#-features)
- [🧱 Tech Stack](#-tech-stack)
- [⚙️ Configuration](#-configuration)
- [📈 Observability](#-observability)
- [🏗️ Build Metadata](#-build-metadata)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [API Reference](#-api-reference)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)

---

## 🚀 Key Features

- **Student Management:** CRUD operations for student records including name, course, email, and phone number.
- **Production-Ready Observability:** Leverages Spring Boot Actuator for health checks, metrics, and info endpoints.
- **Micrometer & Prometheus Integration:** Exposes metrics in Prometheus format for external monitoring.
- **Custom Health Indicators:** Includes health checks for the email service and student service.
- **Profile-Aware Configuration:** Supports different configurations for development, QA, staging, and production environments.
- **Data Persistence:** Utilizes Spring Data JPA with Flyway for database migrations (supports H2 and PostgreSQL).
- **Robust Logging:** Implements structured logging with Logstash Logback Encoder and includes request/response logging with MDC support.
- **Input Validation:** Enforces data integrity through Bean Validation and custom validation rules.
- **Email Notifications:** Sends email notifications upon successful student creation (mocked in dev/qa, real in prod/staging).
- **Build Metadata:** Integrates `spring-boot-maven-plugin` and `git-commit-id-maven-plugin` to expose build information.

---

## 🧱 Tech Stack

| Layer | Technology | Details |
|---|---|---|
| Language | Java | 17 |
| Framework | Spring Boot | 3.5.3 |
| Web | Spring Web | `spring-boot-starter-web` |
| Persistence | Spring Data JPA | `spring-boot-starter-data-jpa` |
| Database | H2 | `runtime/dev` |
| Database | PostgreSQL | `runtime` |
| Migrations | Flyway | `flyway-core`, `flyway-database-postgresql` |
| Observability | Spring Boot Actuator | `spring-boot-starter-actuator` |
| Metrics | Micrometer | `micrometer-registry-prometheus` |
| Logging | Logback + Logstash | `logstash-logback-encoder` (structured JSON) |
| Mail | Spring Mail | `spring-boot-starter-mail` |
| Validation | Bean Validation | `spring-boot-starter-validation`, custom validators |
| AOP | Spring AOP | `spring-boot-starter-aop` |
| Environment Config | `spring-dotenv` | `.env` file support |
| Build Metadata | Maven Plugins | `spring-boot-maven-plugin`, `git-commit-id-maven-plugin` |
| Utilities | Lombok | Data, Slf4j, etc. |
| Testing | Spring Boot Test | `spring-boot-starter-test` |
| Build Tool | Maven | Wrapper included (`./mvnw`) |

---

## ⚙️ Configuration

Environment variables for sensitive configurations (like database credentials, mail server details) are managed via `spring-dotenv`. Create a `.env` file in the root directory. **Do not commit this file to your repository.**

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

Profile-specific configurations are defined in `src/main/resources/application-{profile}.yml` (e.g., `application-dev.yml`, `application-prod.yml`).

---

## 📈 Observability

The application exposes actuator endpoints under the `/actuator` path.

Base management URL:

```
http://localhost:8080/actuator
```

| Endpoint | Purpose |
|---|---|
| `/actuator/health` | Displays the overall health of the application, including custom health indicators for the email and student services. |
| `/actuator/info` | Shows build information, including version and git commit details. |
| `/actuator/metrics` | Lists available metrics that can be scraped and provides details on specific metrics. |
| `/actuator/prometheus` | Exposes application metrics in a format scrapeable by Prometheus. |

*Note: The exact set of exposed endpoints can be configured via `management.endpoints.web.exposure.include` in your `application.yml` or environment properties.*

---

## 🏗️ Build Metadata

Integration with the `spring-boot-maven-plugin` (`build-info` goal) and `git-commit-id-maven-plugin` ensures that detailed build and Git commit information is captured. This information is made available through the `/actuator/info` endpoint, providing valuable context for deployed applications.

---

## 🧰 Prerequisites

- **Java Development Kit (JDK):** Version 17 or higher.
- **Apache Maven:** Version 3.x (or use the included `./mvnw` Maven wrapper).
- **Database:** A PostgreSQL instance is required if you are running the application with profiles other than the default H2 setup (e.g., `prod`, `staging`).

---

## 📦 Installation

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/Sridhar0112/springboot-actuator.git
    cd springboot-actuator
    ```

2.  **Configure Environment Variables:**
    Create a `.env` file in the root of the project and add your database and mail server credentials:
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
    *Note: For local development, you can rely on the default H2 database configuration by not providing database credentials in the `.env` file.* 

---

## ▶️ Usage

This application serves as a robust backend for managing student data, with a strong emphasis on production monitoring and observability.

### Running the Application

Use the Maven wrapper to run the application:

```bash
./mvnw spring-boot:run
```

To run with a specific profile (e.g., `dev`, `qa`, `staging`, `prod`), use the following command:

```bash
./mvnw spring-boot:run -Dspring.profiles.active=<profile-name>
```

Replace `<profile-name>` with the desired profile (e.g., `dev`, `qa`, `staging`, `prod`).

### Example Use Cases

- **Student Information System:** A core backend for managing student records in educational institutions.
- **Observability Demonstration:** A practical example of integrating Spring Boot Actuator with Prometheus for monitoring application health and performance.
- **Microservice Backend:** Can serve as a microservice for managing student-related data within a larger distributed system.

---

## 🏗️ Project Structure

```
springboot-actuator/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── logs/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── .env  (create this file for configuration)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/sridhar/springboot/
│   │   │       ├── Config/
│   │   │       ├── Controller/
│   │   │       ├── Dto/
│   │   │       ├── Exception/
│   │   │       ├── health/
│   │   │       │   └── service/
│   │   │       ├── logging/
│   │   │       │   ├── aspect/
│   │   │       │   ├── config/
│   │   │       │   ├── constants/
│   │   │       │   ├── filter/
│   │   │       │   ├── interceptor/
│   │   │       │   ├── mdc/
│   │   │       │   └── util/
│   │   │       ├── models/
│   │   │       ├── Service/
│   │   │       │   └── notification/
│   │   │       └── SpringbootApplication.java
│   │   ├── resources/
│   │   │   ├── application-dev.yml
│   │   │   ├── application-prod.yml
│   │   │   ├── application-qa.yml
│   │   │   ├── application-staging.yml
│   │   │   ├── application-uat.yml
│   │   │   ├── application.yml
│   │   │   ├── db/
│   │   │   │   └── migration/
│   │   │   │       └── V1__create_student_table.sql
│   │   │   └── logback-spring.xml
│   │   └── webapp/
│   └── test/
│       └── java/com/sridhar/springboot/
│           └── SpringbootApplicationTests.java
└── README.md
```

---

## 🌐 API Reference

This section details the available API endpoints for interacting with the student management system.

Base URL: `http://localhost:8080/api/v1/students`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/add` | Adds a new student to the system. |
| `GET` | `/getstudent` | Retrieves a list of all students. |
| `GET` | `/getstudent/{id}` | Retrieves a specific student by their ID. |
| `DELETE` | `/delete/{id}` | Deletes a student by their ID. |
| `PUT` | `/update/{id}` | Updates an existing student's information by their ID. |

### Request/Response Examples

**Add Student (POST `/add`)**

**Request Body:**
```json
{
  "name": "Jane Doe",
  "course": "Spring Boot",
  "email": "jane.doe@example.com",
  "phone": "9876543210"
}
```

**Success Response (201 Created):**
```json
{
  "message": "Student added successfully",
  "name": "Jane Doe"
}
```

**Error Response (400 Bad Request - Validation Error):**
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

**Get Student by ID (GET `/getstudent/{id}`)
**
**Path Parameter:** `id` (e.g., `1`)

**Success Response (200 OK):**
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

## 🤝 Contributing

Contributions are welcome! If you have suggestions for improving this project, please:

1.  **Fork the repository.**
2.  **Create a new branch** (`git checkout -b feature/your-feature-name`).
3.  **Make your changes** and commit them (`git commit -m 'Add some feature'`).
4.  **Push to the branch** (`git push origin feature/your-feature-name`).
5.  **Open a Pull Request.**

Please ensure your code adheres to the project's coding standards and includes appropriate tests.

---

## 📄 License

This project is not currently specified with a license. Please refer to the repository owner for licensing details.

---

## 👤 Author

**Sridhar** — [GitHub Profile](https://github.com/Sridhar0112)

<div align="center">

⭐ **Star this repo** if it helped you learn Spring Boot observability!

</div>
