package com.mizuledevelopment.profiles.manager;

import com.mizuledevelopment.ZPractice;
import com.mizuledevelopment.profiles.Profile;
import com.mizuledevelopment.util.helper.ProfileHelper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

public class ProfileManager {

    private final ZPractice plugin;
    private final Set<Profile> profiles = new HashSet<>();

    public ProfileManager(ZPractice plugin) {
        this.plugin = plugin;
    }

    public void load(){
        FindIterable<Document> findIterable = this.plugin.getMongoHandler().getProfiles().find();
        try (MongoCursor<Document> cursor = findIterable.iterator()) {
            while (cursor.hasNext()) {
                profiles.add(ProfileHelper.from(cursor.next()));
            }
        }
    }

    public void save(){
        profiles.forEach(profile -> this.plugin.getMongoHandler().getProfiles().replaceOne(Filters.eq("uuid",
                profile.getUuid()), ProfileHelper.from(profile), new UpdateOptions().upsert(true)));
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public boolean containsProfile(final String uniqueId) {
        for (Profile profile : profiles) {
            if (profile.getUuid().equalsIgnoreCase(uniqueId)) {
                return true;
            }
        }
        return false;
    }

    public void createProfile(final String string) {
        profiles.add(new Profile(string, 0,0,0,0,null,null,null,null));
    }
}
