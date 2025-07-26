import grpc
import hello_pb2
import hello_pb2_grpc

def run():
    with grpc.insecure_channel('localhost:9090') as channel:
        stub = hello_pb2_grpc.SimpleStub(channel)
        request = hello_pb2.HelloRequest(name='Abhijit')
        responses = stub.StreamHello(request)
        for response in responses:
            print("SayHello client received", response)

run()