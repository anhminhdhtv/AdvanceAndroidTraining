package com.example.mvvm_todoapp.data.remote;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todoapp.data.remote.ApiResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<ApiResponse<T>>> {

    private Type mResponseType;

    public LiveDataCallAdapter(Type mResponseType) {
        this.mResponseType = mResponseType;
    }

    @Override
    public Type responseType() {
        return this.mResponseType;
    }

    @Override
    public LiveData<ApiResponse<T>> adapt(Call<T> call) {

        return new LiveData<ApiResponse<T>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<T>() {
                                     @Override
                                     public void onResponse(Call<T> call, Response<T> response) {
                                         postValue(new ApiResponse<T>(response));
                                     }

                                     @Override
                                     public void onFailure(Call<T> call, Throwable t) {
                                         postValue(new ApiResponse<T>(t));
                                     }
                                 }

                    );
                }
            }
        };
    }
}
