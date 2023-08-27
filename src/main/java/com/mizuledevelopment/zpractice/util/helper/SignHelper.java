package com.mizuledevelopment.zpractice.util.helper;

import com.google.gson.reflect.TypeToken;
import com.mizuledevelopment.zpractice.sign.DataSign;
import com.mizuledevelopment.zpractice.util.LazyLocation;
import com.mizuledevelopment.zpractice.zPractice;
import org.bson.Document;

public class SignHelper {

    public static Document from(DataSign dataSign){
        Document document = new Document();
        document.put("name", dataSign.getName());
        document.put("arena", dataSign.getArena());
        document.put("kit", dataSign.getKit());
        document.put("location", zPractice.GSON.toJson(dataSign.getLocation(), LazyLocation.class));
        document.put("players", dataSign.getMaxPlayers());
        return document;
    }

    public static DataSign from(Document document) {
        return new DataSign(document.getString("name"), document.getString("arena"), document.getString("kit"),
            zPractice.GSON.fromJson(document.getString("location"), LazyLocation.class), document.getInteger("players"));
    }
}
