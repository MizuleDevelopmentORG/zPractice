package com.mizuledevelopment.zpractice.util.helper;

import com.mizuledevelopment.zpractice.profiles.Profile;
import org.bson.Document;

import java.util.UUID;

public class ProfileHelper {

    public static Profile from(Document document){
        return new Profile
                ((document.getString("uuid")),
                        document.getInteger("kills"),
                        document.getInteger("deaths"),
                        document.getInteger("wins"),
                        document.getInteger("loses"),
                        document.getList("items1", String.class),
                        document.getList("items2", String.class),
                        document.getList("items3", String.class));
    }

    public static Document from(Profile profile) {
        Document document = new Document();
        document.put("uuid", profile.getUuid());
        document.put("kills", profile.getKills());
        document.put("deaths", profile.getDeaths());
        document.put("wins", profile.getWins());
        document.put("loses", profile.getLoses());
        document.put("items1", profile.getItems1());
        document.put("items2", profile.getItems2());
        document.put("items3", profile.getItems3());
        return document;
    }
}
