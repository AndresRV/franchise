FROM openjdk:17-jdk-slim
WORKDIR /app
ADD build/libs/power-up-arquetipo-0.0.1-SNAPSHOT.jar app/test.jar
EXPOSE 8081
CMD ["/bin/sh"]
ENTRYPOINT ["java", "-jar", "-jar", "app/test.jar"]

#sudo docker images
#sudo docker build -t franchise-cf .
#sudo docker rmi franchise-cf
#sudo docker run -p 8081:8080 --name franchise franchise-cf
#sudo docker ps -a
#sudo docker start franchise
#sudo docker stop franchise
#sudo docker rm franchise