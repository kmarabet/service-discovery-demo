#mvn clean package
#For debugging on 5005 port
#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/eureka-service-1.0-SNAPSHOT-exec.jar
java -jar target/eureka-service-1.0-SNAPSHOT-exec.jar