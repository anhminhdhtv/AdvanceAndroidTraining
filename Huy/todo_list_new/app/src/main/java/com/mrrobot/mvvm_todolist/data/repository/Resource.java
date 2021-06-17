package com.mrrobot.mvvm_todolist.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.mrrobot.mvvm_todolist.data.repository.Status.ERROR;
import static com.mrrobot.mvvm_todolist.data.repository.Status.LOADING;
import static com.mrrobot.mvvm_todolist.data.repository.Status.SUCCESS;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final String message;
    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> com.mrrobot.mvvm_todolist.data.repository.Resource<T> success(@NonNull T data) {
        return new com.mrrobot.mvvm_todolist.data.repository.Resource<>(SUCCESS, data, null);
    }

    public static <T> com.mrrobot.mvvm_todolist.data.repository.Resource<T> error(String msg, @Nullable T data) {
        return new com.mrrobot.mvvm_todolist.data.repository.Resource<>(ERROR, data, msg);
    }

    public static <T> com.mrrobot.mvvm_todolist.data.repository.Resource<T> loading(@Nullable T data) {
        return new com.mrrobot.mvvm_todolist.data.repository.Resource<>(LOADING, data, null);
    }
}
