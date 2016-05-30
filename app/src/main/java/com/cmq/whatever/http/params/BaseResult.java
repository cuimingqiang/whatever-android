package com.cmq.whatever.http.params;

/**
 * Created by cuimingqiang on 16/5/30.
 */
public class BaseResult<T> {
    public int code;
    public String errMsg;
    public T data;
}
