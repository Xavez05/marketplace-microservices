# 🏪 Marketplace Microservices Platform

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![React](https://img.shields.io/badge/React_18-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Vite](https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

A full-stack marketplace application built with a **microservices architecture**. The backend uses Spring Boot and Spring Cloud (Eureka + API Gateway + JWT Auth), while the frontend is a **React 18 + Vite + Tailwind CSS** SPA with protected routes and JWT session management. All services are orchestrated with Docker Compose.

---

## 📐 Architecture Overview

```
                    ┌────────────────────────────────────────────────┐
                    │                Docker Compose                   │
                    │                                                │
  Browser ──────►  │  ┌──────────────────────┐                     │
                    │  │  Frontend (React SPA) │                     │
                    │  │  React 18 · Vite      │                     │
                    │  │  Tailwind · Axios      │                     │
                    │  └──────────┬───────────┘                     │
                    │             │ JWT Bearer token                 │
                    │  ┌──────────▼───────────┐                     │
                    │  │     API Gateway       │ :8080               │
                    │  │  Routes + JWT guard   │                     │
                    │  └──────────┬───────────┘                     │
                    │  ┌──────────▼───────────┐                     │
                    │  │    Eureka Server      │ :8761               │
                    │  │  Service Discovery    │                     │
                    │  └──┬──┬──┬──┬──┬───────┘                     │
                    │     ▼  ▼  ▼  ▼  ▼                             │
                    │  Auth User Product Cart Order                  │
                    │  Svc  Svc Svc     Svc  Svc                    │
                    └────────────────────────────────────────────────┘
```

---

## 🖥️ Frontend — React 18 + Vite + Tailwind CSS

### Pages & Routing

| Route | Page | Access |
|---|---|---|
| `/` | Login | Public |
| `/register` | Register | Public |
| `/app` | Home (Dashboard) | Protected |
| `/app/products` | Product listing | Protected |
| `/app/cart` | Shopping cart | Protected |
| `/app/orders` | Order history | Protected |
| `/app/orders/:id` | Order detail | Protected |
| `/app/confirm` | Order confirmation | Protected |
| `/app/profile` | User profile | Protected |

### Key Features

- **JWT Authentication** — Token stored in localStorage, automatically injected on every request via Axios interceptor
- **Protected Routes** — `ProtectedRoute` component wraps all private pages, redirecting unauthenticated users
- **Shared Layout** — `DashboardLayout` provides consistent navigation across all authenticated pages
- **JWT Decode** — Client-side token parsing for session management without extra API calls

### Tech Stack

- React 18 · React Router DOM v7
- Vite 4 · Tailwind CSS 3
- Axios (with JWT interceptor) · jwt-decode

### Run locally (development)

```bash
cd front
npm install
npm run dev
```

### Build for production

```bash
npm run build
```

---

## 🧩 Backend Services

### 🌐 API Gateway — `localhost:8080`
Single entry point that routes all client requests and validates JWT tokens before forwarding to microservices.

### 🔍 Eureka Server — `localhost:8761`
Service registry enabling dynamic service discovery across all microservices.

### 🔐 Auth Service
JWT-based authentication: login, token generation, and validation.

### 👤 User Service
User account management: registration, profile updates, and queries.

### 📦 Product Service
Product catalog management: listing, creation, and inventory.

### 🛒 Cart Service
Shopping cart operations: add items, update quantities, and clear cart.

### 🧾 Order Service
Order processing from cart checkout through fulfillment tracking.

---

## 🔐 Authentication Flow

```
1. User logs in  ──► Auth Service ──► JWT token issued
2. Token stored in localStorage
3. Axios interceptor injects token on every request:
   Authorization: Bearer <token>
4. API Gateway validates token before routing to any service
5. ProtectedRoute checks token on the frontend before rendering pages
```

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

### Service URLs

| Service | URL |
|---|---|
| Frontend (dev) | http://localhost:5173 |
| API Gateway | http://localhost:8080 |
| Eureka Dashboard | http://localhost:8761 |
| Auth Service | http://localhost:8081 |
| User Service | http://localhost:8082 |
| Product Service | http://localhost:8083 |
| Cart Service | http://localhost:8084 |
| Order Service | http://localhost:8085 |

---

## 🛠️ Full Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React 18 · Vite · Tailwind CSS · React Router DOM v7 |
| HTTP Client | Axios + JWT interceptor |
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
