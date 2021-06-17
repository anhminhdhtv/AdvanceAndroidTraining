//package com.example.mvvm_todoapp.data.retrofit;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;
//
//public class RetrofitRequest {
//    public static final String BASE_URL="https://5fa8f65ac9b4e90016e69cdc.mockapi.io/";
//
//    private static Retrofit retrofit;
//
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
//
//
//}
