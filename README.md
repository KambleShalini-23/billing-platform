# Multi-Tenant Billing & Invoicing Platform

SaaS billing platform: subscriptions, invoices and Stripe payments for multiple tenants.

**Stack:** Java 21 · Spring Boot · PostgreSQL · React + Vite · Docker · Stripe · AWS

## Project structure
```
backend/    Spring Boot REST API
frontend/   React single-page app
docs/       Epics and design notes
```

## Prerequisites
- Java 21
- Node.js 20.19+ or 22.12+
- Docker Desktop

## Run locally
```bash
# 1. Database
docker compose up -d

# 2. Backend (http://localhost:8080)
cd backend
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run

# 3. Frontend (http://localhost:5173)
cd frontend
npm install
npm run dev
```

Health check: http://localhost:8080/actuator/health

## Roadmap
- [ ] Epic 1: Foundation & multi-tenancy (in progress)
- [ ] Epic 2: Authentication & users
- [ ] Epic 3: Plans & pricing
- [ ] Epic 4: Customers & subscriptions (Stripe)
- [ ] Epic 5: Invoicing
- [ ] Epic 6: Payments & webhooks
- [ ] Epic 7: Dashboard & reporting
- [ ] Epic 8: Docker & AWS deployment
- [ ] Epic 9: Hardening