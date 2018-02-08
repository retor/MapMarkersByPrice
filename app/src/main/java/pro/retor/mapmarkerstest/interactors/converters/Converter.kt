package pro.retor.mapmarkerstest.interactors.converters

/**
 * Created by retor on 06.02.2018.
 */
interface Converter<I, O> {
    fun toModel(item: I): O

    fun fromModel(item: O): I
}