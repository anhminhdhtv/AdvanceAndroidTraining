//package com.example.mvvm_todoapp.data.retrofit;
//
//import com.example.mvvm_todoapp.data.model.ResultAPITodoTask;
//import com.example.mvvm_todoapp.data.model.TodoTask;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.http.Body;
//import retrofit2.http.DELETE;
//import retrofit2.http.Field;
//import retrofit2.http.FormUrlEncoded;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.PUT;
//import retrofit2.http.Path;
//import retrofit2.http.Query;
//
//public interface ApiRequest {
//    @GET("/api/todo_task")
//    Call<List<ResultAPITodoTask>> getTodoTask();
//
//    @DELETE("/api/todo_task/{id}")
//    Call<ResultAPITodoTask> deleteTodo(@Path("id") String id);
//
//    @GET("/api/todo_task/{id}")
//    Call<ResultAPITodoTask> getInfoTodo(@Path("id") String id);
//
//    //Add a todo
//    @FormUrlEncoded
//    @POST("/api/todo_task")
//    Call<ResultAPITodoTask> addTodo(
//            @Field("task_name") String taskName,
//            @Field("date") String date,
//            @Field("description") String description
//    );
//
//
//
//    //update todo
//    @PUT("/api/todo_task/{id}")
//    Call<ResultAPITodoTask> updateTodoTask(
//            @Path("id") String id,
//            @Body ResultAPITodoTask body
//    );
//
//
//}
