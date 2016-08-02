#mvn clean package
#For debugging on 5005 port
#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/simple-micro-service-1.0-SNAPSHOT-exec.jar
java -jar target/simple-micro-service-1.0-SNAPSHOT-consul-exec.jar --server.port=2226