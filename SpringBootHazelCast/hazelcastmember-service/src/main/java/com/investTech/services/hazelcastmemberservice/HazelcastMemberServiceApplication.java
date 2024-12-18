package com.investTech.services.hazelcastmemberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class HazelcastMemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastMemberServiceApplication.class, args);
	}

}
