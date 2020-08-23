package com.rayh.examples.grpc.server;

import com.rayh.examples.grpc.service.DataFlowServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaServer
public class GrpcDataflowServer {

    public static void main(String args[]){
        SpringApplication.run(GrpcDataflowServer.class);
    }

    @Component
    @RequiredArgsConstructor
    public static class Worker implements CommandLineRunner {

        @Value("grpc.server.port")
        private int grpcServerPort;

        @Override
        public void run(String... args) throws Exception {
            Server server = ServerBuilder.forPort(grpcServerPort).
                                addService(new DataFlowServiceImpl()).build();
            server.start();
            server.awaitTermination();
        }
    }

}
