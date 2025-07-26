# gRPC 

commands to fire request to gRPC service

grpcurl -plaintext -d "{\"name\":\"kedare\"}" localhost:9090 Simple.SayHello

grpcurl -plaintext -d "{\"name\":\"Abhijit\"}" localhost:9090 Simple.StreamHello

grpcurl -plaintext -d "{}" localhost:9090 grpc.health.v1.Health.Watch

grpcurl -plaintext -d "{}" localhost:9090 grpc.health.v1.Health.Check

* https://docs.spring.io/spring-grpc/reference/index.html

To develope client in python - follow below steps

Generate stub

python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. hello.proto

