package com.mizuledevelopment.zpractice.kit.manager;

import com.mizuledevelopment.zpractice.kit.Kit;
import com.mizuledevelopment.zpractice.util.helper.KitHelper;
import com.mizuledevelopment.zpractice.zPractice;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

public class KitManager {

    private final zPractice plugin;
    private final Set<Kit> kits = new HashSet<>();

    public KitManager(zPractice plugin) {
        this.plugin = plugin;
    }

    public void load(){
        FindIterable<Document> iterable = this.plugin.getMongoHandler().getKits().find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                kits.add(KitHelper.from(cursor.next()));
            }
        }
    }

    @Deprecated
    public void save(){
        kits.forEach(kit -> this.plugin.getMongoHandler().getKits()
            .replaceOne(Filters.eq("name", kit.getName()), KitHelper.from(kit), new UpdateOptions().upsert(true)));
    }
}
