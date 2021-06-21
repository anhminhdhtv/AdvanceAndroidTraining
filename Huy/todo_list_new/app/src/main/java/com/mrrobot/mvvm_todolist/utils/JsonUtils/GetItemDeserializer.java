package com.mrrobot.mvvm_todolist.utils.JsonUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mrrobot.mvvm_todolist.data.model.Todo;

import java.lang.reflect.Type;

public class GetItemDeserializer implements JsonDeserializer<Todo> {
    @Override
    public Todo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Todo items = new Todo(
                jsonObject.get("id").getAsString(),
                jsonObject.get("task_name").getAsString(),
                jsonObject.get("date").getAsString(),
                jsonObject.get("description").getAsString());
        return items;
    }
}