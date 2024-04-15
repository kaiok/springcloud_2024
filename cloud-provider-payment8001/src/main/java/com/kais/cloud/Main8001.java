package com.kais.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author kais
 * @date 2024.04.04. 15:35
 */
@SpringBootApplication
// consul 注册服务
@EnableDiscoveryClient
@MapperScan("com.kais.cloud.mapper")
// consul动态刷新配置
@RefreshScope
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }
}
