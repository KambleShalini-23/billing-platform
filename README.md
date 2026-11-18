# Multi-Tenant Billing & Invoicing Platform

A SaaS billing platform that lets many businesses (tenants) manage plans, subscriptions
and invoices for their own customers, with Stripe for payment processing.

**Stack:** Java 21 · Spring Boot · PostgreSQL · Flyway · React + Vite + TypeScript · Docker · Stripe · AWS

## How it works
- **Tenant** = a business using the platform (e.g. a gym). **Customer** = that business's own client.
- Multi-tenancy model: shared tables with a `tenant_id` on every row.
- The platform owns the billing logic (plans, subscriptions, invoices); Stripe processes the payments.

## Project structure
```
backend/                         Spring Boot REST API
  src/main/java/.../billing/
    tenant/                      Tenant entity, repository
  src/main/resources/
    application.yaml             Config (env vars with local defaults)
    db/migration/                Flyway migrations (V1__, V2__, ...)
frontend/                        React single-page app (Vite)
docs/                            Epics and design notes
docker-compose.yml               Local PostgreSQL
```

## Prerequisites
- Java 21
- Node.js 20.19+ or 22.12+
- Docker Desktop

## Run locally
```bash
# 1. Database
docker compose up -d

# 2. Backend (http://localhost:8080) — Flyway migrations run automatically on startup
cd backend
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run

# 3. Frontend (http://localhost:5173)
cd frontend
npm install
npm run dev
```
Health check: http://localhost:8080/actuator/health

## Tests
PostgreSQL must be running (`docker compose up -d`):
```bash
cd backend
./mvnw test
```

## Database
- Schema changes are Flyway migrations in `backend/src/main/resources/db/migration`.
- Never edit a migration that has already been applied; add a new `V<n>__description.sql`.
- Reset your local database (deletes all local data):
  ```bash
  docker compose down -v && docker compose up -d
  ```

## Roadmap
- [ ] **Epic 1: Foundation & multi-tenancy**
    - [x] 1.1 Project setup & dev environment
    - [x] 1.2 Flyway migration + Tenant entity
    - [ ] 1.3 Tenant signup API + signup page
    - [ ] 1.4 Tenant context, API keys, isolation
    - [ ] 1.5 Error handling, validation, API docs, Testcontainers
- [ ] Epic 2: Authentication & users
- [ ] Epic 3: Plans & pricing
- [ ] Epic 4: Customers & subscriptions (Stripe)
- [ ] Epic 5: Invoicing
- [ ] Epic 6: Payments & webhooks
- [ ] Epic 7: Dashboard & reporting
- [ ] Epic 8: Docker & AWS deployment
- [ ] Epic 9: Hardening