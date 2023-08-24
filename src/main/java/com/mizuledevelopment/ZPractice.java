package com.mizuledevelopment;

import com.mizuledevelopment.mongo.MongoHandler;
import com.mizuledevelopment.profiles.manager.ProfileManager;
import com.mizuledevelopment.sign.manager.DataSignManager;
import com.mizuledevelopment.util.config.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ZPractice extends JavaPlugin {

    private Config configuration;
    private Config messages;
    private ProfileManager profileManager;
    private DataSignManager dataSignManager;
    private MongoHandler mongoHandler;

    @Override
    public void onEnable() {
        this.configuration();

        this.mongoHandler = new MongoHandler("");
        this.profileManager = new ProfileManager(this);
        this.dataSignManager = new DataSignManager(this);
        this.dataSignManager.load();
        this.profileManager.load();
    }

    @Override
    public void onDisable() {
        this.profileManager.save();
        this.dataSignManager.save();
    }

    private void configuration(){
        configuration = new Config(this, new File(getDataFolder(), "configuration.yml"), new YamlConfiguration(), "configuration.yml");
        configuration.create();
        messages = new Config(this, new File(getDataFolder(), "messages.yml"), new YamlConfiguration(), "messages.yml");
        messages.create();
    }

    public Config getConfiguration() {
        return configuration;
    }

    public Config getMessages() {
        return messages;
    }

    public MongoHandler getMongoHandler() {
        return mongoHandler;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
