package pro.retor.mapmarkerstest.di.testpack.data.repos

import dagger.Module
import dagger.Provides
import pro.retor.mapmarkerstest.repos.base.MarkersRepository
import pro.retor.mapmarkerstest.repos.impl.BasicMarkersRepository
import javax.inject.Singleton

/**
 * Created by retor on 19.01.2018.
 */
@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun providesMarkersRepository(repo: BasicMarkersRepository): MarkersRepository = repo
}