package com.example.mvvm_todoapp.utils.JsonUtils;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.utils.Utilities;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetItemListDeserializer  implements JsonDeserializer<List<TodoTask>> {
    @Override
    public List deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List items = new ArrayList<>();

        final JsonArray itemsJsonArray = json.getAsJsonArray();

        for (JsonElement itemsJsonElement : itemsJsonArray) {
            final JsonObject itemJsonObject = itemsJsonElement.getAsJsonObject();
            final String id = itemJsonObject.get("id").getAsString();
            final String name = itemJsonObject.get("task_name").getAsString();
            final LocalDate date = Utilities.convertStringToDate(itemJsonObject.get("date").getAsString());
            final String description = itemJsonObject.get("description").getAsString();

            items.add(new TodoTask(id, name, date, description));
        }

        return items;
    }
}