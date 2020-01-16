package com.plr.vehicle.config;

import com.plr.vehicle.util.IdWorkerUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:13
 */
@Configuration
@ConfigurationProperties(prefix = "util")
@Data
public class IdWorkerConfig {
    private Long workerId;
    private Long dataCenterId;
    @Bean
    public IdWorkerUtil createIdWorker() {
        IdWorkerUtil worker = new IdWorkerUtil(workerId, dataCenterId);
        return worker;
    }
}
