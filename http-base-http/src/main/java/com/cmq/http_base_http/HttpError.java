package com.cmq.http_base_http;

/**
 * Created by cuimingqiang on 15/12/17.
 * 请求错误
 */
public class HttpError extends Exception{
    /**
     * 异常code
     */
    public int code;

    public HttpError(int code, String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpError{" +
                "code=" + code +
                '}'+super.toString();
    }
}
