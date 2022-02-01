package com.markdown.auth.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@Profile({"dev"})
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(this.uri);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
