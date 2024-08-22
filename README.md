# user-login-service
 A Spring Boot service for user authentication, including login, sign-up, and password management functionalities.

## Features

- **User Login API**: Authenticate users via HTTP authentication.
- **Forget Password API**: Allow users to reset their passwords.
- **Sign-Up API**: Register new users in the system.
- **Self Sign-Up API**: Enable users to sign up themselves without admin intervention.
- **Security**: Secured using Spring Security with BCrypt password encoding.
- **Messaging**: Integrated with Kafka for event-driven architecture (pending).
- **Database**: Utilizes Hibernate for ORM with MySQL/MongoDB support.
- **API Documentation**: Integrated with Swagger for API exploration and testing (pending).
- **Containerization**: Docker support (optional).

## Getting Started

### Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **MySQL/MongoDB**
- **Kafka** (For event-driven architecture)
- **Docker** (Optional)

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/CodeNinja0101/user-login-service.git
   cd user-login-service
