# SmartBank - Online Banking System

A professional full-stack Online Banking System built with Advanced Java, Spring Boot, Spring Security, JWT, Hibernate/JPA, MySQL and Angular.

## Features
- Customer registration and login
- JWT authentication and BCrypt password security
- Role-based access: CUSTOMER and ADMIN
- Auto-generated account number
- View balance and account details
- Fund transfer between SmartBank accounts
- Transaction history
- Loan application and loan status tracking
- Admin dashboard
- Admin loan approval/rejection
- Account freeze API

## Tech Stack
### Backend
- Java 17
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Swagger/OpenAPI

### Frontend
- Angular 17
- Standalone components
- Angular Router
- HTTP Interceptor
- Route Guards
- Forms
- Responsive CSS

## Default Admin Login
```text
Email: admin@smartbank.com
Password: admin123
```

## Backend Setup
1. Create MySQL database manually or let Spring create it:
```sql
CREATE DATABASE smartbank;
```

2. Update database username/password in:
```text
backend/src/main/resources/application.properties
```

3. Run backend:
```bash
cd backend
mvn spring-boot:run
```

Backend runs at:
```text
http://localhost:8080
```

Swagger:
```text
http://localhost:8080/swagger-ui.html
```

## Frontend Setup
```bash
cd frontend
npm install
npm start
```

Frontend runs at:
```text
http://localhost:4200
```

## Important APIs
### Auth
- POST `/auth/register`
- POST `/auth/login`

### Customer
- GET `/account`
- POST `/account/transfer`
- GET `/account/transactions`
- POST `/loans`
- GET `/loans`

### Admin
- GET `/admin/dashboard`
- GET `/admin/users`
- GET `/admin/accounts`
- GET `/admin/loans`
- PUT `/admin/loans/{id}/{status}`
