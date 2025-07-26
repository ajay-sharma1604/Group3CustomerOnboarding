# KYC Microservice 🛡️

Handles customer KYC document submission, admin verification, and status tracking as part of the **Customer Onboarding Backend Workflow**.

---

## 🚀 Features

- 📤 **KYC Submission**: Upload PAN, Aadhaar, Photograph (Base64)
- ✅ **Validation**: Field-level validation using Jakarta Validator
- 🔐 **Masking**: Aadhaar & PAN returned in masked format
- 🛂 **Admin Review**: Verify or Reject KYC with remarks
- ✉️ **Email Integration**: Sends rejection mail to customer
- 🖼️ **Photo View**: Admin can view uploaded photo
- 🔍 **Status API**: Customer can check KYC verification status
- 📑 **Swagger UI**: Interactive API testing

---

## 📦 Technologies Used

| Tech              | Version    |
|------------------|------------|
| Spring Boot      | 3.3.1      |
| Spring Cloud     | 2023.0.1   |
| Spring Data JPA  | ✅          |
| Java Mail Sender | ✅          |
| Oracle DB        | ✅          |
| Swagger (springdoc-openapi) | 2.3.0 |
| Java             | 21         |
| Feign Client     | ✅ (For Customer info) |

---

## 🗃️ Endpoints

### 🧾 Customer APIs

| Method | Endpoint                         | Description                        |
|--------|----------------------------------|------------------------------------|
| POST   | `/kycservice/api/kyc`            | Submit KYC                         |
| GET    | `/kycservice/api/kyc/{id}`       | Get masked KYC                     |
| GET    | `/kycservice/api/kyc`            | Get all KYC records                |
| PUT    | `/kycservice/api/kyc/{id}`       | Update KYC                         |
| DELETE | `/kycservice/api/kyc/{id}`       | Delete KYC                         |

### 🛂 Admin APIs

| Method | Endpoint                                      | Description                     |
|--------|-----------------------------------------------|---------------------------------|
| PUT    | `/kycservice/api/kyc/{id}/review`             | Verify or Reject KYC            |
| GET    | `/kycservice/api/kyc/{id}/photo/view`         | View photo (Base64 to Image)    |

### 🧑 Customer Status API

| Method | Endpoint                            | Description               |
|--------|-------------------------------------|---------------------------|
| GET    | `/kycservice/api/kyc/{id}/status`   | Get KYC status + remark   |

---

## 📷 Photograph Upload (Base64)

Upload `photograph` field as base64-encoded string in request body.

Example:
```json
"photograph": "data:image/jpeg;base64,/9j/4AAQSkZJRgABA..."
