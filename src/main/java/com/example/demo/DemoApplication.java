package com.example.demo;

import com.example.demo.service.impl.UserServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		UserServiceImpl service = applicationContext.getBean(UserServiceImpl.class);
		System.out.println(service.getAaa());
		UserServiceImpl service2 = applicationContext.getBean(UserServiceImpl.class);
		System.out.println(service2.getAaa());

	}

	String mongodbUserName = "root";

	String mongodbPassword = "root";

	String mongodbDatabase = "test";

	String mongodbHost = "127.0.0.1";

	int mongodbPort = 27017;

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		// Set credentials
		//MongoCredential credential = MongoCredential.createCredential(mongodbUserName, mongodbDatabase, mongodbPassword.toCharArray());
		ServerAddress serverAddress = new ServerAddress(mongodbHost, mongodbPort);

		// Mongo Client
		MongoClient mongoClient = new MongoClient(serverAddress);

		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, mongodbDatabase);

		return simpleMongoDbFactory;
	}

	/**
	 * Template ready to use to operate on the database
	 * @return Mongo Template ready to use
	 */
	@Bean(name = "mongodbVs")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}
}
