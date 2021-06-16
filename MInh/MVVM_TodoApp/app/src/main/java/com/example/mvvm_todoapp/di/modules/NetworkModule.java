package com.example.mvvm_todoapp.di.modules;

import com.example.mvvm_todoapp.data.model.TodoTask;
import com.example.mvvm_todoapp.data.remote.LiveDataCallAdapterFactory;
import com.example.mvvm_todoapp.data.remote.Service;
import com.example.mvvm_todoapp.utils.JsonUtils.GetItemDeserializer;
import com.example.mvvm_todoapp.utils.JsonUtils.GetItemListDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Singleton
    @Provides
    public Service provideRetrofitService(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy/MM/dd")
                .registerTypeAdapter(new TypeToken<List<TodoTask>>() {}.getType(), new GetItemListDeserializer())
                .registerTypeAdapter(TodoTask.class, new GetItemDeserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl("https://5fa8f65ac9b4e90016e69cdc.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build().create(Service.class);
    }
}
