package pro.retor.mapmarkerstest.di.testpack.data.api

import dagger.Module
import dagger.Provides
import pro.retor.mapmarkerstest.data.api.MarkersApi
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by retor on 19.01.2018.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesPortalAuthApi(retrofit: Retrofit): MarkersApi = retrofit.create(MarkersApi::class.java)
}