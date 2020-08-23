package com.baeldung.grpc.server;

import com.baeldung.grpc.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
public class GrpcServer {

    public static void main(String args[]){
        SpringApplication.run(GrpcServer.class);
    }

    @Component
    @RequiredArgsConstructor
    public static class Worker implements CommandLineRunner {

        @Value("grpc.server.port")
        private String grpcServerPort;

        @Override
        public void run(String... args) throws Exception {
            Server server = ServerBuilder.forPort(8091)
                    .addService(new HelloServiceImpl()).build();

            System.out.println("Starting server...");
            server.start();
            System.out.println("Server started!");
            server.awaitTermination();
        }
    }

}
