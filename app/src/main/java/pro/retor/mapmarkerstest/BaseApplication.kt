package pro.retor.mapmarkerstest

import android.app.Application
import pro.retor.mapmarkerstest.di.testpack.app.ApplicationComponent

/**
 * Created by retor on 07.02.2018.
 */
abstract class BaseApplication: Application() {

    abstract fun getAppComponent():ApplicationComponent
}