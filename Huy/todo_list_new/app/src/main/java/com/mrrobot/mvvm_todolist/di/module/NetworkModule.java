package com.mrrobot.mvvm_todolist.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.remote.LiveDataCallAdapterFactory;
import com.mrrobot.mvvm_todolist.data.remote.Service;
import com.mrrobot.mvvm_todolist.utils.JsonUtils.GetItemDeserializer;
import com.mrrobot.mvvm_todolist.utils.JsonUtils.GetItemListDeserializer;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    //    public static final String BASE_URL="https://5fa8f65ac9b4e90016e69cdc.mockapi.io/";
//
//    private static Retrofit retrofit;
//    @Singleton
//    @Provides
//    public static Retrofit getRetrofitInstance(){
//        if(retrofit==null) {
//            Gson gson = new GsonBuilder().setLenient().create();
//            OkHttpClient builder = new OkHttpClient.Builder().readTimeout(50000, TimeUnit.MILLISECONDS)//thời gian cho app đọc data
//                    .writeTimeout(5000, TimeUnit.MILLISECONDS)//thời gian cho app ghi dữ liệu
//                    .connectTimeout(5000, TimeUnit.MILLISECONDS)//thời gian cho app kết nối lại
//                    .retryOnConnectionFailure(true)//kết nối lại
//                    .build();
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .client(builder)
//                    .build();
//        }
//        return  retrofit;
//    }
    @Singleton
    @Provides
    public Service provideRetrofitService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy/MM/dd")
                .registerTypeAdapter(new TypeToken<List<Todo>>() {
                }.getType(), new GetItemListDeserializer())
                .registerTypeAdapter(Todo.class, new GetItemDeserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl("https://5fa8f65ac9b4e90016e69cdc.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build().create(Service.class);
    }
}
