package com.isheng.core.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@EnableAsync
@EnableScheduling
@EnableDubboConfiguration
@SpringBootApplication(scanBasePackages= {"com.isheng.core.sys"})
public class SysCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysCoreApplication.class, args);
	}
}
