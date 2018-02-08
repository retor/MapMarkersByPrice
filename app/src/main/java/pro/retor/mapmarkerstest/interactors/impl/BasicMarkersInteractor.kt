package pro.retor.mapmarkerstest.interactors.impl

import io.reactivex.Flowable
import io.reactivex.Observable
import pro.retor.mapmarkerstest.interactors.base.MarkersInteractor
import pro.retor.mapmarkerstest.interactors.filters.MarkersFilter
import pro.retor.mapmarkerstest.interactors.models.MarkersGroup
import pro.retor.mapmarkerstest.interactors.models.SomeMarker
import pro.retor.mapmarkerstest.repos.base.MarkersRepository
import javax.inject.Inject

/**
 * Created by retor on 05.02.2018.
 */
class BasicMarkersInteractor @Inject constructor(private val repository: MarkersRepository) : MarkersInteractor {

    override fun subscribeToMirkers(filter: MarkersFilter): Flowable<List<MarkersGroup>> {
        return repository.loadMarkersList()
                .flatMap {
                    Flowable.fromIterable(it)
                            .map { SomeMarker(it.lng, it.lat, it.title, it.price) }
                            .toList().toFlowable()
                }
                .map { it.groupBy { it.price } }
                .flatMap {
                    Observable.fromIterable(it.entries)
                            .sorted { o1, o2 -> o1.key.compareTo(o2.key) }
                            .map { makeGroups(filter, it) }
                            .toList()
                            .toFlowable()
                }
    }

    private fun makeGroups(filter: MarkersFilter, entry: Map.Entry<Long, List<SomeMarker>>): MarkersGroup {
        return MarkersGroup(entry.key, entry.value, filter.colorToPrice(entry.key))
    }
}