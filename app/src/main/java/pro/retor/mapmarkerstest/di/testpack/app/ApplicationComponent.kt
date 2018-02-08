package pro.retor.mapmarkerstest.di.testpack.app

import android.app.Application
import android.content.Context
import dagger.Component

/**
 * Created by retor on 18.01.2018.
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun providesContext(): Context

    fun providesApplication(): Application
}