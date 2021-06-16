package com.example.mvvm_todoapp.data.remote;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.model.TodoTask;

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
    @GET("todo_task")
    LiveData<ApiResponse<List<TodoTask>>> getAllTasks();

    @GET("todo_task/{id}")
    LiveData<ApiResponse<TodoTask>> getTaskByID(@Path("id")String id);

    @POST("todo_task")
    LiveData<ApiResponse<Void>> insertTask(@Body TodoTask todoTask);

    @PUT("todo_task/{id}")
    LiveData<ApiResponse<Void>> update(@Path("id") String id, @Body TodoTask todoTask);

    @DELETE("todo_task/{id}")
    LiveData<ApiResponse<Void>> deleteTask(@Path("id") String id);
}
