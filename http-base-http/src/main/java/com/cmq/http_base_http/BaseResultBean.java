package com.cmq.http_base_http;

/**
 * Created by cuimingqiang on 15/12/17.
 */

/**
 * 返回数据的包装类
 * @param <T> 真正想要的数据
 */
public class BaseResultBean<T> {
    /**
     * 0 位成功
     * 其他表示异常状态
     */
    public Integer code; //
    /**
     * 异常消息
     */
    public String msg;

    /**
     * 返回的数据
     */
    public T data; //数据
}
