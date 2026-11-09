# Spring Boot REST API (`new-java-app`)

A Spring Boot RESTful application running on **Java 21** with **Gradle 9**, configured to serve on port **3000** with hot reload / auto-restart support via **Spring Boot DevTools**.

---

## 📋 Prerequisites

- **Java JDK 21+**
  Check your Java version:
  ```bash
  java -version
  ```
- No separate Gradle installation is required; the included Gradle Wrapper (`./gradlew`) is used.

---

## ⚙️ Configuration

Application settings are located in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
spring.application.name=new-java-app
server.port=3000
```

- **Base URL:** `http://localhost:3000`

---

## 🚀 How to Run

### Option A: Using `npm` Shortcuts (Fastest & Simplest)

If you prefer the familiar Node.js / npm experience, a [package.json](package.json) wrapper is configured:

| Command | What it does |
| :--- | :--- |
| **`npm run dev`** | **Starts watch mode + app with auto-reload (Single command!)** |
| **`npm start`** | Runs the app directly (`./gradlew bootRun`) |
| **`npm run build`** | Builds the executable JAR (`./gradlew build`) |
| **`npm test`** | Runs all tests (`./gradlew test`) |
| **`npm run clean`** | Cleans build artifacts (`./gradlew clean`) |

---

### Option B: Using Gradle Directly

```bash
./gradlew bootRun
```

*(If you ever see daemon lock warnings on your system, you can append `--no-daemon`: `./gradlew --no-daemon bootRun`)*

---

### Method 2: Packaged JAR (Production Style)

1. Build the executable JAR:
   ```bash
   ./gradlew build
   ```
2. Run the JAR:
   ```bash
   java -jar build/libs/app.jar
   ```

To stop the server, press `Ctrl + C`.

---

## 🔄 Auto-Reload / Hot-Reload on Code Changes

In Java/Spring Boot, the equivalent of `nodemon` or `nest start --watch` is powered by **Spring Boot DevTools** (`spring-boot-devtools` in `build.gradle`).

Whenever your `.java` files are recompiled, DevTools detects the change and automatically restarts the application within milliseconds without having to manually stop and start the server.

### CLI Workflow (Continuous Watch Mode)

Open **two terminal windows**:

1. **Terminal 1 — Continuous Compiler (Watcher):**
   ```bash
   ./gradlew -t classes
   ```
   > The `-t` (or `--continuous`) flag tells Gradle to watch the file system. Any time you save a `.java` file, Gradle automatically recompiles the classes.

2. **Terminal 2 — Application Runner:**
   ```bash
   ./gradlew bootRun
   ```

Now, every time you edit and save any file, Terminal 1 will compile it, and Terminal 2 will immediately trigger a fast live-restart!

### VS Code / IDE Workflow

If you run the application via VS Code or your IDE's Run/Debug button:
1. Enable **Auto Save** in VS Code (`File` > `Auto Save`).
2. VS Code automatically compiles saved files in the background, and DevTools will reload the server automatically.

---

## 📡 FAQ API Endpoints (MongoDB CRUD)

All endpoints are defined in [`src/main/java/com/example/new_java_app/controller/FaqController.java`](src/main/java/com/example/new_java_app/controller/FaqController.java).

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/faqs` | Create a new FAQ |
| **GET** | `/faqs` | List all FAQs (supports `?category=billing`) |
| **GET** | `/faqs/{id}` | Get FAQ by MongoDB ID |
| **PUT** | `/faqs/{id}` | Update an existing FAQ |
| **DELETE**| `/faqs/{id}` | Delete an FAQ by ID |

---

### API Examples & cURL

#### 1. Create an FAQ (`POST /faqs`)
```bash
curl -X POST http://localhost:3000/faqs \
     -H "Content-Type: application/json" \
     -d '{
       "question": "What is Spring Boot?",
       "answer": "Spring Boot is a framework for building production-ready Java apps.",
       "category": "general",
       "published": true
     }'
```

---

#### 2. Get All FAQs (`GET /faqs`)
```bash
curl http://localhost:3000/faqs
```
Filter by category:
```bash
curl "http://localhost:3000/faqs?category=general"
```

---

#### 3. Get FAQ by ID (`GET /faqs/{id}`)
```bash
curl http://localhost:3000/faqs/<MONGO_ID>
```

---

#### 4. Update FAQ (`PUT /faqs/{id}`)
```bash
curl -X PUT http://localhost:3000/faqs/<MONGO_ID> \
     -H "Content-Type: application/json" \
     -d '{
       "question": "What is Spring Boot 4?",
       "answer": "It provides modern cloud-native features and Java 21 support.",
       "published": true
     }'
