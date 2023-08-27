package com.mizuledevelopment.zpractice.util.gson;

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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mizuledevelopment.zpractice.zPractice;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class JsonChain {

    private final JsonObject json = new JsonObject();

    public JsonChain addProperty(
        final @NotNull String property,
        final @NotNull String value
    ) {
        this.json.addProperty(property, value);
        return this;
    }

    public JsonChain addProperty(
        final @NotNull String property,
        final @NotNull Number value
    ) {
        this.json.addProperty(property, value);
        return this;
    }

    public JsonChain addProperty(
        final @NotNull String property,
        final @NotNull Boolean value
    ) {
        this.json.addProperty(property, value);
        return this;
    }

    public JsonChain addProperty(
        final @NotNull String property,
        final @NotNull Character value
    ) {
        this.json.addProperty(property, value);
        return this;
    }

    public JsonChain addProperty(
        final @NotNull String property,
        final @NotNull Object value
    ) {
        this.json.add(property, zPractice.GSON.toJsonTree(value));
        return this;
    }

    public JsonChain addProperty(
        final @NotNull String property,
        final @NotNull UUID value
    ) {
        this.json.add(property, new JsonPrimitive(value.toString()));
        return this;
    }

    public JsonChain add(
        final @NotNull String property,
        final @NotNull JsonElement element
    ) {
        addElement(property, element);
        return this;
    }

    public JsonChain add(
        final @NotNull String property,
        final @NotNull JsonObject object
    ) {
        addObject(property, object);
        return this;
    }

    public JsonChain addElement(
        final @NotNull String property,
        final @NotNull JsonElement element
    ) {
        this.json.add(property, element);
        return this;
    }

    public JsonChain addObject(
        final @NotNull String property,
        final @NotNull JsonObject object
    ) {
        this.json.add(property, object);
        return this;
    }

    public JsonObject get() {
        return this.json;
    }
}
