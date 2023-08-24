package com.mizuledevelopment.sign.manager;

import com.mizuledevelopment.ZPractice;
import com.mizuledevelopment.sign.DataSign;
import com.mizuledevelopment.util.helper.SignHelper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

public class DataSignManager {

    private final ZPractice plugin;
    private final Set<DataSign> dataSigns = new HashSet<>();

    public DataSignManager(ZPractice plugin) {
        this.plugin = plugin;
    }

    public void load(){
        FindIterable<Document> iterable = this.plugin.getMongoHandler().getSigns().find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                dataSigns.add(SignHelper.from(cursor.next()));
            }
        }
    }

    public void save(){
        dataSigns.forEach(dataSign -> this.plugin.getMongoHandler().getSigns().replaceOne(
                Filters.eq("name", dataSign.getName()), SignHelper.from(dataSign), new UpdateOptions().upsert(true)));
    }
}
