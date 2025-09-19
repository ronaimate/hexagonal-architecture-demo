# Hexagonal Architecture in Kotlin

This project demonstrates a Kotlin-based implementation of **Hexagonal Architecture**, also known as **Ports and Adapters**. The goal is not to build a production-ready application, but rather to explore and showcase the architectural principles in a way that reflects my personal understanding.

## 🧠 Motivation

There are many interpretations of hexagonal architecture out there — and frankly, it often feels like everyone understands it slightly differently. For me, the essence of hexagonal architecture is:

- Clear separation of concerns
- A clean domain model
- Business logic that is completely independent of frameworks and infrastructure

## 🧱 Architectural Choice

While [Mark Seemann’s article on Ports and Fat Adapters](https://blog.ploeh.dk/2023/03/06/ports-and-fat-adapters/) argues for placing use-case logic directly inside adapters (a.k.a. “fat adapters”), I’ve chosen a more traditional approach. In this project, **use-cases are extracted into dedicated classes**, which makes them easier to test and reuse across multiple adapters (e.g., REST, Kafka, Scheduler).

This decision was driven by a desire for **better testability and clearer separation** between application logic and delivery mechanisms.

## 📦 Project Structure

The project is organized into layers that reflect the hexagonal model:

```
src/
├── main/
│ └── kotlin/
│ ├── domain/ # Business model and rules
│ │ ├── model/ # Domain entities (e.g., User)
│ │ ├── businesslogic/ # Use-cases (e.g., SaveUserUseCase)
│ │ └── port/out/ # Output ports (e.g., UserRepositoryPort)
│ ├── infra/ # Infrastructure and adapters
│ │ ├── adapter/ # Port implementations (e.g., JPA adapter)
│ │ └── jpa/ # Spring Boot entry point, config
│ └── resources/ # Configuration files
└── test/
└── kotlin/ # Unit and integration tests
```



## 🧪 Testing Philosophy

All business logic is testable in isolation. No mocking frameworks are required — in-memory adapters are used for testing use-cases. This reinforces the idea that the domain should be **pure and framework-free**.

## 🚀 Technologies

- Kotlin
- Spring Boot
- JPA (PostgreSQL)
- JUnit 5

## 📚 References

- [Ports and Fat Adapters by Mark Seemann](https://blog.ploeh.dk/2023/03/06/ports-and-fat-adapters/)
- [Hexagonal Architecture – Alistair Cockburn](https://alistair.cockburn.us/hexagonal-architecture/)

---

Feel free to explore, critique, or extend this project. It’s meant to be a learning tool — not a dogma.
