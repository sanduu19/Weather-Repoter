# 🧗 Challenges Faced & How I Overcame Them

This document outlines the key challenges encountered during the development of the **Weather Reporter App** and the strategies used to overcome them. It reflects the technical and architectural decisions made to ensure scalability, security, and maintainability.

---

## 🔐 1. Protecting API Keys from the Frontend

**Challenge:**  
Calling weather APIs directly from the frontend would expose sensitive API keys, leaving them vulnerable to abuse or theft.

**Solution:**  
A secure **Spring Boot backend** was created to act as a proxy layer:
- API keys are securely stored server-side in environment variables.
- All external API calls are routed through the backend.
- Used **Strategy** and **Adapter** patterns to abstract provider logic.

---

## 🧱 2. Designing a Scalable & Extensible Architecture

**Challenge:**  
The application needed to support multiple weather APIs and be ready for features like analytics or caching without refactoring core logic.

**Solution:**  
- Applied **SOLID principles**.
- Implemented **Strategy Pattern** using the `WeatherAdapter` interface.
- Created **Builder Pattern** for structured API responses.
- Prepared for a **Factory Pattern** to handle provider selection.

---

## 🐞 3. Handling API Errors & User Feedback Gracefully

**Challenge:**  
Unpredictable API responses and failures needed to be handled gracefully without affecting user experience.

**Solution:**  
- Defined custom exceptions like `CityNotFoundException`.
- Centralized exception handling using `@ControllerAdvice`.
- In the frontend, built a robust `fetchWeather()` with loading/error state components like `LoadingPage`.

---

## 🐳 4. Dockerizing Both Frontend and Backend Effectively

**Challenge:**  
Running both the backend and frontend inside containers on the same EC2 instance required seamless coordination.

**Solution:**  
- Wrote separate Dockerfiles for backend and frontend.
- Used `--build-arg` to inject API base URL into frontend build.
- Managed runtime variables using `.env.prod` and Docker run commands.

---

## 🔁 5. Automating CI/CD with GitHub Actions

**Challenge:**  
Manual Docker builds and deployment were slow and error-prone.

**Solution:**  
- Created a **GitHub Actions** workflow to build and push Docker images on every push to `main`.
- Used **GitHub Secrets** for secure Docker authentication.
- Automated both backend and frontend image deployment to Docker Hub.

---

## 🌐 6. Managing Environment-Specific Variables

**Challenge:**  
The frontend needed different API base URLs in development and production environments.

**Solution:**  
- Passed backend URLs into the frontend Docker build via `--build-arg`.
- Avoided hardcoded values; used `.env.prod` for flexibility.

---

## 🧪 7. Debugging a Split Stack Setup

**Challenge:**  
Debugging issues across two separate technologies (Spring Boot + Next.js) was more complex.

**Solution:**  
- Standardized API responses using `ServiceResponse<T>`.
- Simplified error handling in frontend through clean abstraction.
- Used descriptive logs and exception messages.

---

## ✅ Lessons Learned

- **Clean architecture saves time**: A good design upfront reduces bugs later.
- **Automation boosts productivity**: CI/CD lets you ship fast and reliably.
- **Abstraction empowers growth**: Interfaces and design patterns helped adapt and extend the system effortlessly.

This project has not only been a technical success but also a reflection of good engineering discipline, adaptability, and attention to scalable design.
