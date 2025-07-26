# 🏦 Customer Service Microservice (Spring Boot + OracleDB)

This repository contains the **Customer Service** microservice, which is part of the larger **Customer Onboarding Backend Workflow** system. This microservice handles **customer registration** functionalities and integrates with OracleDB and Eureka for service discovery.

---

## 🚀 Key Features

- ✅ Register and manage customers via RESTful APIs
- ✅ Spring Boot 3.5.3 and Java 21
- ✅ Integrated with OracleDB using Spring Data JPA
- ✅ Field-level validation (email, phone, PAN, Aadhaar, etc.)
- ✅ Swagger UI for live API documentation
- ✅ Eureka client registration for service discovery
- ✅ Postman collection included for API testing

---

## 📁 Project Structure

```
customer-service-data-jpa/
├── src/
│   ├── main/
│   │   ├── java/com/oracle/customer/
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── schema.sql (optional)
├── pom.xml
├── README.md
└── customer-service-swagger.json
```

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3.5.3
- Spring Cloud 2025.0.0
- Oracle JDBC (ojdbc11)
- Swagger with Springdoc OpenAPI 2.3.0
- Eureka Discovery Client
- Maven

---

## 🧪 How to Run

1. ✅ Configure your OracleDB credentials in `application.properties`
2. ✅ Make sure Eureka Server is running
3. ✅ Run the application as `Spring Boot App`

---

## 🔗 API Documentation (Swagger)

Start the application and open the browser at:

```
http://localhost:8081/swagger-ui/index.html
```

You’ll see the live API documentation and can test each endpoint directly.

---

## 📫 Postman Collection

Use the file `customer-service-swagger.json` to import all endpoints into Postman:

1. Open Postman
2. Click **Import** > **File** > select `customer-service-swagger.json`
3. Explore and test APIs

---

## 🧬 ER Diagram

> (Include ER diagram here or mention file path)

- **Customer Table**
  - `customerId` (Primary Key)
  - `fullName`, `email`, `phone`, `dob`, `address`, `pan`, `aadhaar`

---

## 💡 Future Scope

- Add JWT-based authentication
- Add logging with ELK stack
- Integrate with Kafka for async communication with Account & KYC services

---

## 👨‍💻 Developed By

**Ajay Sharma**  
📫 *Email: [your-email@example.com]*  
📍 India

---

> This microservice is built as part of the "Project Innovation through Code Contest and Presentation" and is aligned with modern microservices practices.