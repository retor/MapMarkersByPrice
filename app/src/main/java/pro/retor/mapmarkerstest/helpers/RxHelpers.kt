package pro.retor.mapmarkerstest.helpers

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pro.retor.mapmarkerstest.data.api.errors.ErrorHandler
import pro.retor.mapmarkerstest.data.api.errors.ServerError
import retrofit2.Response


/**
 * Created by retor on 16.03.2016.
 */
object RxHelpers {
    fun <T> applySchedulers(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <M, T : Response<M>> applyError(errorHandler: ErrorHandler): FlowableTransformer<T, M> {
        return FlowableTransformer{ tObservable ->
            tObservable.flatMap { response ->
                Flowable.create<M>({ subscriber ->
                    if (!subscriber.isCancelled) {
                        if (response.isSuccessful && response.body()!=null) {
                            subscriber.onNext(response.body()!!)
                        } else {
                            if (response.code() == 401) {
                                subscriber.onError(errorHandler.parseError(response))
                            } else {
                                val serverError = errorHandler.parseError(response)
                                if (serverError?.message != null)
                                    subscriber.onError(serverError)
                                else
                                    subscriber.onError(ServerError(response.code(), response.message()))
                            }
                        }
                    }
                },BackpressureStrategy.LATEST)
            }
        }
    }

   /* fun <T> applySchedulersComputation(): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applySchedulersNewThread(): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyTransformationTextField(): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable
                    .skip(2)
                    .debounce(500L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
        }
    }

    fun <M, T : Response<M>> applyError(errorHandler: ErrorHandler): Observable.Transformer<T, M> {
        return Observable.Transformer { tObservable ->
            tObservable.flatMap { response ->
                Observable.create<M> { subscriber ->
                    if (subscriber != null && !subscriber.isUnsubscribed) {
                        if (response.isSuccessful) {
                            subscriber.onNext(response.body())
                        } else {
                            if (response.code() == 401) {
                                subscriber.onError(errorHandler.parseError(response))
//                                subscriber.onError(SessionExpiredException(response.message()))
                            } else {
                                val serverError = errorHandler.parseError(response)
                                if (serverError?.message != null)
                                    subscriber.onError(serverError)
                                else
                                    subscriber.onError(ServerError(response.code(), response.message()))
                            }
                        }
//                        subscriber.onCompleted()
                    }
                }
            }
        }
    }

    fun <M, T : Response<M>> applyErrorToResponse(errorHandler: ErrorHandler): Observable.Transformer<T, T> {
        return Observable.Transformer { tObservable ->
            tObservable.flatMap { response ->
                Observable.create<T> { subscriber ->
                    if (subscriber != null && !subscriber.isUnsubscribed) {
                        if (response.isSuccessful) {
                            subscriber.onNext(response)
                        } else {
                            if (response.code() == 401) {
                                subscriber.onError(errorHandler.parseError(response))
//                                subscriber.onError(SessionExpiredException(response.message()))
                            } else {
                                val serverError = errorHandler.parseError(response)
                                if (serverError?.message != null)
                                    subscriber.onError(serverError)
                                else
                                    subscriber.onError(ServerError(response.code(), response.message()))
                            }
                        }
//                        subscriber.onCompleted()
                    }
                }
            }
        }
    }

    fun timer(seconds: Int, change: Action1<Boolean>): Subscription {
        return Observable.timer(seconds.toLong(), TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { true }
                .subscribe(change)
    }

    fun <T> timer(seconds: Int, retryCount: Int, next: Observable<T>): Observable<T> {
        return Observable.timer(seconds.toLong(), TimeUnit.SECONDS, Schedulers.newThread())
                .flatMap { _ -> next }
                .retry(retryCount.toLong())
    }*/
}
