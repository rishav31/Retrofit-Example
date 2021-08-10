package com.rishav.retrofitexample.model;

/**
 * Created by Rishav on 10-Aug-21.
 */

public class Result<T> {

    private T data;

    public Result(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}