package com.plr.vehicle;

import com.plr.vehicle.config.SystemConfig;
import com.plr.vehicle.tcp.TcpAsyncServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:19
 */
@SpringBootApplication
@MapperScan("com.plr.vehicle.dao")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new SystemConfig());
        springApplication.run(args);
        TcpAsyncServer tcpServer = new TcpAsyncServer();
        tcpServer.start();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

}
