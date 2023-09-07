package com.mizuledevelopment.zpractice.profiles.manager;

import com.mizuledevelopment.zpractice.profiles.Profile;
import com.mizuledevelopment.zpractice.util.helper.ProfileHelper;
import com.mizuledevelopment.zpractice.zPractice;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProfileManager {

    private final zPractice plugin;
    private final Set<Profile> profiles = new HashSet<>();

    public ProfileManager(final zPractice plugin) {
        this.plugin = plugin;
    }

    public void load() {
        final FindIterable<Document> findIterable = this.plugin.getMongoHandler().getProfiles().find();
        try (final MongoCursor<Document> cursor = findIterable.iterator()) {
            while (cursor.hasNext()) {
                this.profiles.add(ProfileHelper.from(cursor.next()));
            }
        }
    }

    public void save() {
        this.profiles.forEach(profile -> this.plugin.getMongoHandler().getProfiles().replaceOne(Filters.eq("uuid",
            profile.getUuid()), ProfileHelper.from(profile), new ReplaceOptions().upsert(true)));
    }

    public Set<Profile> getProfiles() {
        return this.profiles;
    }

    public boolean containsProfile(final UUID uniqueId) {
        for (final Profile profile : this.profiles) {
            if (profile.getUuid().equals(uniqueId.toString())) {
                return true;
            }
        }
        return false;
    }

    public void createProfile(final String uuid) {
        this.profiles.add(new Profile(uuid, 0, 0, 0, 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0));
    }

    public Profile get(UUID uuid) {
        for (Profile profile : profiles) {
            if (profile.getUuid().equalsIgnoreCase(uuid.toString())) {
                return profile;
            }
        }
        return null;
    }
}
