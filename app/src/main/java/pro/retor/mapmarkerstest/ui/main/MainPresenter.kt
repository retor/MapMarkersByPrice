package pro.retor.mapmarkerstest.ui.main

import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject
import pro.retor.mapmarkerstest.MarkersTestApplication
import pro.retor.mapmarkerstest.helpers.RxBinder
import pro.retor.mapmarkerstest.interactors.base.MarkersInteractor
import pro.retor.mapmarkerstest.interactors.filters.MarkersFilter
import pro.retor.mapmarkerstest.interactors.models.MarkersGroup
import pro.retor.mapmarkerstest.ui.base.MapPresenter
import pro.retor.mapmarkerstest.ui.base.MapView
import javax.inject.Inject

/**
 * Created by retor on 04.02.2018.
 */
@InjectViewState
class MainPresenter @Inject constructor() : MapPresenter() {
    override fun initViews(viewState: MapView) {

    }

    private val subject = BehaviorSubject.create<List<MarkersGroup>>()

    @Inject
    lateinit var interactor: MarkersInteractor

    init {
        MarkersTestApplication.interactorsComponent.inject(this)
        interactor.subscribeToMirkers(object : MarkersFilter {
            override fun colorToPrice(price: Long): Float {
                return when (price) {
                    in 1L..10L -> BitmapDescriptorFactory.HUE_AZURE
                    in 11L..20L -> BitmapDescriptorFactory.HUE_BLUE
                    in 21L..30L -> BitmapDescriptorFactory.HUE_CYAN
                    in 31L..40L -> BitmapDescriptorFactory.HUE_GREEN
                    in 41L..50L -> BitmapDescriptorFactory.HUE_MAGENTA
                    in 51L..60L -> BitmapDescriptorFactory.HUE_ORANGE
                    in 61L..70L -> BitmapDescriptorFactory.HUE_RED
                    in 71L..80L -> BitmapDescriptorFactory.HUE_ROSE
                    in 81L..90L -> BitmapDescriptorFactory.HUE_VIOLET
                    in 91L..100L -> BitmapDescriptorFactory.HUE_YELLOW
                    else -> -1f
                }
            }
        }).subscribe(subject::onNext, subject::onError, subject::onComplete)
    }

    override fun subscribeToMarkers(mapReady: Observable<String>, uiFunction: Function<Observable<List<MarkersGroup>>, Disposable>) {
        addSubscription(RxBinder.bind(mapReady
                .flatMap { subject }, uiFunction))
    }
}