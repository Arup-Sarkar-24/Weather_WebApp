package com.mycompany.weatherapp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.mycompany.weatherapp.repositories", // Path to MongoDB repositories
        mongoTemplateRef = "mongoTemplate" // Specify the MongoTemplate bean name
)
public class RealtimeDB extends AbstractMongoClientConfiguration {

    @Value("${realtime.data.mongodb.uri}")
    private String mongoUri;

    @Override
    protected String getDatabaseName() {
        return "weather_realtime"; // MongoDB database name
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory) {
        return new MongoTemplate(databaseFactory);
    }
}
