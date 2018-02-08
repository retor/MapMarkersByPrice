package pro.retor.mapmarkerstest.di.testpack.data

import dagger.Component
import pro.retor.mapmarkerstest.di.testpack.app.ApplicationComponent
import pro.retor.mapmarkerstest.di.testpack.data.api.ApiModule
import pro.retor.mapmarkerstest.di.testpack.data.repos.RepositoriesModule
import pro.retor.mapmarkerstest.repos.base.MarkersRepository
import javax.inject.Singleton

/**
 * Created by retor on 19.01.2018.
 */
@Singleton
@Component(modules = arrayOf(DataModule::class, ApiModule::class, RepositoriesModule::class),
        dependencies = arrayOf(ApplicationComponent::class))
interface RepositoriesComponent {
    fun providesMarkersRepository(): MarkersRepository
}