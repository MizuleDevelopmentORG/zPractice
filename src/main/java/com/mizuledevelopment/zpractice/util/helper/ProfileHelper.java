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
                        document.getList("items3", String.class),
                        document.getList("armor1", String.class),
                        document.getList("armor2", String.class),
                        document.getList("armor3", String.class),
                        document.getList("bar1", String.class),
                        document.getList("bar2", String.class),
                        document.getList("bar3", String.class),
                        document.getInteger("selected"));
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
        document.put("armor1", profile.getItems1Armor());
        document.put("armor2", profile.getItems2Armor());
        document.put("armor3", profile.getItems3Armor());
        document.put("bar1", profile.getItems1Bar());
        document.put("bar2", profile.getItems2Bar());
        document.put("bar3", profile.getItems3Bar());
        document.put("selected", profile.getSelectedKit());
        return document;
    }
}
