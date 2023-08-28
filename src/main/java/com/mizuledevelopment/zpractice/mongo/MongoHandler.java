package com.mizuledevelopment.zpractice.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.lang.module.ModuleFinder;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoHandler {

    private final MongoCollection<Document> profiles;
    private final MongoCollection<Document> signs;
    private final MongoCollection<Document> kits;
    private final MongoCollection<Document> arenas;

    public MongoHandler(final String s) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(s))
                .codecRegistry(pojoCodecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("practice");

        this.profiles = mongoDatabase.getCollection("profiles");
        this.signs = mongoDatabase.getCollection("signs");
        this.kits = mongoDatabase.getCollection("kits");
        this.arenas = mongoDatabase.getCollection("arenas");
    }

    public MongoCollection<Document> getProfiles() {
        return profiles;
    }

    public MongoCollection<Document> getSigns() {
        return signs;
    }

    public MongoCollection<Document> getKits() {
        return kits;
    }

    public MongoCollection<Document> getArenas() {
        return arenas;
    }
}
