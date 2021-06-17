package com.mrrobot.mvvm_todolist.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mrrobot.mvvm_todolist.MyApplication;
import com.mrrobot.mvvm_todolist.data.db.AppDatabase;
import com.mrrobot.mvvm_todolist.data.db.dao.TodoDao;
import com.mrrobot.mvvm_todolist.data.model.Todo;
import com.mrrobot.mvvm_todolist.data.remote.LiveDataCallAdapterFactory;
import com.mrrobot.mvvm_todolist.data.remote.RetrofitRequest;
import com.mrrobot.mvvm_todolist.data.remote.Service;
import com.mrrobot.mvvm_todolist.data.repository.RepositoryData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppContainer {
    Application application;
    AppDatabase db;
    TodoDao todoDao;
    Service service;
    public RepositoryData repositoryData;
    public AppContainer(Application application) {
        this.application = application;
        db= getDatabase(this.application);
        todoDao= db.getTodoDAO();
        service= retrofit.create(Service.class);
        repositoryData = new RepositoryData(todoDao,service);
    }
//    public Application setApplication(Application application){
//        this.application = application;
//        return application;
//    }

    public static final String BASE_URL="https://5fa8f65ac9b4e90016e69cdc.mockapi.io/";
    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient builder = new OkHttpClient.Builder().readTimeout(50000, TimeUnit.MILLISECONDS)//thời gian cho app đọc data
            .writeTimeout(5000, TimeUnit.MILLISECONDS)//thời gian cho app ghi dữ liệu
            .connectTimeout(5000, TimeUnit.MILLISECONDS)//thời gian cho app kết nối lại
            .retryOnConnectionFailure(true)//kết nối lại
            .build();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .build();
    private static volatile com.mrrobot.mvvm_todolist.data.db.AppDatabase INSTANCE;
    public AppDatabase getDatabase(Application application){
        if(INSTANCE==null){
            synchronized (com.mrrobot.mvvm_todolist.data.db.AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(application,
                            com.mrrobot.mvvm_todolist.data.db.AppDatabase.class, "myDatabase")
                            .build();
                }

            }
        }
        return INSTANCE;
    }

}
