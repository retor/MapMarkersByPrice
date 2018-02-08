package pro.retor.mapmarkerstest.di.testpack.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by retor on 18.01.2018.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesApplication():Application = application

    @Provides
    fun providesAppContext():Context = application
}