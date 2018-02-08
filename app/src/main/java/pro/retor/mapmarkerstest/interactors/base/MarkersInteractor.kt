package pro.retor.mapmarkerstest.interactors.base

import io.reactivex.Flowable
import pro.retor.mapmarkerstest.interactors.filters.MarkersFilter
import pro.retor.mapmarkerstest.interactors.models.MarkersGroup

/**
 * Created by retor on 05.02.2018.
 */
interface MarkersInteractor {
    fun subscribeToMirkers(filter:MarkersFilter):Flowable<List<MarkersGroup>>
}