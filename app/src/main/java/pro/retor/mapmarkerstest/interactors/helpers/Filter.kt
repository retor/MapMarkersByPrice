package pro.retor.mapmarkerstest.interactors.helpers

/**
 * Created by retor on 01.02.2018.
 */
interface Filter<T> {
    fun applyFilter(list: List<T>): Map<Int, List<T>>
}