package com.mizuledevelopment.zpractice.queue.manager;

import com.mizuledevelopment.zpractice.queue.Queue;

import java.util.HashSet;
import java.util.Set;

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
}
