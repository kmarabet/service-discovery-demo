#mvn clean package
#For debugging on 5005 port
#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/simple-micro-service-1.0-SNAPSHOT-exec.jar
#export SERVER_PORT=2223
java -jar target/simple-micro-service-1.0-SNAPSHOT-exec.jar --server.port=2223