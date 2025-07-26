package com.kedare.grpc;

import com.kedare.grpc.proto.HelloReply;
import com.kedare.grpc.proto.HelloRequest;
import com.kedare.grpc.proto.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
    private static final Logger log = LoggerFactory.getLogger(GrpcServerService.class);

    @Override
    public void sayHello(final HelloRequest request, final StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + request.getName());
        if (request.getName().startsWith("error")) {
            throw new IllegalArgumentException("Bad name: "+ request.getName());
        }
        if (request.getName().startsWith("internal")) {
            throw new RuntimeException();
        }
        final HelloReply helloReply = HelloReply.newBuilder().setMessage("Hello! " + request.getName()).build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
    }

    @Override
    public void streamHello(final HelloRequest request, final StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + request.getName());
        int count = 0;
        while (count < 10) {
            HelloReply helloReply = HelloReply.newBuilder().setMessage("Hello(" + count + ")==> " + request.getName()).build();
            responseObserver.onNext(helloReply);
            count++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }
}
