package pro.retor.mapmarkerstest.ui.base

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import pro.retor.mapmarkerstest.interactors.models.MarkersGroup

/**
 * Created by retor on 04.02.2018.
 */
abstract class MapPresenter : MvpPresenter<MapView>() {

    private val subscription: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initViews(viewState)
    }

    abstract fun initViews(viewState: MapView)

    abstract fun subscribeToMarkers(mapReady: Observable<String>, uiFunction: Function<Observable<List<MarkersGroup>>, Disposable>)

    protected fun addSubscription(subscription: Disposable) {
        this.subscription.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription.clear()
    }
}