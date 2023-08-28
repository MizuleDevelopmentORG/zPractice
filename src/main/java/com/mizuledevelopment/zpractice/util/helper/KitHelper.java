package com.mizuledevelopment.zpractice.util.helper;

import com.mizuledevelopment.zpractice.kit.Kit;
import org.bson.Document;

public class KitHelper {

    public static Document from(Kit kit) {
        Document document = new Document();
        document.put("name", kit.getName());
        document.put("items", kit.getItems());
        return document;
    }

    public static Kit from(Document document) {
        return new Kit(document.getString("name"), document.getList("items", String.class));
    }
}
