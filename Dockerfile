FROM openjdk:11
VOLUME /main-app
ADD build/libs/*.jar readingisgood.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "readingisgood.jar"]