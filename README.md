# 🏥 MedNex Enterprise HMS

## 📌 Project Description

MedNex Enterprise HMS is a full-stack Hospital Management System developed using Spring Boot, React.js, JWT Authentication, and MySQL.

The system helps manage:
- Patients
- Doctors
- Appointments
- Authentication & Authorization
- Dashboard Statistics
- Email OTP Verification

The application is designed using secure REST APIs with role-based access control for different users such as Admin, Doctor, and Nurse.

---

# 🚀 Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- MySQL
- Spring Mail Sender

## Frontend
- React.js
- Axios
- React Router
- CSS

---

# ✨ Features

- Secure Login Authentication
- JWT-Based Authentication
- Email OTP Verification
- Role-Based Access Control
- Patient Management
- Doctor Management
- Appointment Scheduling
- Dashboard Analytics
- REST APIs
- MySQL Database Integration

---

# 📂 Project Structure

```bash
MedNex/
│
├── backend/
│
├── frontend/
│
├── screenshots/
│
└── README.md
```

---

# 🔐 Authentication

JWT-based authentication is implemented using Spring Security.

### User Roles
- ADMIN
- DOCTOR
- NURSE

---

# 📧 OTP Verification

Email OTP verification is implemented using Spring Boot Mail Sender and Gmail SMTP integration.

### OTP Flow
1. User enters email
2. Backend generates OTP
3. OTP is sent to registered email
4. User verifies OTP using API

---

# 🗄️ Database

Database Used:
- MySQL

### Main Tables
- Users
- Doctors
- Patients
- Appointments

---

# 📊 ER Diagram

(Add ER Diagram Image Here)

```md
![ER Diagram](ER-Diagram.png)
```

---

# ⚙️ Installation & Setup

## Backend Setup

```bash
cd backend
mvn spring-boot:run
```

## Frontend Setup

```bash
cd frontend
npm install
npm start
```

---

# 📌 Future Improvements

- Docker Deployment
- Email Notifications
- Advanced Reports
- Mobile Responsive UI

---

# 👨‍💻 Author

Maaraj
