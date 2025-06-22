# 🌤️ Weather Reporter App

A full-stack weather reporting application built with **Spring Boot** and **Next.js**, designed with clean architecture, design patterns, and modern deployment practices using **Docker**, **GitHub Actions**, and **AWS EC2**.

---

## 📦 Table of Contents

- [🚀 Project Overview](#-project-overview)
- [🛠️ Tech Stack](#️-tech-stack)
- [📐 Design Principles & Patterns](#-design-principles--patterns)
- [🧩 Backend Architecture](#-backend-architecture)
- [⚛️ Frontend Architecture](#️-frontend-architecture)
- [🔐 Why a Backend?](#-why-a-backend)
- [⚙️ CI/CD Pipeline](#️-cicd-pipeline)
- [🚀 Deployment Strategy](#-deployment-strategy)
- [💡 Future Enhancements](#-future-enhancements)
- [📌 Summary](#-summary)

---

## 🚀 Project Overview

The Weather Reporter App allows users to search for a city and get real-time weather information including temperature, humidity, wind speed, and UV index. It is optimized for performance, security, and future extensibility.

---

## 🛠️ Tech Stack

| Layer         | Technology           | Purpose                                  |
|--------------|----------------------|------------------------------------------|
| **Frontend** | Next.js + React      | Fast rendering, SEO-friendly UI          |
|              | Tailwind CSS         | Utility-first styling                    |
| **Backend**  | Spring Boot          | RESTful API, scalable backend logic      |
| **Language** | Java 21, TypeScript  | Type safety and modern features          |
| **CI/CD**    | GitHub Actions       | Build and push Docker images             |
| **Hosting**  | Docker, AWS EC2      | Lightweight and cost-effective deployment|
| **Registry** | Docker Hub           | Centralized image storage                |

---

## 📐 Design Principles & Patterns

### ✅ SOLID Principles

#### 1. S – Single Responsibility Principle (SRP)

Each class or module has one and only one reason to change.

- `WeatherService` handles only business logic.
- `WeatherController` manages HTTP requests/responses.
- `ServiceResponse<T>` encapsulates response formatting.

**🔧 Quality Gained:** Easier to test, debug, and modify in isolation.

---

#### 2. O – Open/Closed Principle (OCP)

Modules are open for extension but closed for modification.

- Add new providers by creating a new `WeatherAdapter` implementation.

**🔧 Quality Gained:** System scales without changing existing code.

---

#### 3. L – Liskov Substitution Principle (LSP)

Subtypes must be substitutable for their base types.

- All providers implement `WeatherAdapter`, used generically in `WeatherService`.

**🔧 Quality Gained:** Polymorphic flexibility and safe extension.

---

#### 4. I – Interface Segregation Principle (ISP)

Clients shouldn't depend on interfaces they don’t use.

- Interfaces like `WeatherAdapter` are narrowly scoped.

**🔧 Quality Gained:** Reduced coupling and higher cohesion.

---

#### 5. D – Dependency Inversion Principle (DIP)

High-level modules shouldn't depend on low-level modules. Depend on abstractions.

- `WeatherService` relies on `WeatherAdapter` interface.
- Spring’s constructor injection via `@RequiredArgsConstructor`.

**🔧 Quality Gained:** Better testability and loose coupling.

---

### 🎯 Design Patterns

| Pattern         | Where It's Used                                                                 |
|-----------------|----------------------------------------------------------------------------------|
| **Strategy**     | Switch between weather providers using `WeatherAdapter`                        |
| **Adapter**      | Normalize different external API formats into internal `WeatherResponse` model |
| **Builder**      | Create `ServiceResponse<T>` in a readable, flexible way                        |
| **Factory** (future) | Centralize provider creation logic (e.g., `WeatherAdapterFactory`)        |

---

### 💎 Quality Attributes Improved

| Quality Attribute | Principle/Pattern Applied                  | Result                                                          |
|-------------------|--------------------------------------------|-----------------------------------------------------------------|
| **Maintainability** | SRP, Strategy, Adapter                   | Easily change logic in isolation                                |
| **Extensibility**   | OCP, DIP, Strategy                       | Add new providers without altering existing code                |
| **Reusability**     | Adapter, ISP                             | Reuse components like `ServiceResponse` or `WeatherAdapter`     |
| **Testability**     | DIP, SRP, Interfaces                     | Easily mock services and providers in unit tests                |
| **Readability**     | Builder Pattern, modular code            | Clean, expressive, and understandable code                      |
| **Security**        | API encapsulation in backend             | Secrets (API keys) are hidden from frontend exposure            |
| **Scalability**     | Strategy, Factory                        | Ready to support more APIs, localization, and caching mechanisms|

---

## 🧩 Backend Architecture

- **Spring Boot + Java 21**
- **Layered Design:**  
  `Controller` → `Service` → `Provider` → `DTO`
- **WeatherAdapter Interface:**  
  Abstracts external weather APIs (e.g., WeatherAPI.com)
- **ServiceResponse<T>:**  
  Unified API response wrapper
- **Custom Exceptions:**  
  e.g., `CityNotFoundException` improves client-side clarity

---

## ⚛️ Frontend Architecture

- **Next.js + TypeScript + Tailwind CSS**
- **`fetchWeather()`** encapsulates all API logic
- **Loading and Error States:**  
  `LoadingPage`, message feedback, retry logic
- **Optimized Images:**  
  Weather icons via `next/image`
- **Responsive Design:**  
  Mobile-first, modern glassmorphism UI

---

## 🔐 Why a Backend?

| Benefit           | Why It Matters                                                         |
|-------------------|------------------------------------------------------------------------|
| 🔒 **Security**     | API keys are hidden from the public frontend                          |
| ⚙️ **Abstraction**   | Unified model across multiple weather APIs                             |
| 🚀 **Performance**  | Enables caching, response compression, and rate limiting              |
| 🧪 **Testability**  | Centralized logic is easier to test                                   |
| 📊 **Analytics**    | Log and analyze usage, errors, and performance                        |

---

## ⚙️ CI/CD Pipeline

The Weather Reporter App uses **GitHub Actions** to implement a fully automated **CI/CD pipeline** that builds and pushes Docker images for both the frontend and backend services.

### 🔁 Trigger

- The pipeline is automatically triggered on every **push to the `main` branch**.

### 🔧 Steps Performed

1. **Code Checkout**  
   The workflow checks out the latest code from the repository.

2. **Docker Authentication**  
   It logs into **Docker Hub** using credentials stored securely in GitHub Secrets.

3. **Backend Build & Push**  
   - Builds the **Spring Boot backend** as a Docker image.
   - Tags the image and pushes it to Docker Hub under the `weather-reporter-backend` repository.

4. **Frontend Build & Push**  
   - Builds the **Next.js frontend** as a Docker image, injecting the backend API URL via build arguments.
   - Tags the image and pushes it to Docker Hub under the `weather-reporter-frontend` repository.

### 🔐 Security

- Sensitive data like **Docker credentials** are securely managed using **GitHub Secrets**, ensuring the pipeline does not expose any private information.

### 🚢 Outcome

- Both frontend and backend Docker images are always **up-to-date in Docker Hub**, ready to be pulled and deployed on the EC2 server.
- This allows for a **smooth and fast deployment** process, with minimal manual steps required after pushing code.
