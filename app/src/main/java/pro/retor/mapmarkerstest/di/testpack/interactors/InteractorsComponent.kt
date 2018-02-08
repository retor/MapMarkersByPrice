package pro.retor.mapmarkerstest.di.testpack.interactors

import dagger.Component
import pro.retor.mapmarkerstest.di.scopes.AppScope
import pro.retor.mapmarkerstest.di.testpack.data.RepositoriesComponent
import pro.retor.mapmarkerstest.interactors.base.MarkersInteractor
import pro.retor.mapmarkerstest.ui.main.MainPresenter

/**
 * Created by retor on 19.01.2018.
 */
@AppScope
@Component(modules = arrayOf(InteractorsModule::class),
        dependencies = arrayOf(RepositoriesComponent::class))
interface InteractorsComponent {

    fun providesMarkersInteractor(): MarkersInteractor

    fun inject(presenter: MainPresenter)
}