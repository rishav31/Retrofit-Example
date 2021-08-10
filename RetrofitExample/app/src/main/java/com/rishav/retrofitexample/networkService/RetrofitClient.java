package com.rishav.retrofitexample.networkService;

import android.content.Context;
import android.util.Log;

import com.rishav.retrofitexample.listener.ApiListener;
import com.rishav.retrofitexample.model.Result;
import com.rishav.retrofitexample.model.SOAnswersResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rishav on 4/12/2018.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static RetrofitClient retrofitClient = null;
    private Context mContext;
    private ApiListener apiListener;

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static RetrofitClient getClientInstance(Context context){
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient(context);
        return retrofitClient;
    }

    private RetrofitClient(Context context){
        mContext = context;
        apiListener = (ApiListener) mContext;
    }

    private Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private SOService getSOService() {
        return getClient(BASE_URL).create(SOService.class);
    }

    public void loadAnswersUsingMap() {
        Map<String, String> map = new HashMap<>();
        map.put("order","aesc");
        map.put("sort","activity");
        map.put("site","stackoverflow");
        getSOService().getAnswers(map).enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                    apiListener.onSuccessResponse(SOService.answers,new Result(response.body()));
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                    apiListener.onFailureResponse(SOService.answers,new Result(statusCode));
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                apiListener.onFailureResponse(SOService.answers,new Result(t.getMessage()));
            }
        });
    }

    public void loadAnswers() {
        getSOService().getAnswers("desc","activity","stackoverflow").enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                    apiListener.onSuccessResponse(SOService.answers,new Result(response.body()));
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                    apiListener.onFailureResponse(SOService.answers,new Result(statusCode));
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API");
                apiListener.onFailureResponse(SOService.answers,new Result(t.getMessage()));
            }
        });
    }
}
