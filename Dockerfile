FROM openjdk:11.0-oracle
ADD ./target/muzix-App-0.0.1-SNAPSHOT.jar /usr/src/muzix-App-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar","muzix-App-0.0.1-SNAPSHOT.jar"]