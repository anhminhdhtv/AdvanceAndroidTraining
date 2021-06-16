package com.example.mvvm_todoapp.utils.JsonUtils;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.utils.Utilities;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GetItemDeserializer implements JsonDeserializer<TodoTask> {
    @Override
    public TodoTask deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        TodoTask items = new TodoTask(
                jsonObject.get("id").getAsString(),
                jsonObject.get("task_name").getAsString(),
                Utilities.convertStringToDate(jsonObject.get("date").getAsString()),
                jsonObject.get("description").getAsString());
        return items;
    }
}