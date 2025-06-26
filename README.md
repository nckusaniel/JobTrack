# JobTrack - 工作追蹤系統

## 📝 專案簡介
JobTrack 是一個簡易的工作追蹤系統，使用 **Spring Boot** 作為後端框架，整合 **PostgreSQL** 資料庫與 **Swagger UI** API 文件工具。此系統提供基本的 **使用者 CRUD 功能**，方便面試記錄、求職進度管理或作為個人學習與作品展示使用。

---

## 🔧 技術棧（Tech Stack）
- 🧠 **Java 17**
- ⚙️ **Spring Boot 3.x**
- 🐘 **PostgreSQL**
- 🐳 **Docker（可選容器化）**
- 🧪 **JUnit 5**（單元測試）
- 📄 **Swagger UI**（自動產生 API 文件）

---

## 🚀 快速開始

### 1️⃣ 環境需求
- Java 17
- Maven 3.8+
- PostgreSQL 13+
- Docker（可選）
- 
🛠️ 建立資料庫
啟動 PostgreSQL，並建立資料庫 jobtrack_db：

CREATE DATABASE jobtrack_db;

⚙️ 設定資料庫連線
修改 src/main/resources/application.properties：

spring.datasource.url=jdbc:postgresql://localhost:5432/jobtrack_db
spring.datasource.username=postgres
spring.datasource.password=你的密碼
spring.jpa.hibernate.ddl-auto=update

▶️ 啟動專案
使用 Maven 啟動：

mvn spring-boot:run
瀏覽 http://localhost:8080/swagger-ui/index.html 查看 API 文件。

🔁 API 使用方式
✔️ 基本 API 路由（User）
方法	路由	說明
GET	/api/users	取得所有使用者
POST	/api/users	建立新使用者
PUT	/api/users/{id}	更新使用者資料
DELETE	/api/users/{id}	刪除使用者

✅ POST 範例

{
  "username": "newuser",
  "email": "newuser@example.com"
}

🧪 單元測試
執行專案的單元測試：

mvn test

📦 Docker 容器化（可選）
🔨 打包專案

mvn clean package

🐳 建立 Docker 映像檔
建立 Dockerfile

FROM openjdk:17-jdk-slim
COPY target/jobtrack-0.0.1-SNAPSHOT.jar jobtrack.jar
ENTRYPOINT ["java", "-jar", "/jobtrack.jar"]

接著建置映像檔並執行容器：
docker build -t jobtrack .
docker run -p 8080:8080 jobtrack