```

---

#### 5. Delete FAQ (`DELETE /faqs/{id}`)
```bash
curl -X DELETE http://localhost:3000/faqs/<MONGO_ID>
```

---

## 📁 Project Directory Structure

### Current Layout (Feature-by-Feature / Modular Structure)
```text
new-java-app/
├── build.gradle                               # Dependencies (Web, MongoDB, DevTools)
├── settings.gradle                            # Project settings
├── package.json / dev.sh                      # npm shortcuts (npm run dev)
├── gradlew / gradlew.bat                      # Gradle wrapper
├── README.md                                  # Documentation
└── src/
    ├── main/
    │   ├── java/com/example/new_java_app/
    │   │   ├── NewJavaAppApplication.java     # Main entry point (@SpringBootApplication)
    │   │   │
    │   │   ├── config/                        # Global Configurations
    │   │   │   └── OpenApiConfig.java         # Swagger / OpenAPI documentation config
    │   │   │
    │   │   └── services/                      # 📁 All Business Services
    │   │       ├── faq/                       # 📁 FAQ Feature
    │   │       │   ├── model/
    │   │       │   │   └── Faq.java           # MongoDB Document Schema (@Document)
    │   │       │   ├── repo/
    │   │       │   │   └── FaqRepository.java # DB queries (MongoRepository)
    │   │       │   ├── service/
    │   │       │   │   └── FaqService.java    # Business logic (@Service)
    │   │       │   ├── controller/
    │   │       │   │   └── FaqController.java # REST Controller (@RestController)
    │   │       │   └── dto/
    │   │       │       ├── CreateFaqRequest.java
    │   │       │       └── UpdateFaqRequest.java
    │   │       │
    │   │       └── user/                      # 📁 Future User Feature
    │   │           └── ...
    │   └── resources/
    │       └── application.properties         # Server port (3000) & MongoDB URI
    └── test/
        └── java/com/example/new_java_app/
            └── NewJavaAppApplicationTests.java
```

---

## 🏗️ Code Architecture: Package-by-Feature (Like NestJS Modules)

Instead of giant global folders (`all controllers/`, `all models/`), we organize code **by feature / domain**:

- **Each service has its own folder:** Everything related to FAQ is in `faq/`. Everything related to User will be in `user/`.
- **Easy deletion:** If you ever remove or replace a feature, you simply delete its single folder.
- **Fewer imports:** Since `Faq`, `FaqRepository`, and `FaqService` share the same package `com.example.new_java_app.faq`, they don't even need to import each other!


---

## 🏗️ Code Architecture & Design Pattern

Spring Boot follows the **Layered Architecture** (Separation of Concerns), similar to frameworks like **NestJS**.

### 1. Request Data Flow

```text
HTTP Request
     │
     ▼
[ Controller Layer ]      <── Handles HTTP, routing, query/body params, status codes
     │
     ▼
[ Service Layer ]         <── Business logic, calculations, data transformations
     │
     ▼
[ Repository Layer ]      <── Database queries (JPA, MongoDB, SQL)
     │
     ▼
  Database
```

---

### 2. Standard Layers Explained

| Layer | Package | NestJS / Node.js Equivalent | Responsibility |
| :--- | :--- | :--- | :--- |
| **Controller** | `com.example.new_java_app.controller` | `@Controller()` / Express routes | Receives HTTP requests (`GET`, `POST`, etc.), parses input JSON, returns HTTP responses. Keeps zero business logic. |
| **Service** | `com.example.new_java_app.service` | `@Injectable()` Providers | Contains business logic, validations, database transaction boundaries (`@Transactional`), and orchestrates multiple repositories. |
| **Repository** | `com.example.new_java_app.repository` | TypeORM Repository / Prisma Client | Database operations (`save`, `findById`, `delete`). Uses Spring Data interfaces without writing raw SQL. |
| **Model / Entity**| `com.example.new_java_app.model` | TypeORM `@Entity` / Mongoose Schema | Represents database tables/collections (e.g., `@Entity User`). |
| **DTO** | `com.example.new_java_app.dto` | NestJS DTO classes / TypeScript interfaces | Contracts for request/response payloads (Java `record`s), preventing direct exposure of database models. |
| **Config** | `com.example.new_java_app.config` | NestJS Modules / Middleware | Global configurations like CORS, Security, Swagger, JWT filters. |

---

### 3. Recommended Production Code Structure

As your project grows, organize classes into dedicated packages under `com.example.new_java_app`:

```text
src/main/java/com/example/new_java_app/
├── NewJavaAppApplication.java                 # Main entry point (@SpringBootApplication)
│
├── controller/                                # API endpoints & routing
│   └── UserController.java
│
├── service/                                   # Business logic layer
│   ├── UserService.java                       # Interface
│   └── impl/
│       └── UserServiceImpl.java               # Implementation (@Service)
│
├── repository/                                # Database access layer
│   └── UserRepository.java                    # (@Repository / JpaRepository)
│
├── model/                                     # Database entities
│   └── User.java                              # (@Entity / @Table)
│
├── dto/                                       # Request & Response schemas
│   ├── CreateUserRequest.java                 # (record)
│   └── UserResponse.java                      # (record)
│
└── config/                                    # Security, CORS, Swagger
    └── CorsConfig.java
```


