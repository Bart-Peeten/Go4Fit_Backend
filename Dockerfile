FROM openjdk:8
ADD target/Go4Fit_API.jar Go4Fit_API.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "Go4Fit_API.jar"]