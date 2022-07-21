FROM openjdk:17-oracle
COPY target/bookslist-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookslist-0.0.1-SNAPSHOT.jar"]

