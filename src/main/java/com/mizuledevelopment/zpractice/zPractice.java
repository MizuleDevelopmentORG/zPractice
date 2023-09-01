package com.mizuledevelopment.zpractice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mizuledevelopment.zpractice.arena.command.ArenaCreateCommand;
import com.mizuledevelopment.zpractice.arena.command.ArenaDeleteCommand;
import com.mizuledevelopment.zpractice.arena.command.ArenaSetKitCommand;
import com.mizuledevelopment.zpractice.arena.command.ArenaSetLocationCommand;
import com.mizuledevelopment.zpractice.arena.manager.ArenaManager;
import com.mizuledevelopment.zpractice.kit.command.KitCreateCommand;
import com.mizuledevelopment.zpractice.kit.command.KitDeleteCommand;
import com.mizuledevelopment.zpractice.kit.command.KitSetItemsCommand;
import com.mizuledevelopment.zpractice.kit.manager.KitManager;
import com.mizuledevelopment.zpractice.listener.ProfileListener;
import com.mizuledevelopment.zpractice.mongo.MongoHandler;
import com.mizuledevelopment.zpractice.profiles.manager.ProfileManager;
import com.mizuledevelopment.zpractice.queue.manager.QueueManager;
import com.mizuledevelopment.zpractice.sign.command.SignCreateCommand;
import com.mizuledevelopment.zpractice.sign.command.SignDeleteCommand;
import com.mizuledevelopment.zpractice.sign.listener.SignListener;
import com.mizuledevelopment.zpractice.sign.manager.DataSignManager;
import com.mizuledevelopment.zpractice.util.LazyLocation;
import com.mizuledevelopment.zpractice.util.command.manager.CommandManager;
import com.mizuledevelopment.zpractice.util.config.Config;
import com.mizuledevelopment.zpractice.util.serializer.LazyLocationTypeSerializer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public final class zPractice extends JavaPlugin {

    private Config configuration;
    private Config messages;
    private ProfileManager profileManager;
    private DataSignManager dataSignManager;
    private MongoHandler mongoHandler;
    private QueueManager queueManager;
    private KitManager kitManager;
    private ArenaManager arenaManager;

    public static final Gson GSON = GsonComponentSerializer.gson().populator()
        .apply(new GsonBuilder()
            .registerTypeHierarchyAdapter(LazyLocation.class, new LazyLocationTypeSerializer())
            .serializeNulls()
            .enableComplexMapKeySerialization()
        ).create();

    @Override
    public void onEnable() {
        this.configuration();
        this.command();
        this.listener(Bukkit.getPluginManager());

        this.mongoHandler = new MongoHandler(this.getConfiguration().getConfiguration().getString("mongo.uri"));
        this.profileManager = new ProfileManager(this);
        this.dataSignManager = new DataSignManager(this);
        this.kitManager = new KitManager(this);
        this.arenaManager = new ArenaManager(this);

        this.dataSignManager.load();
        this.profileManager.load();
        this.kitManager.load();
        this.arenaManager.load();

        this.queueManager = new QueueManager();
    }

    @Override
    @Deprecated
    public void onDisable() {
        this.profileManager.save();
        this.dataSignManager.save();
        this.kitManager.save();
        this.arenaManager.reset();
        this.arenaManager.save();
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

        CommandManager arenaManager = new CommandManager(this.getCommand("arena"));
        arenaManager.addSubCommand(new ArenaCreateCommand(this));
        arenaManager.addSubCommand(new ArenaDeleteCommand(this));
        arenaManager.addSubCommand(new ArenaSetLocationCommand(this));
        arenaManager.addSubCommand(new ArenaSetKitCommand(this));
        arenaManager.registerCommands();

        CommandManager kitManager = new CommandManager(this.getCommand("kit"));
        kitManager.addSubCommand(new KitCreateCommand(this));
        kitManager.addSubCommand(new KitDeleteCommand(this));
        kitManager.addSubCommand(new KitSetItemsCommand(this));
        kitManager.registerCommands();
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

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }
}
