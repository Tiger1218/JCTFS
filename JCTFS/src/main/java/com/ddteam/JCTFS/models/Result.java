package com.ddteam.JCTFS.models;

public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    public Result(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static<T> Result<T> ok(String message, T data){
        Result<T> result = new Result<T>(0, message, data);
        return result;
    }
    public static<T> Result<T> error(String message, T data){
        Result<T> result = new Result<T>(1, message, data);
        return result;
    }
}
