package com.mizuledevelopment;

import com.mizuledevelopment.listener.ProfileListener;
import com.mizuledevelopment.mongo.MongoHandler;
import com.mizuledevelopment.profiles.manager.ProfileManager;
import com.mizuledevelopment.queue.manager.QueueManager;
import com.mizuledevelopment.sign.command.SignCreateCommand;
import com.mizuledevelopment.sign.command.SignDeleteCommand;
import com.mizuledevelopment.sign.listener.SignListener;
import com.mizuledevelopment.sign.manager.DataSignManager;
import com.mizuledevelopment.util.command.manager.CommandManager;
import com.mizuledevelopment.util.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public final class ZPractice extends JavaPlugin {

    private Config configuration;
    private Config messages;
    private ProfileManager profileManager;
    private DataSignManager dataSignManager;
    private MongoHandler mongoHandler;
    private QueueManager queueManager;

    @Override
    public void onEnable() {
        this.configuration();
        this.command();
        this.listener(Bukkit.getPluginManager());

        this.mongoHandler = new MongoHandler(this.getConfiguration().getConfiguration().getString("mongo.uri"));
        this.profileManager = new ProfileManager(this);
        this.dataSignManager = new DataSignManager(this);
        this.dataSignManager.load();
        this.profileManager.load();

        this.queueManager = new QueueManager();
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

    private void listener(PluginManager pluginManager) {
        Arrays.asList(
                new ProfileListener(this),
                new SignListener(this)
        ).forEach(listener -> pluginManager.registerEvents(listener, this));
    }

    private void command(){
        CommandManager signManager = new CommandManager(this.getCommand("sign"));
        signManager.addSubCommand(new SignCreateCommand(this));
        signManager.addSubCommand(new SignDeleteCommand(this));
        signManager.registerCommands();
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

    public DataSignManager getDataSignManager() {
        return dataSignManager;
    }

    public QueueManager getQueueManager() {
        return queueManager;
    }
}
