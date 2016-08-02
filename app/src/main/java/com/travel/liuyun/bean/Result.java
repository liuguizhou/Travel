package com.travel.liuyun.bean;

import java.io.Serializable;

/**
 * Created by shengshibotong.com.
 * User: Joe by 12-12-24 上午9:07
 */
public class Result<T> implements Serializable {
    private int status;
    private int code;
    private int recordcount;
    private T data;
    private String message;

    public Result() {
    }

    public Result(T data) {
        this(1, data);
    }

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, int code) {
        this.status = status;
        this.code = code;
    }

    public Result(int status, int code, T data) {
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public Result(int status, T data) {
        this(status, 200, data);
    }

    public boolean isFailed() {
        return 0 == status;
    }

    public static Result failed() {
        return new Result(0);
    }

    public boolean isSuccess() {
        return 1 == status;
    }

    public boolean isDataSuccess() {
        return 1 == status && null != data;
    }

    public static Result success() {
        return new Result(1);
    }

    public static Result error() {
        return new Result(0, 911);
    }

    public static Result error(String message) {
        final Result result = new Result(0, 911);
        result.setMessage(message);
        return result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(int recordcount) {
        this.recordcount = recordcount;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
