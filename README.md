How to run:

1) Install Apache Kafa
2) Navigate to "C:\kafka\bin\windows" and use below command to start "kafka server"
  Command to start kafka server: kafka-server-start.bat C:\kafka\config\kraft\server.properties
3) Fork this repo and open in Intellij IDE. Start the application.
4) Try to run the POST and GET API endpoints, to check whether the application is running.
5) We can able to see the messages in the console.

=> If anyone wants to see the messages in console.Use below commands
```
  Command to create kafka topic: kafka-topics.bat --create --topic test --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
  Command to create kafka producer: kafka-console-producer.bat --topic test --bootstrap-server localhost:9092
  command to create consumer: kafka-console-consumer.bat --topic test --from-beginning --bootstrap-server localhost:9092
```
