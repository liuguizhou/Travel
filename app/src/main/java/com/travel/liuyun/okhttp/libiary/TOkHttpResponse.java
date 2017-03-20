package com.travel.liuyun.okhttp.libiary;

/**
 * Created by lpc on 2016/11/4.
 */

public class TOkHttpResponse<T> {

    private T result;
    private int status ;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
