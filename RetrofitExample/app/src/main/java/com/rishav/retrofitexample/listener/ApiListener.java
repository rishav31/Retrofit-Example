package com.rishav.retrofitexample.listener;

import com.rishav.retrofitexample.model.Result;

/**
 * Created by Rishav on 10-Aug-21.
 */
public interface ApiListener {

    void onSuccessResponse(int id, Result data);

    void onFailureResponse(int id, Result data);
}
