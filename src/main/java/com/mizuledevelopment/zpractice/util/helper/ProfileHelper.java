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
                        document.getList("arenaWins", String.class),
                        document.getList("arenaLoses", String.class),
                        document.getList("arenaKills", String.class),
                        document.getList("arenaDeaths", String.class));
    }

    public static Document from(Profile profile) {
        Document document = new Document();
        document.put("uuid", profile.getUuid());
        document.put("kills", profile.getKills());
        document.put("deaths", profile.getDeaths());
        document.put("wins", profile.getWins());
        document.put("loses", profile.getLoses());
        document.put("arenaWins", profile.getArenaWins());
        document.put("arenaLoses", profile.getArenaLoses());
        document.put("arenaKills", profile.getArenaKills());
        document.put("arenaDeaths", profile.getArenaDeaths());
        return document;
    }
}
