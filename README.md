# 🏪 Marketplace Microservices Platform

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Vite](https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

A full-stack marketplace application built with a **microservices architecture**. The backend is powered by Spring Boot and Spring Cloud (Eureka + API Gateway + JWT Auth), while the frontend is a **React + Vite** SPA. All services are orchestrated with Docker Compose.

---

## 📐 Architecture Overview

```
                    ┌────────────────────────────────────────────────┐
                    │                Docker Compose                   │
                    │                                                │
  Browser ──────►  │  ┌──────────────┐    ┌─────────────────┐      │
                    │  │  Frontend    │───►│   API Gateway   │      │
                    │  │ React + Vite │    └────────┬────────┘      │
                    │  └──────────────┘             │ routes        │
                    │                    ┌──────────▼───────┐       │
                    │                    │   Eureka Server  │       │
                    │                    └──────────┬───────┘       │
                    │           ┌──────────────────┬┴─────────────┐ │
                    │           ▼                  ▼              ▼ │
                    │      ┌─────────┐       ┌──────────┐  ┌──────┐ │
                    │      │  Auth   │       │  Users   │  │ Cart │ │
                    │      │ Service │       │ Service  │  │ Svc. │ │
                    │      └─────────┘       └──────────┘  └──────┘ │
                    │           ▼                  ▼                 │
                    │      ┌─────────┐       ┌──────────┐           │
                    │      │Products │       │  Orders  │           │
                    │      │ Service │       │ Service  │           │
                    │      └─────────┘       └──────────┘           │
                    └────────────────────────────────────────────────┘
```

---

## 🧩 Services

### 🖥️ Frontend — React + Vite
Modern SPA built with React and Vite. Communicates with the backend exclusively through the API Gateway.

### 🌐 API Gateway
Single entry point that routes all requests to the appropriate microservice and enforces JWT authentication on every request.

### 🔍 Eureka Server
Service registry enabling dynamic service discovery and load balancing across all microservices.

### 🔐 Auth Service
JWT-based authentication: login, token generation, and validation.

### 👤 User Service
User account management: registration, profile updates, and queries.

### 📦 Product Service
Product catalog management: creation, listing, and inventory tracking.

### 🛒 Cart Service
Shopping cart operations: add items, update quantities, and clear cart.

### 🧾 Order Service
Order processing from cart checkout through to fulfillment tracking.

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+
- Node.js 18+
- Docker & Docker Compose

### Run everything with Docker
```bash
docker-compose up --build
```

### Run frontend locally (development)
```bash
cd front
npm install
npm run dev
```

### Service URLs

| Service | URL |
|---|---|
| Frontend | http://localhost:5173 |
| API Gateway | http://localhost:8080 |
| Eureka Dashboard | http://localhost:8761 |
| Auth Service | http://localhost:8081 |
| User Service | http://localhost:8082 |
| Product Service | http://localhost:8083 |
| Cart Service | http://localhost:8084 |
| Order Service | http://localhost:8085 |

---

## 🔐 Authentication Flow

```
Client ──► API Gateway ──► Auth Service ──► JWT Token issued
               │
               └──► All subsequent requests validated via JWT
                    before routing to any protected service
```

All protected endpoints require a valid JWT in the `Authorization: Bearer <token>` header.

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React · Vite |
| Backend Language | Java |
| Framework | Spring Boot |
| Service Discovery | Spring Cloud Netflix Eureka |
| API Routing | Spring Cloud Gateway |
| Authentication | JWT (JSON Web Tokens) |
| Containerization | Docker · Docker Compose |
| Build Tool | Maven |

---

## 👨‍💻 Author

**Antony Chávez** — Backend Developer
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/antonychavez)
[![GitHub](https://img.shields.io/badge/GitHub-Xavez05-181717?style=flat&logo=github&logoColor=white)](https://github.com/Xavez05)
