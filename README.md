# Learniverse Connect

A full-stack web application for managing and enrolling in courses, built with React (frontend) and Spring Boot (backend).

##  Features

###  Frontend
- Browse, search and view courses with detailed modal views.
- Add to favorites and cart.
- Checkout with selected provider.
- Admin can view course IDs and hidden courses.

### ️ Backend
- REST API with Spring Boot & JPA.
- Multi-provider support with pricing.
- Role-based user system.
- MySQL storage.

##  Technologies

| Layer      | Stack                                   |
|------------|------------------------------------------|
| Frontend   | React, React Router, Context API         |
| Backend    | Spring Boot, Spring Data JPA             |
| Database   | MySQL                                    |
| Deployment | GitHub Pages (Frontend), Linux (Backend) |
| Build      | Maven, npm                               |

---

##  Getting Started

###  Backend

Clone and build:
```bash
git clone https://github.com/your-org/backend-repo.git
cd backend-repo
./mvnw clean package
```
Run:
```bash
java -jar target/lib-website-monolith-1.0.0.jar \
  --spring.datasource.url=jdbc:mysql://localhost:3306/courses \
  --spring.datasource.username=admin \
  --spring.datasource.password=admin \
  --spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

Run in background:
```bash
nohup java -jar target/lib-website-monolith-1.0.0.jar \
  --spring.datasource.url=jdbc:mysql://localhost:3306/courses \
  --spring.datasource.username=admin \
  --spring.datasource.password=admin \
  --spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect > app.log 2>&1 &
  ```

Verify:
```bash
curl http://localhost:8082/api/courses/getAll
```

### Frontend

Clone and setup:

```bash
git clone https://github.com/your-org/frontend-repo.git
cd frontend-repo
npm install
```

Update API URL in code:
```js
fetch("http://<PUBLIC_SERVER_IP>:8082/api/courses/getAll")
```

Run locally:
```bash
npm start
```

Deploy on GitHub pages:
1. add to package.json:
```json
"homepage": "https://yourusername.github.io/frontend-repo",
"scripts": {
  "predeploy": "npm run build",
  "deploy": "gh-pages -d build"
}
```

2. Deploy:
```bash
npm run deploy
```
-----
## Roles

| Role  | Abilities                     |
|-------|-------------------------------|
| Admin | Hide/unhide courses, view IDs |
| User  | Enroll, checkout, favorite    |
| Guest | Browse visible courses        |

--------
## Authors
###	Marius Bringsvor Rusten
### Sivert Sulebakk

-------