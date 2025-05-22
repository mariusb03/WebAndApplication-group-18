# Learniverse Connect

A full-stack web application for managing and enrolling in courses, built with React (frontend) and Spring Boot (backend).

##  Table of Contents

- [Prerequisites](#prerequisites)
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
    - [Backend](#backend)
    - [Frontend](#frontend)
- [Roles](#roles)
- [Environment Variables](#environment-variables)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **Backend**:
    - Java 17 (or higher, recommend LTS version)
    - Maven 3.6.0 or higher
    - MySQL 8.0 or higher
    - A MySQL client (e.g., MySQL Workbench or DBeaver)

- **Frontend**:
    - Node.js 16.x or higher
    - npm 8.x or higher
    - Git

- **Optional**:
    - IntelliJ IDEA or another IDE for backend development
    - Visual Studio Code or another editor for frontend development
    - Docker (if you plan to containerize the application)

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

- `REACT_APP_API_URL`: Base URL for the backend API.

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

-----

## Environment Variables

### Backend
- `SERVER_PORT`: Port for the backend server (default: 8082).

------

## API Endpoints

### Courses
- `PUT /api/courses/{courseId}/toggleVisibility`: Toggle course visibility.
- `PUT /api/courses/Update/{id}`: Update a course.
- `POST /api/courses/{courseId}/user/{userId}`: Add a user to a course.
- `POST /api/courses/{courseId}/topics/{topicId}`: Add a topic to a course.
- `POST /api/courses/{courseId}/provider/{providerId}`: Add a provider to a course.
- `POST /api/courses/add`: Add a new course.
- `GET /api/courses/user/{userId}`: Get all courses of a user.
- `GET /api/courses/topic/{topicId}`: Get all courses of a topic.
- `GET /api/courses/provider/{providerId}`: Get all courses of a provider.
- `GET /api/courses/getUsersForCourse/{courseId}`: Get all users of a course.
- `GET /api/courses/getTopicsForCourse/{courseId}`: Get all topics of a course.
- `GET /api/courses/getProviderForCourse/{courseId}`: Get all providers of a course.
- `GET /api/courses/getPrice/{courseId}`: Get the price of a course.
- `GET /api/courses/getById/{id}`: Get course by ID.
- `GET /api/courses/getAll`: Get all courses.
- `DELETE /api/courses/delete/{id}`: Delete a course.

### User
- `GET /user/{userId}/favourites/`: Get user's favorite courses.
- `GET /user/getById/{userId}`: Get user by ID.
- `GET /user/getAll`: Get all users.
- `POST /user/{userId}/favourite/{courseId}`: Add a course to favorites.
- `POST /user/login`: User login.
- `POST /user/add`: Add a new user.
- `DELETE /user/{userId}/favourite/{courseId}`: Remove a course from favorites.
- `DELETE /user/delete/{userId}`: Delete a user.
- `PUT /user/update/{userId}`: Update user details.

### Topics
- `PUT /api/topics/update/{id}`: Update a topic.
- `POST /api/topics/add`: Add a new topic.
- `GET /api/topics/getById/{id}`: Get a topic by ID.
- `GET /api/topics/getAll`: Get all topics.
- `DELETE /api/topics/delete/{id}`: Delete a topic.

### Providers
- `PUT /api/providers/update/{id}`: Update a course provider.
- `POST /api/providers/add`: Add a new course provider.
- `GET /api/providers/getById/{id}`: Get course provider by ID.
- `GET /api/providers/getAll`: Get all course providers.
- `DELETE /api/providers/delete/{id}`: Delete a course provider.

--------
## Authors
###	Marius Bringsvor Rusten
### Sivert Sulebakk

-------