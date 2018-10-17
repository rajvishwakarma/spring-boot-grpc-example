# spring-boot-grpc-example
This is sample gRPC implementation.

To test the app and compare between gRPC and REST, test the following APIs-

A. For gRPC
   URL: http://localhost:8082/grpc?fname=test&lname=user
   Method: GET
   Response: Hello test user!
   
B. For REST
   URL: http://localhost:8082/rest?fname=test&lname=user
   Method: GET
   Response: Hello test user!
