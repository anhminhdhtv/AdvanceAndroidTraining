package com.mrrobot.mvvm_todolist.utils.JsonUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mrrobot.mvvm_todolist.data.model.Todo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetItemListDeserializer  implements JsonDeserializer<List<Todo>> {
    @Override
    public List deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List items = new ArrayList<>();

        final JsonArray itemsJsonArray = json.getAsJsonArray();

        for (JsonElement itemsJsonElement : itemsJsonArray) {
            final JsonObject itemJsonObject = itemsJsonElement.getAsJsonObject();
            final String id = itemJsonObject.get("id").getAsString();
            final String name = itemJsonObject.get("task_name").getAsString();
            final String date = itemJsonObject.get("date").getAsString();
            final String description = itemJsonObject.get("description").getAsString();

            items.add(new Todo(id, name, date, description));
        }

        return items;
    }
}