package com.mizuledevelopment.zpractice.tab;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabManager {

    private final zPractice plugin;
    private final List<String> header;
    private final List<String> footer;

    public TabManager(zPractice plugin){
        this.plugin = plugin;
        this.header = new ArrayList<>(this.plugin.getConfiguration().getConfiguration().getStringList("tab.header"));
        this.footer = new ArrayList<>(this.plugin.getConfiguration().getConfiguration().getStringList("tab.footer"));
    }

    public Component getHeader() {
        String result = null;
        for (String string : header) {
            if (result == null) {
                result = string;
            } else {
                result = result + string;
            }
        }
        return TextUtil.parse(result, MessageType.from(Objects.requireNonNull(result)));
    }

    public Component getFooter() {
        String result = null;
        for (String string : footer) {
            if (result == null) {
                result = string;
            } else {
                result = result + string;
            }
        }
        return TextUtil.parse(result, MessageType.from(Objects.requireNonNull(result)));
    }
}
