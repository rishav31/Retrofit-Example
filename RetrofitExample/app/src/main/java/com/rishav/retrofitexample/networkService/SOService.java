package com.rishav.retrofitexample.networkService;

import com.rishav.retrofitexample.model.SOAnswersResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Rishav on 4/12/2018.
 */

public interface SOService {

    int answers = 1;//<answers?order=desc&sort=activity&site=stackoverflow>
    @GET("/answers")
    Call<SOAnswersResponse> getAnswers(@Query("order")String order,@Query("sort")String sort,@Query("site") String siteName);

    int answer_tags = 2;
    @GET("/answers")
    Call<SOAnswersResponse> getAnswers(@QueryMap Map<String,String> map);
}
