package com.mizuledevelopment.zpractice.sign.manager;

import com.mizuledevelopment.zpractice.zPractice;
import com.mizuledevelopment.zpractice.sign.DataSign;
import com.mizuledevelopment.zpractice.util.helper.SignHelper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

public class DataSignManager {

    private final zPractice plugin;
    private final Set<DataSign> dataSigns = new HashSet<>();

    public DataSignManager(zPractice plugin) {
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

    @SuppressWarnings(value = "ALL")
    public void save(){
        dataSigns.forEach(dataSign -> this.plugin.getMongoHandler().getSigns().replaceOne(
                Filters.eq("name", dataSign.getName()), SignHelper.from(dataSign), new UpdateOptions().upsert(true)));
    }

    public DataSign getSignByLocation(String location){
        for (DataSign dataSign : dataSigns) {
            if (dataSign.getLocation().equalsIgnoreCase(location)) {
                return dataSign;
            }
        }
        return null;
    }

    public Set<DataSign> getDataSigns() {
        return dataSigns;
    }

    public DataSign getSignByName(final String name) {
        for (DataSign dataSign : dataSigns) {
            if (dataSign.getName().equalsIgnoreCase(name)) {
                return dataSign;
            }
        }
        return null;
    }

    public void remove(final DataSign signByLocation) {
        dataSigns.remove(signByLocation);
    }


}
