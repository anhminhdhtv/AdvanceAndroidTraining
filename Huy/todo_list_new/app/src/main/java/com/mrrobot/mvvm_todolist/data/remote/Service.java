package com.mrrobot.mvvm_todolist.data.remote;

import androidx.lifecycle.LiveData;

import com.mrrobot.mvvm_todolist.data.model.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {

    @Headers({"Accept: */*"})
    @GET("/api/todo_task")
    LiveData<ApiResponse<List<Todo>>> getAllTasks();

    @GET("/api/todo_task/{id}")
    LiveData<ApiResponse<Todo>> getTaskByID(@Path("id")String id);

//    @POST("todo_task")
//    LiveData<Void> insertTask(@Body Todo todoTask);
//
//    @PUT("todo_task/{id}")
//    LiveData<ApiResponse<Void>> update(@Path("id") String id, @Body TodoTask todoTask);
//
//    @DELETE("todo_task/{id}")
//    LiveData<ApiResponse<Void>> deleteTask(@Path("id") String id);
}
