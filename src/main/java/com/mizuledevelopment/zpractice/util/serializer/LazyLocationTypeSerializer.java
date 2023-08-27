package com.mizuledevelopment.zpractice.util.serializer;

/*
 * This file is part of Axel, licensed under the Apache 2.0 License.
 *
 *  Copyright (c) 2022-2023 MineArcade
 *  Copyright (c) 2022-2023 powercas_gamer
 *  Copyright (c) 2022-2023 contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import com.google.gson.*;
import com.mizuledevelopment.zpractice.util.LazyLocation;
import com.mizuledevelopment.zpractice.util.gson.JsonChain;

import java.lang.reflect.Type;

public class LazyLocationTypeSerializer implements JsonSerializer<LazyLocation>, JsonDeserializer<LazyLocation> {

    @Override
    public LazyLocation deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
        if (element == null || element.isJsonNull() || !element.isJsonObject())
            return null;

        final JsonObject object = element.getAsJsonObject();
        return new LazyLocation(
            object.get("world").getAsString(),
            object.has("world-name") ? object.get("world-name").getAsString() : null,
            object.get("x").getAsDouble(),
            object.get("y").getAsDouble(),
            object.get("z").getAsDouble(),
            object.get("yaw").getAsFloat(),
            object.get("pitch").getAsFloat()
        );
    }

    @Override
    public JsonElement serialize(final LazyLocation location, final Type type, final JsonSerializationContext context) {
        if (location == null)
            return null;

        /*
                node.node("world").set(String.class, value.world());
        node.node("world-name").set(String.class, value.worldName());
        node.node("x").set(Double.class, value.x());
        node.node("y").set(Double.class, value.y());
        node.node("z").set(Double.class, value.z());
        node.node("yaw").set(Float.class, value.yaw());
        node.node("pitch").set(Float.class, value.pitch());
         */

        return new JsonChain()
            .addProperty("world", location.world())
            .addProperty("world-name", location.worldName())
            .addProperty("x", location.x())
            .addProperty("y", location.y())
            .addProperty("z", location.z())
            .addProperty("yaw", location.yaw())
            .addProperty("pitch", location.pitch())
            .get();
    }
}
