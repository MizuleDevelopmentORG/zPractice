package com.mizuledevelopment.zpractice.queue.manager;

import com.mizuledevelopment.zpractice.queue.Queue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class QueueManager {

    private final Set<Queue> queues = new HashSet<>();

    public Set<Queue> getQueues() {
        return queues;
    }

    public boolean has(final String name) {
        for (Queue queue : queues) {
            if (queue.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Queue get(final String name) {
        for (Queue queue : queues) {
            if (queue.getName().equalsIgnoreCase(name)) {
                return queue;
            }
        }
        return null;
    }

    public boolean containsPlayer(final UUID uuid) {
        for (Queue queue : queues) {
            if (queue.getPlayers().contains(uuid)) {
                return true;
            }
        }
        return false;
    }
}
