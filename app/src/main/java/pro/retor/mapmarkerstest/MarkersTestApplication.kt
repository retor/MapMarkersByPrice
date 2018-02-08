package pro.retor.mapmarkerstest

import pro.retor.mapmarkerstest.di.testpack.app.ApplicationComponent
import pro.retor.mapmarkerstest.di.testpack.app.ApplicationModule
import pro.retor.mapmarkerstest.di.testpack.app.DaggerApplicationComponent
import pro.retor.mapmarkerstest.di.testpack.data.DaggerRepositoriesComponent
import pro.retor.mapmarkerstest.di.testpack.data.DataModule
import pro.retor.mapmarkerstest.di.testpack.data.RepositoriesComponent
import pro.retor.mapmarkerstest.di.testpack.data.api.ApiModule
import pro.retor.mapmarkerstest.di.testpack.data.repos.RepositoriesModule
import pro.retor.mapmarkerstest.di.testpack.interactors.DaggerInteractorsComponent
import pro.retor.mapmarkerstest.di.testpack.interactors.InteractorsModule

/**
 * Created by retor on 07.02.2018.
 */
class MarkersTestApplication : BaseApplication() {
    companion object {
        private val reposComponent: RepositoriesComponent by lazy {
            DaggerRepositoriesComponent.builder()
                    .apiModule(ApiModule())
                    .dataModule(DataModule(BuildConfig.markersServer))
                    .repositoriesModule(RepositoriesModule())
                    .applicationComponent(comp)
                    .build()
        }

        val interactorsComponent by lazy{
            DaggerInteractorsComponent.builder()
                    .repositoriesComponent(reposComponent)
                    .interactorsModule(InteractorsModule())
                    .build()
        }

        lateinit var comp: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        comp = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun getAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}