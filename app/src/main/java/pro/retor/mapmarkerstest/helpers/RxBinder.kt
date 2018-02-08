package pro.retor.mapmarkerstest.helpers

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

/**
 * Created by retor on 03.02.2018.
 */
object RxBinder {

    fun <T> ui(onNext: Consumer<T>, onError: Consumer<Throwable>): Function<Observable<T>, Disposable> {
        return Function {
            it.observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError)
        }
    }

    fun <T> bind(from: Observable<T>, uiFunction: Function<Observable<T>, Disposable>): Disposable {
        return uiFunction.apply(from)
    }
}