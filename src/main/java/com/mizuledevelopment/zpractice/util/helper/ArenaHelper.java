package com.mizuledevelopment.zpractice.util.helper;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.zPractice;
import org.bson.Document;

public class ArenaHelper {

    public static Arena from(zPractice plugin, Document document) {
        return new Arena(plugin, document.getString("name"), document.getString("world"), document.getString("location-one"),
            document.getString("location-two"), document.getList("items", String.class));
    }

    public static Document from(Arena arena) {
        Document document = new Document();
        document.put("name", arena.getName());
        document.put("world", arena.getWorld());
        document.put("location-one", arena.getLocationOne());
        document.put("location-two", arena.getLocationTwo());
        document.put("items", arena.getKitItems());
        return document;
    }
}
