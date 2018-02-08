package pro.retor.mapmarkerstest.di.testpack.interactors

import dagger.Module
import dagger.Provides
import pro.retor.mapmarkerstest.di.scopes.AppScope
import pro.retor.mapmarkerstest.interactors.base.MarkersInteractor
import pro.retor.mapmarkerstest.interactors.impl.BasicMarkersInteractor

/**
 * Created by retor on 19.01.2018.
 */
@Module
class InteractorsModule {

    @AppScope
    @Provides
    fun providesMarkersInteractor(interactor: BasicMarkersInteractor): MarkersInteractor =
            interactor
}