package pro.retor.mapmarkerstest.ui.base

import com.arellomobile.mvp.MvpView

/**
 * Created by retor on 04.02.2018.
 */
interface MapView : MvpView {
    fun showError(t:Throwable)
}