package com.cmq.http_base_http;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by cuimingqiang on 16/3/2.
 *  将返回的数据去包装
 */
public class FlatMap<T> implements Func1<BaseResultBean<T>, Observable<T>> {
    @Override
    public Observable<T> call(final BaseResultBean<T> result) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (result.code == 200) {
                    subscriber.onNext(result.data);
                    subscriber.onCompleted();
                } else subscriber.onError(new HttpError(result.code, result.msg));
            }
        });
    }
}
