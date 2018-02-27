package com.kroger.neo4jRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Neo4jRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jRedisApplication.class, args);
	}
}
