package com.example.mvvm_todoapp.data.db.converter;


import androidx.room.TypeConverter;

import java.time.LocalDate;

public class DateConverter {
    @TypeConverter
    public static LocalDate toDate(Long timestamp) {
        return timestamp == null ? null : LocalDate.ofEpochDay(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(LocalDate date) {
        return date == null ? null : date.toEpochDay();
    }
}
