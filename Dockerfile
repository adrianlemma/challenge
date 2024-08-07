FROM eclipse-temurin:17.0.12_7-jdk

WORKDIR /root
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root
RUN ./mvnw dependency:go-offline
COPY ./src /root/src
RUN ./mvnw clean install

ENTRYPOINT ["java","-jar","/root/target/challenge-0.0.1-SNAPSHOT.jar"]