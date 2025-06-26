# 使用輕量化的 Java 17 環境
FROM openjdk:17-jdk-slim

# 複製 Jar 檔到容器內部，命名為 jobtrack.jar
COPY target/jobtrack-0.0.1-SNAPSHOT.jar jobtrack.jar

# 指定容器啟動時要執行的指令
ENTRYPOINT ["java", "-jar", "/jobtrack.jar"]
