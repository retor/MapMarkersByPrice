package pro.retor.mapmarkerstest.repos.base

import io.reactivex.Flowable
import pro.retor.mapmarkerstest.repos.models.Marker

/**
 * Created by retor on 05.02.2018.
 */
interface MarkersRepository {
    fun loadMarkersList():Flowable<List<Marker>>
}