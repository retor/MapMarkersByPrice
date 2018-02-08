package pro.retor.mapmarkerstest.ui.main

import android.os.Bundle
import android.os.Handler
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.subjects.PublishSubject
import pro.retor.mapmarkerstest.R
import pro.retor.mapmarkerstest.helpers.RxBinder
import pro.retor.mapmarkerstest.interactors.models.MarkersGroup
import pro.retor.mapmarkerstest.interactors.models.SomeMarker
import pro.retor.mapmarkerstest.ui.base.MapView

class MapsActivity : MvpAppCompatActivity(), MapView {
    private fun showError(): Consumer<Throwable> {
        return Consumer {
            showError(it)
        }
    }

    override fun showError(t: Throwable) {
        t.printStackTrace()
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private lateinit var mMap: GoogleMap

    private fun showMarkers(): Function<Observable<List<MarkersGroup>>, Disposable> {
        return RxBinder.ui(Consumer { list ->
            list.forEach { group ->
                Handler().post {
                    group.markers.forEach {
                        mMap.addMarker(createMarkerOptions(it, group.color))
                    }
                }
            }
            if (list.count() > 0)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(list[0].markers[0].lat, list[0].markers[0].lng)))
        }, showError())
    }

    private fun createMarkerOptions(it: SomeMarker, color: Float): MarkerOptions {
        val icon = BitmapDescriptorFactory.defaultMarker(color)
        return MarkerOptions().position(LatLng(it.lat, it.lng)).title(it.title).icon(icon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(mapListener)

        presenter.subscribeToMarkers(mapEmitter, showMarkers())
    }

    private val mapListener by lazy {
        OnMapReadyCallback {
            mMap = it
            mapEmitter.onNext("")
        }
    }

    val mapEmitter = PublishSubject.create<String>()
}
