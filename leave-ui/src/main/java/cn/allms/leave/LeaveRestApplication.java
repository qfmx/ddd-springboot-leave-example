package cn.allms.leave;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class LeaveRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeaveRestApplication.class, args);
    }
}
