package com.mizuledevelopment.util.helper;

import com.mizuledevelopment.sign.DataSign;
import org.bson.Document;

public class SignHelper {

    public static Document from(DataSign dataSign){
        Document document = new Document();
        document.put("name", dataSign.getName());
        document.put("arena", dataSign.getArena());
        document.put("kit", dataSign.getKit());
        return document;
    }

    public static DataSign from(Document document) {
        return new DataSign(document.getString("name"), document.getString("arena"), document.getString("kit"));
    }
}
