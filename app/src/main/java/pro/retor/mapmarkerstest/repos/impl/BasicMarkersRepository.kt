package pro.retor.mapmarkerstest.repos.impl

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import pro.retor.mapmarkerstest.data.api.MarkersApi
import pro.retor.mapmarkerstest.data.api.errors.ErrorHandler
import pro.retor.mapmarkerstest.helpers.RxHelpers
import pro.retor.mapmarkerstest.repos.base.MarkersRepository
import pro.retor.mapmarkerstest.repos.models.Marker
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by retor on 06.02.2018.
 */
class BasicMarkersRepository @Inject constructor(private val api: MarkersApi, private val errorHandler: ErrorHandler) : MarkersRepository {

    override fun loadMarkersList(): Flowable<List<Marker>> {
        return api.getMarkers(2, "5a6b56cb31000057201b8ab7")
                .compose(RxHelpers.applyError<List<pro.retor.mapmarkerstest.data.api.entity.Marker>, Response<List<pro.retor.mapmarkerstest.data.api.entity.Marker>>>(errorHandler))
                .compose(RxHelpers.applySchedulers<List<pro.retor.mapmarkerstest.data.api.entity.Marker>>())
                .compose {
                    it.flatMap {
                        Flowable.fromIterable(it)
                                .observeOn(Schedulers.newThread())
                                .map {
                                    Marker(it.lon ?: 0.0, it.lat ?: 0.0,
                                            "${it.price} price", it.price ?: 0)
                                }
                                .toList().toFlowable()
                    }
                }
    }
}