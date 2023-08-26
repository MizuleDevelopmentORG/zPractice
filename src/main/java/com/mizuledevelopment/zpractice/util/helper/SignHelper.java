package com.mizuledevelopment.zpractice.util.helper;

import com.mizuledevelopment.zpractice.sign.DataSign;
import org.bson.Document;

public class SignHelper {

    public static Document from(DataSign dataSign){
        Document document = new Document();
        document.put("name", dataSign.getName());
        document.put("arena", dataSign.getArena());
        document.put("kit", dataSign.getKit());
        document.put("location", dataSign.getLocation());
        document.put("players", dataSign.getMaxPlayers());
        return document;
    }

    public static DataSign from(Document document) {
        return new DataSign(document.getString("name"), document.getString("arena"), document.getString("kit"),
                document.getString("location"), document.getInteger("players"));
    }
}
